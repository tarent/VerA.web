-- Schema-Upgrade VerA.web Datenbank
--
-- Copyright © 2015
--	Thorsten “mirabilos” Glaser <t.glaser@tarent.de>
-- Copyright © 2013–2015 tarent solutions GmbH
--
-- This file is part of VerA.web and published under the same licence.

CREATE OR REPLACE FUNCTION serv_vwdbupgrade() RETURNS VARCHAR AS $$

DECLARE
	vmsg VARCHAR;
	vdate VARCHAR;
	vversion VARCHAR;
	vcurvsn VARCHAR;
	vnewvsn VARCHAR;
	vint INT4;

BEGIN

	-- set this to the current DB schema version (date)
	vversion := '2015-07-10';

	-- initialisation
	vint := 0;
	SELECT CURRENT_TIMESTAMP INTO vdate;
	SELECT COUNT(*) INTO vint FROM veraweb.tconfig WHERE cname = 'SCHEMA_VERSION';
	IF vint = 0 THEN
		RAISE EXCEPTION 'Not a VerA.web database'
		    USING HINT = 'veraweb.tconfig SCHEMA_VERSION does not exist';
		RETURN 'error';
	ELSEIF vint <> 1 THEN
		RAISE EXCEPTION 'Not a VerA.web database'
		    USING HINT = 'veraweb.tconfig SCHEMA_VERSION is not unique';
		RETURN 'error';
	END IF;

	SELECT cvalue INTO vcurvsn FROM veraweb.tconfig WHERE cname = 'SCHEMA_VERSION';
	vmsg := 'begin.SCHEMA UPDATE FROM VERSION ' || vcurvsn || ' TO VERSION ' || vversion;
	INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);
	IF vcurvsn > vversion THEN
		RAISE EXCEPTION 'VerA.web current database version (%) newer than target version (%)',
		    vcurvsn, vversion
		    USING HINT = 'veraweb.tconfig SCHEMA_VERSION';
		RETURN 'error';
	ELSEIF vcurvsn < '2013-02-21' THEN
		vmsg := 'end.SCHEMA UPDATE (DB schema too old)';
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);
		RAISE WARNING 'VerA.web current database version (%) too old', vcurvsn
		    USING HINT = 'Must be at least VerA.web 1.3.15 (2013-02-21)';
		RETURN 'error (DB too old)';
	ELSEIF vcurvsn = vversion THEN
		vmsg := 'end.SCHEMA UPDATE (nothing to do)';
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);
		RETURN 'ok ' || vversion || ' (nothing to do)';
	END IF;

	-- 1.4
	vnewvsn := '2013-06-12';
	IF vcurvsn < vnewvsn THEN
		vmsg := 'begin.update(1.4)';
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);

		-- Add table "ttask" with related sequence
		CREATE SEQUENCE ttask_pk_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
		CREATE TABLE veraweb.ttask (
			pk INTEGER DEFAULT nextval('ttask_pk_seq') NOT NULL,
			title VARCHAR(100) NOT NULL,
			description VARCHAR(1000),
			startdate TIMESTAMP WITH TIME ZONE,
			enddate TIMESTAMP WITH TIME ZONE,
			degree_of_completion INTEGER DEFAULT 0,
			priority INTEGER,
			fk_event INTEGER NOT NULL REFERENCES veraweb.tevent (pk),
			fk_person INTEGER REFERENCES veraweb.tperson (pk),
			createdby VARCHAR(50),
			changedby VARCHAR(50),
			created TIMESTAMP WITH TIME ZONE,
			changed TIMESTAMP WITH TIME ZONE,
			CONSTRAINT ttask_pkey PRIMARY KEY (pk)
		) WITH OIDS;

		-- Alter table "tlocation"
		ALTER TABLE veraweb.tlocation ADD COLUMN
			contactperson VARCHAR(250),
			ADD COLUMN address VARCHAR(250),
			ADD COLUMN zip VARCHAR(50),
			ADD COLUMN location VARCHAR(100),
			ADD COLUMN callnumber VARCHAR(50),
			ADD COLUMN faxnumber VARCHAR(50),
			ADD COLUMN email VARCHAR(100),
			ADD COLUMN comment VARCHAR(1000),
			ADD COLUMN url VARCHAR(250),
			ADD COLUMN gpsdata VARCHAR(1000),
			ADD COLUMN roomnumber VARCHAR(250);

		-- Alter table "tevent"
		ALTER TABLE veraweb.tevent DROP COLUMN location;
		ALTER TABLE veraweb.tevent ADD COLUMN fk_location INTEGER REFERENCES veraweb.tlocation(pk);

		-- post-upgrade 1.4
		vmsg := 'end.update(1.4)';
		UPDATE veraweb.tconfig SET cvalue = vnewvsn WHERE cname = 'SCHEMA_VERSION';
		vcurvsn := vnewvsn;
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);
	END IF;

	-- 1.5.0.1
	vnewvsn := '2014-11-17';
	IF vcurvsn < vnewvsn THEN
		vmsg := 'begin.update(1.5.0.1)';
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);

		-- Alter table "tevent"
		alter table veraweb.tevent add eventtype varchar(100);
		alter table veraweb.tevent add column mediarepresentatives varchar(100);

		-- Alter table "tguest"
		alter table veraweb.tguest add column delegation varchar(255);
		alter table veraweb.tguest add column osiam_login varchar(255);

		-- Trigger for link mandant with categories
		CREATE FUNCTION linkOrgUnitWithCategorie()
		        RETURNS TRIGGER AS $BODY$
		        BEGIN
		                INSERT INTO veraweb.tcategorie (fk_event, fk_orgunit, catname, flags, rank) VALUES (NULL, NEW.PK, 'Pressevertreter', NULL, NULL);
		                RETURN NEW;
		        END;
		        $BODY$ LANGUAGE plpgsql;

		CREATE TRIGGER createCategorieOnUnitInsert
		        AFTER INSERT ON veraweb.torgunit
		        FOR EACH ROW EXECUTE PROCEDURE linkOrgUnitWithCategorie();

		-- Migrate old OrgUnits
		CREATE FUNCTION migrateOrgUnits() RETURNS integer AS $BODY$
			DECLARE
			    unLinked RECORD;
			BEGIN

			FOR unLinked IN SELECT pk FROM veraweb.torgunit WHERE pk NOT IN( SELECT fk_orgunit FROM veraweb.tcategorie ) LOOP

		        execute format('INSERT INTO veraweb.tcategorie (fk_event, fk_orgunit, catname, flags, rank) VALUES (NULL, %s, %s, NULL, NULL);', unlinked, quote_literal(E'Pressevertreter'));

		    END LOOP;

		    RETURN 1;
		END;
		$BODY$ LANGUAGE plpgsql;

		PERFORM migrateOrgUnits();
		DROP FUNCTION migrateOrgUnits();

		-- Create new tables for the optional delegation fields
		CREATE TABLE veraweb.toptional_fields (
			pk serial NOT NULL,
			fk_event serial NOT NULL,
			label text,

			FOREIGN KEY (fk_event) REFERENCES veraweb.tevent(pk),
			PRIMARY KEY (pk)
		);

		CREATE TABLE veraweb.toptional_fields_delegation_content (
			fk_guest serial NOT NULL,
			fk_delegation_field serial NOT NULL,
			value text,

			FOREIGN KEY (fk_guest) REFERENCES veraweb.tguest(pk),
			FOREIGN KEY (fk_delegation_field) REFERENCES veraweb.toptional_fields(pk),
			PRIMARY KEY (fk_guest, fk_delegation_field)
		);

		-- post-upgrade 1.5.0.1
		vmsg := 'end.update(1.5.0.1)';
		UPDATE veraweb.tconfig SET cvalue = vnewvsn WHERE cname = 'SCHEMA_VERSION';
		vcurvsn := vnewvsn;
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);
	END IF;

	-- 1.5.0.2
	vnewvsn := '2015-01-26';
	IF vcurvsn < vnewvsn THEN
		vmsg := 'begin.update(1.5.0.2)';
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);

		-- Alter table "tevent"
		alter table veraweb.tevent add column hash varchar(100);

		-- post-upgrade 1.5.0.2
		vmsg := 'end.update(1.5.0.2)';
		UPDATE veraweb.tconfig SET cvalue = vnewvsn WHERE cname = 'SCHEMA_VERSION';
		vcurvsn := vnewvsn;
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);
	END IF;

	-- 1.5.0.3
	vnewvsn := '2015-02-05';
	IF vcurvsn < vnewvsn THEN
		vmsg := 'begin.update(1.5.0.3)';
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);

		-- Alter table "tperson"
		alter table veraweb.tperson add column username varchar(100);

		-- post-upgrade 1.5.0.3
		vmsg := 'end.update(1.5.0.3)';
		UPDATE veraweb.tconfig SET cvalue = vnewvsn WHERE cname = 'SCHEMA_VERSION';
		vcurvsn := vnewvsn;
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);
	END IF;

	-- 1.5.0.4
	vnewvsn := '2015-02-24';
	IF vcurvsn < vnewvsn THEN
		vmsg := 'begin.update(1.5.0.4)';
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);

		-- Alter table "tdoctype"
		ALTER TABLE veraweb.tdoctype ADD CONSTRAINT docname_unique UNIQUE(docname);

		-- post-upgrade 1.5.0.4
		vmsg := 'end.update(1.5.0.4)';
		UPDATE veraweb.tconfig SET cvalue = vnewvsn WHERE cname = 'SCHEMA_VERSION';
		vcurvsn := vnewvsn;
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);
	END IF;

	-- 1.5.1.0
	vnewvsn := '2015-02-25';
	IF vcurvsn < vnewvsn THEN
		vmsg := 'begin.update(1.5.1.0)';
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);

		-- Alter table "tdoctype"
		CREATE OR REPLACE FUNCTION veraweb.umlaut_fix(character varying) RETURNS character varying AS $BODY$
		    BEGIN
		        IF
		        ($1 LIKE '%ä%') THEN RETURN replace($1,'ä','ae');
		        END IF;
		        IF
		        ($1 LIKE '%ö%') THEN RETURN replace($1,'ö','oe');
		        END IF;
		        IF
		        ($1 LIKE '%ü%') THEN RETURN replace($1,'ü','ue');
		        END IF;
		        IF
		        ($1 LIKE '%ß%') THEN RETURN replace($1,'ß','ss');
		        END IF;
		        RETURN $1;
		    END;
		$BODY$ LANGUAGE plpgsql;

		-- post-upgrade 1.5.1.0
		vmsg := 'end.update(1.5.1.0)';
		UPDATE veraweb.tconfig SET cvalue = vnewvsn WHERE cname = 'SCHEMA_VERSION';
		vcurvsn := vnewvsn;
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);
	END IF;

	-- 1.5.1.2
	vnewvsn := '2015-03-12';
	IF vcurvsn < vnewvsn THEN
		vmsg := 'begin.update(1.5.1.2)';
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);

		-- Alter table "tcategorie"
		ALTER TABLE veraweb.tcategorie ADD CONSTRAINT mandant_categ_unique UNIQUE(fk_orgunit,catname);

		-- post-upgrade 1.5.1.2
		vmsg := 'end.update(1.5.1.2)';
		UPDATE veraweb.tconfig SET cvalue = vnewvsn WHERE cname = 'SCHEMA_VERSION';
		vcurvsn := vnewvsn;
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);
	END IF;

	-- 1.5.1.3
	vnewvsn := '2015-03-24';
	IF vcurvsn < vnewvsn THEN
		vmsg := 'begin.update(1.5.1.3)';
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);

		-- Create sequence "link_uuid_pk_seq"
		CREATE SEQUENCE veraweb.link_uuid_pk_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

		-- Create table "link_uuid"
		CREATE TABLE veraweb.link_uuid (
		    pk INTEGER DEFAULT nextval('veraweb.link_uuid_pk_seq') NOT NULL,
		    uuid character varying(100),
		    linktype character varying(100),
		    personid INTEGER DEFAULT 0,
		    CONSTRAINT link_uuid_pkey PRIMARY KEY (pk)
		) WITH OIDS;

		-- Add foreign key constraints
		ALTER TABLE veraweb.link_uuid ADD CONSTRAINT link_uuid_fkey_person
		    FOREIGN KEY (personid) REFERENCES veraweb.tperson (pk) ON DELETE RESTRICT ON UPDATE RESTRICT;

		-- post-upgrade 1.5.1.3
		vmsg := 'end.update(1.5.1.3)';
		UPDATE veraweb.tconfig SET cvalue = vnewvsn WHERE cname = 'SCHEMA_VERSION';
		vcurvsn := vnewvsn;
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);
	END IF;

	-- 1.5.1.5.1
	vnewvsn := '2015-04-14';
	IF vcurvsn < vnewvsn THEN
		vmsg := 'begin.update(1.5.1.5.1)';
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);

		-- Remove foreign key constraints (Hotfix)
		ALTER TABLE veraweb.tcategorie DROP CONSTRAINT mandant_categ_unique;

		-- post-upgrade 1.5.1.5.1
		vmsg := 'end.update(1.5.1.5.1)';
		UPDATE veraweb.tconfig SET cvalue = vnewvsn WHERE cname = 'SCHEMA_VERSION';
		vcurvsn := vnewvsn;
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);
	END IF;

	-- 1.5.1.6
	vnewvsn := '2015-04-15';
	IF vcurvsn < vnewvsn THEN
		vmsg := 'begin.update(1.5.1.6)';
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);

		-- Restore mandant_categ_unique constraint removed in 1.5.1.5.1
		ALTER TABLE veraweb.tcategorie ADD CONSTRAINT mandant_categ_unique UNIQUE(fk_orgunit,catname);

		-- post-upgrade 1.5.1.6
		vmsg := 'end.update(1.5.1.6)';
		UPDATE veraweb.tconfig SET cvalue = vnewvsn WHERE cname = 'SCHEMA_VERSION';
		vcurvsn := vnewvsn;
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);
	END IF;

	-- 1.5.1.9
	vnewvsn := '2015-04-23';
	IF vcurvsn < vnewvsn THEN
		vmsg := 'begin.update(1.5.1.9)';
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);

		-- Create table "tevent_function"
		CREATE TABLE veraweb.tevent_function (
		  pk serial NOT NULL,
		  fk_event int4 NOT NULL DEFAULT 0,
		  fk_function int4 NOT NULL DEFAULT 0,
		  CONSTRAINT tevent_function_pkey PRIMARY KEY (pk)
		) WITH OIDS;

		-- Create table "tevent_category"
		CREATE TABLE veraweb.tevent_category (
		  pk serial NOT NULL,
		  fk_event int4 NOT NULL DEFAULT 0,
		  fk_category int4 NOT NULL DEFAULT 0,
		  CONSTRAINT tevent_category_pkey PRIMARY KEY (pk)
		) WITH OIDS;

		-- post-upgrade 1.5.1.9
		vmsg := 'end.update(1.5.1.9)';
		UPDATE veraweb.tconfig SET cvalue = vnewvsn WHERE cname = 'SCHEMA_VERSION';
		vcurvsn := vnewvsn;
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);
	END IF;

	-- 1.5.1.17
	vnewvsn := '2015-05-20';
	IF vcurvsn < vnewvsn THEN
		vmsg := 'begin.update(1.5.1.17)';
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);

		-- Create new table for storing field types
		CREATE TABLE veraweb.toptional_field_type (
		  pk serial NOT NULL,
		  description text,

		  CONSTRAINT toptional_field_type_pk PRIMARY KEY (pk)
		) WITH OIDS;

		-- Inserting data for types
		INSERT INTO veraweb.toptional_field_type values (1, 'Eingabefeld');
		INSERT INTO veraweb.toptional_field_type values (2, 'Einfaches Auswahlfeld');
		INSERT INTO veraweb.toptional_field_type values (3, 'Mehrfaches Auswahlfeld');

		-- Modify toptional_fields to allow field types
		ALTER TABLE veraweb.toptional_fields ADD COLUMN fk_type INTEGER REFERENCES veraweb.toptional_field_type(pk);

		-- Create new table for storing field type's content
		CREATE SEQUENCE veraweb.toptional_field_type_content_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
		CREATE TABLE veraweb.toptional_field_type_content (
		  pk INTEGER DEFAULT nextval('veraweb.toptional_field_type_content_seq') NOT NULL,
		  fk_optional_field INTEGER NOT NULL REFERENCES veraweb.toptional_fields(pk) ON DELETE CASCADE,
		  content text NOT NULL,

		  CONSTRAINT toptional_field_type_content_pkey PRIMARY KEY (pk)
		);

		-- Update the orgunit for default categories
		UPDATE tcategorie set fk_orgunit=-1 where fk_orgunit IS NULL;

		-- post-upgrade 1.5.1.17
		vmsg := 'end.update(1.5.1.17)';
		UPDATE veraweb.tconfig SET cvalue = vnewvsn WHERE cname = 'SCHEMA_VERSION';
		vcurvsn := vnewvsn;
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);
	END IF;

	-- 1.5.1.21
	vnewvsn := '2015-06-02';
	IF vcurvsn < vnewvsn THEN
		vmsg := 'begin.update(1.5.1.21)';
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);

		-- Dropping old Primary Key constraint
		ALTER TABLE veraweb.toptional_fields_delegation_content DROP CONSTRAINT toptional_fields_delegation_content_pkey;

		-- Create new sequence to allow unique PK in toptional_fields_delegation_content
		CREATE SEQUENCE veraweb.toptional_fields_delegation_content_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

		-- Including new PK column to work with unique values
		ALTER TABLE veraweb.toptional_fields_delegation_content ADD COLUMN pk INTEGER DEFAULT nextval('veraweb.toptional_fields_delegation_content_seq') NOT NULL;
		ALTER TABLE veraweb.toptional_fields_delegation_content ADD CONSTRAINT toptional_fields_delegation_content_pkey PRIMARY KEY (pk);

		-- post-upgrade 1.5.1.21
		vmsg := 'end.update(1.5.1.21)';
		UPDATE veraweb.tconfig SET cvalue = vnewvsn WHERE cname = 'SCHEMA_VERSION';
		vcurvsn := vnewvsn;
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);
	END IF;

	-- 1.5.1.22
	vnewvsn := '2015-06-11';
	IF vcurvsn < vnewvsn THEN
		vmsg := 'begin.update(1.5.1.22)';
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);

		-- New flag to allow events without login
		ALTER TABLE veraweb.tevent ADD COLUMN login_required int4 DEFAULT 0;

		-- New UUID to identify created users to registration
		ALTER TABLE veraweb.tguest ADD COLUMN login_required_uuid CHARACTER VARYING(100);

		-- post-upgrade 1.5.1.22
		vmsg := 'end.update(1.5.1.22)';
		UPDATE veraweb.tconfig SET cvalue = vnewvsn WHERE cname = 'SCHEMA_VERSION';
		vcurvsn := vnewvsn;
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);
	END IF;

	-- 1.5.1.34
	vnewvsn := '2015-07-10';
	IF vcurvsn < vnewvsn THEN
		vmsg := 'begin.update(1.5.1.34)';
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);

		-- New flag to allow events without login
		ALTER TABLE veraweb.tevent ADD COLUMN maxreserve int4 DEFAULT 0;

		-- New column to identify Guest-Photo
		ALTER TABLE veraweb.tguest ADD COLUMN image_uuid character varying(100);

		-- post-upgrade 1.5.1.34
		vmsg := 'end.update(1.5.1.34)';
		UPDATE veraweb.tconfig SET cvalue = vnewvsn WHERE cname = 'SCHEMA_VERSION';
		vcurvsn := vnewvsn;
		INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);
	END IF;

	-- end
	IF vcurvsn <> vversion THEN
		RAISE WARNING 'Database version after upgrade (%) does not match target (%)',
		    vcurvsn, vversion
		    USING HINT = 'vcurvsn in last if block vs. vversion at begin of upgrade.sql';
	END IF;
	vmsg := 'end.SCHEMA UPDATE TO VERSION ' || vcurvsn;
	INSERT INTO veraweb.tupdate(date, value) VALUES (vdate, vmsg);
	IF vcurvsn <> vversion THEN
		RETURN 'error (schema version mismatch in upgrade.sql)';
	END IF;

	RETURN 'ok ' || vcurvsn || ' (success)';
END;
$$ LANGUAGE 'plpgsql' VOLATILE;

SELECT serv_vwdbupgrade() AS "Status Datenbank-Upgrade";