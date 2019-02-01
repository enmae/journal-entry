package observers;

import model.Entry;

public interface EntryObserver {
    //EFFECTS: respond to journal receiving an entry
    void update(Entry e);
}
