package observers;

import model.Entry;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<EntryObserver> entryObservers;

    public Subject() { entryObservers = new ArrayList(); }

    public void addObservers(EntryObserver eo) {
        if (!entryObservers.contains(eo)) {
            entryObservers.add(eo);
        }
    }

    public void removeObserver(EntryObserver eo) {
        if (entryObservers.contains(eo))
            entryObservers.remove(eo);
    }

    public void notifyObservers(Entry e) {
        for (EntryObserver eo:entryObservers)
            eo.update(e);
    }
}
