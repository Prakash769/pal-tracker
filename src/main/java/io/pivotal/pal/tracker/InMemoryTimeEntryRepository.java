package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    HashMap<Long,TimeEntry> hMap = new HashMap();
    long currid = 0L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {


         TimeEntry tEntry = new TimeEntry(++currid,timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(),timeEntry.getHours());

        //tEntry.setId(id);

        hMap.put(tEntry.getId(),tEntry);

        return hMap.get(tEntry.getId());
    }

    @Override
    public TimeEntry find(long id) {

        System.out.print("find: "+id);
       //TimeEntry timeEntry = new TimeEntry();

        //timeEntry = hMap.get(id);

            return hMap.get(id);
    }

    @Override
    public List<TimeEntry> list() {

        return new ArrayList(hMap.values());

    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (find(id) == null){return null;}

        TimeEntry tEntry = new TimeEntry(id,timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(),timeEntry.getHours());

        System.out.println("tEntry:::"+tEntry);

        hMap.put(tEntry.getId(),tEntry);

        return tEntry;
    }

    public void delete(long id) {
        hMap.remove(id);
    }
}
