package test.calendar;

import calendar.Calendar;
import calendar.Event;
import calendar.EventSet;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CalendarTest {
    @Test
    public void testAddEvent() {
        Calendar calendar = new Calendar();
        Date date1 = new Date(2024, java.util.Calendar.MARCH, 17);
        Event event = new Event(date1, "Meeting");

        calendar.addEvent(event);

        EventSet eventSet = calendar.eventsList(date1);
        assertNotNull(eventSet);
        assertTrue(eventSet.eventSet.contains(event));
    }

    @Test
    public void testDeleteAll() {
        Calendar calendar = new Calendar();
        Date date = new Date(2024, java.util.Calendar.JUNE, 15);

        calendar.addEvent(new Event(date, "Conference"));
        calendar.deleteAll(date);

        EventSet eventSet = calendar.eventsList(date);
        assertNull(eventSet);
    }

    @Test
    public void testRemoveNonExistentEvent() {
        Calendar calendar = new Calendar();
        calendar.deleteAll(new Date(2024, 1, 19)); // Datum, pro které neexistují žádné události

        EventSet eventSet = calendar.eventsList(new Date(2024, 1, 19));
        assertNull(eventSet);
    }


    @Test
    public void testAddingEventsOnDifferentDates() {
        Calendar calendar = new Calendar();
        Date date1 = new Date(2024, java.util.Calendar.MARCH, 17);
        Date date2 = new Date(2024, java.util.Calendar.MARCH, 18);
        Event event1 = new Event(date1, "Meeting 1");
        Event event2 = new Event(date2, "Meeting 2");
        calendar.addEvent(event1);
        calendar.addEvent(event2);

        EventSet eventSet1 = calendar.eventsList(date1);
        EventSet eventSet2 = calendar.eventsList(date2);
        assertNotNull(eventSet1);
        assertNotNull(eventSet2);
        assertTrue(eventSet1.eventSet.contains(event1));
        assertTrue(eventSet2.eventSet.contains(event2));
    }
}