-- Alter table "tperson"

alter table veraweb.tperson add column username varchar(100);

-- Update schema version

UPDATE veraweb.tconfig SET cvalue = '2015-02-05' WHERE cname = 'SCHEMA_VERSION';