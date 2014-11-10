package org.evolvis.veraweb.onlinereg.rest;

import org.evolvis.veraweb.onlinereg.AbstractResourceTest;
import org.evolvis.veraweb.onlinereg.entities.Event;
import org.evolvis.veraweb.onlinereg.entities.Guest;
import org.evolvis.veraweb.onlinereg.entities.Person;
import org.hibernate.Session;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by mley on 02.09.14.
 */
public class EventResourceTest extends AbstractResourceTest<EventResource> {


    public EventResourceTest() {
        super(EventResource.class);
    }

    @BeforeClass
    public static void init() {
        createDummyEvents();
    }

    @Test
    public void testListEvents() {
        List<Event> events = resource.listEvents();
        assertEquals(3, events.size());
    }

    @Test@Ignore
    public void testListUserEvents() {
//        getDummyPersonAndGuests();
        List<Event> events = resource.listUsersEvents("exists");
        assertEquals(2, events.size());
    }

    @Test
    public void testGetEvent() {
        Event e = resource.getEvent(3);
        assertEquals("pastEvent", e.getShortname());
    }

    private static Date getFutureDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 15); // Adds 15 days
        return calendar.getTime();
    }

    private static Date getPastDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -15); // Removes 15 days
        return calendar.getTime();
    }

    private static void createDummyEvents() {
        Session session = sessionFactory.openSession();

        Event e = new Event();
        e.setPk(1);
        e.setDatebegin(getFutureDate());
        e.setShortname("shortname");
        e.setEventtype("Offene Veranstaltung");
        session.persist(e);

        e = new Event();
        e.setPk(2);
        e.setDatebegin(getFutureDate());
        e.setShortname("event2");
        session.persist(e);

        e = new Event();
        e.setPk(3);
        e.setDatebegin(getPastDate());
        e.setShortname("pastEvent");
        e.setEventtype("Offene Veranstaltung");
        session.persist(e);

        e = new Event();
        e.setPk(4);
        e.setDatebegin(getPastDate());
        e.setDateend(getFutureDate());
        e.setShortname("activeEvent");
        e.setEventtype("Offene Veranstaltung");
        session.persist(e);

        e = new Event();
        e.setPk(5);
        e.setDatebegin(getFutureDate());
        e.setDateend(getFutureDate());
        e.setShortname("futureEvent");
        e.setEventtype("Offene Veranstaltung");
        session.persist(e);

        e = new Event();
        e.setPk(6);
        e.setDatebegin(getPastDate());
        e.setDateend(getPastDate());
        e.setShortname("futureEvent");
        e.setEventtype("Offene Veranstaltung");
        session.persist(e);

        session.flush();
        session.close();
    }

//    private static void getDummyPersonAndGuests() {
//
//        Session session = sessionFactory.openSession();
//
//        Person person = new Person();
//        person.setPk(1);
//        person.setUsername("exists");
//        person.setFirstName("Hans");
//        person.setLastName("Wurst");
//        session.save(person);
//
//        Guest guest = new Guest();
//        guest.setPk(2);
//        guest.setFk_event(2);
//        guest.setFk_person(1);
//        session.persist(guest);
//
//        guest = new Guest();
//        guest.setPk(3);
//        guest.setFk_event(6);
//        guest.setFk_person(1);
//        session.persist(guest);
//
//        session.flush();
//        session.close();
//    }
}
