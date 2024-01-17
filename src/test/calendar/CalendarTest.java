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
        calendar.DeleteAll(date);

        EventSet eventSet = calendar.eventsList(date);
        assertNull( eventSet);
    }
}