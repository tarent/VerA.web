package org.evolvis.veraweb.onlinereg.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by mley on 03.08.14.
 */
@Data
@XmlRootElement
@Entity
@Table(name = "tevent")
@NamedQueries({
        @NamedQuery(name = "Event.list", query = "SELECT e FROM Event e where CURRENT_TIMESTAMP < e.datebegin OR CURRENT_TIMESTAMP < e.dateend"),
        @NamedQuery(name = "Event.getEvent", query = "SELECT e FROM Event e where e.pk = :pk")
})
public class Event {
	
    @Id
    private int pk;
    private String shortname;
    private Date datebegin;
    private Date dateend;

    @ManyToOne
    @JoinColumn(name="fk_location")
    private Location location;
}
