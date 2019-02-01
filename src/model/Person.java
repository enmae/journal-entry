package model;

import exceptions.FullJournalException;
import observers.EntryObserver;
import observers.Subject;

public class Person extends Subject implements EntryObserver {
    public String name;
    private Integer entries;
    private int observedEntries;

    //MODIFIES: this
    //EXPECTS: Sets the name of the author.
    public Person (String name) {
        this.name = name;
        this.entries = 0;
    }

    //EFFECTS: Returns the number of entries the Author has.
    public int getEntries() {
        return entries;
    }

    //REQUIRES: A not null person.
    //MODIFIES: this
    //EFFECTS: Adds another entry to current entry result.
    public void newEntry(Person p) throws FullJournalException {
        int currentEntries = p.getEntries();

        if (currentEntries > 5) {
            throw new FullJournalException();
        } else {
            p.setEntries(currentEntries + 1);
        }
    }

    //MODIFIES: this
    //EFFECTS: Sets the value of the new entry to the author
    public void setEntries(int sum) {
        this.entries = sum;
    }

    //MODIFIES: this
    //EFFECTS: Sets the string value of the author name to new name
    public void setName(String strName) { this.name = strName; }

    //MODIFIES: this
    //EFFECTS: Sets the entries value to be one less
    public void removeEntry(Person p) {
        p.setEntries(p.getEntries() - 1);
    }

    //EFFECTS: Creates a new entry and returns it.
    public Entry newEntry(String title, String body) {
        Entry e = new Entry(title,body);
        notifyObservers(e);
        return e;
    }

    @Override
    //EFFECTS: Adds a number to the observed number of entries in the journal and prints the total entries
    public void update(Entry e) {
        observedEntries++;
        System.out.println("There is a total of " + observedEntries + " observed entries!");
    }
}
