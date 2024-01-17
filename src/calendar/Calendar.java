/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar;

import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author rodi0878
 */
public class Calendar implements Iterable<EventSet>{

    @Override
    public Iterator<EventSet> iterator() {
        return calendar.iterator();
    }
    
    private Tree<EventSet> calendar;

    public Calendar() {
        calendar = new Tree<>();
    }
    
    public void addEvent(Event ev) {
        EventSet eventSet = new EventSet(ev.getDate());
        if (calendar.contains(eventSet)) {
            eventSet = calendar.get(eventSet);
        } else {
            calendar.add(eventSet);
        }
        
        eventSet.eventSet.add(ev);
    }
    
    public EventSet eventsList(Date date) {
        return calendar.get(new EventSet(date));
    }
    
    public void DeleteAll(Date date) {
        calendar.erase(new EventSet(date));
    }
}
