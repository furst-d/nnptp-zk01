/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar;

import java.util.Date;
import java.util.List;
import persistence.Storage;

/**
 *
 * @author Roman
 */
public class INPTP_Clean00 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* TODO:
         0) vytvořit git repozitář a COMMITovat každou dílčí ucelenou změnu!
         1) vyčistit kód (clean code)
         2) unit testy
         ---- je nutné důkladně otestovat binární strom
         ---- přidání do prázdného, neprázdného stromu (levá/pravá větev)
         ---- mazání - kořene, listu, vnitřních prvků
         ---- iterování přes prázdný/plný strom
         ---- testy kalendáře, skupiny událostí
         3) celý repozitář odevzdat na stag - semestrální práce
         */

        Storage storage = new Storage();
        storage.store(new Event(new Date(117, java.util.Calendar.JULY, 1), "1-4"));
        storage.store(new Event(new Date(117, java.util.Calendar.AUGUST, 1), "1-4"));
        storage.unstore(new Event(new Date(117, java.util.Calendar.JULY, 1), "1-4"));

        List<Event> eventList = storage.load(Event.class);
        System.out.println(eventList);
        System.out.println("");

        //////////////////////////////////
        
        Calendar calendar = new Calendar();
        calendar.addEvent(new Event(new Date(117, java.util.Calendar.JULY, 1), "1-1"));
        calendar.addEvent(new Event(new Date(117, java.util.Calendar.JULY, 2), "2-1"));
        calendar.addEvent(new Event(new Date(117, java.util.Calendar.JULY, 3), "3-1"));
        calendar.addEvent(new Event(new Date(117, java.util.Calendar.JULY, 1), "1-2"));
        calendar.addEvent(new Event(new Date(117, java.util.Calendar.JULY, 1), "1-3"));
        calendar.addEvent(new Event(new Date(117, java.util.Calendar.JULY, 1), "1-4"));

        for (EventSet c1 : calendar) {
            System.out.println(c1.date);
            System.out.println(c1.eventSet);
        }

        calendar.deleteAll(new Date(117, java.util.Calendar.JULY, 1));
        System.out.println("");

        for (EventSet c1 : calendar) {
            System.out.println(c1.date);
            System.out.println(c1.eventSet);
        }

    }

}
