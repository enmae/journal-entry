package model;

import exceptions.EmptyTextException;
import exceptions.FullJournalException;

import java.util.*;
import static ui.Main.entryArray;

public class Journal extends LineRetriever {
    public String title;
    public List<Entry> listOfEntries;

    //MODIFIES: this
    //EFFECTS: Create new journal entry at a date
    public Journal(String title, List<Entry> loe) {
        this.title = title;
        this.listOfEntries = loe;
    }

//    public Journal(String title) {
//        this.title = title;
//        this.listOfEntries = new ArrayList<>();
//    }

    //EFFECTS: retrieve title
    public String getTitle() {
        return title;
    }

    //EFFECTS: set the title to new string
    public void setTitle(String title) { this.title = title; }

    //EFFECTS: Retrieve user input for which entry would get deleted.
    public void deleteEntry(Person p, Map hm) {
        System.out.println("Which entry would you want to delete?");
        String removeTitle = retrieveNextLine();

        deleteEntry(removeTitle, p, hm);
    }

    //REQUIRES: A valid person and a journal title in the journal array
    //MODIFIES: this
    //EFFECTS: Calls functions to remove the entry from the journal array if
    //         the title of the entry to be removed is the same as an entry
    //         in the journal array
    public void deleteEntry(String removeEntry, Person p, Map hm) {
        for (int i = 0; i < entryArray.size(); i++) {
            if (entryArray.get(i).title.equals(removeEntry)) {
                removeFromJournal(entryArray.get(i), p, hm);
            }
        }
    }

    //EFFECTS: Creates a new entry
    public void newEntry(Person p, Map hm) {
        System.out.println("Please enter a title for your entry:");
        String newTitle = retrieveNextLine();
        System.out.println("What are your thoughts for the day?");
        String newBody = retrieveNextLine();

        try {
            p.newEntry(p);
        } catch (FullJournalException e) {
            System.out.println("Full journal! Delete an entry before trying again.");
        } finally {
            System.out.println("Back to writing...");
        }

        System.out.println("New entry created. You now have a total of " + p.getEntries() + " total entries.");

        try {
            createNew(newTitle, newBody, hm, p);
        } catch (EmptyTextException e) {
            System.out.println("Missing text! Try creating your entry again");
        } finally {
            System.out.println("Back to writing...");
        }
    }

    //REQUIRES: A user entered title and body of entry
    //MODIFIES: this
    //EFFECTS: Checks if the title or body is empty, then creates new entry, and calls checkEntryInArray
    public void createNew(String title, String body, Map hm, Person p) throws EmptyTextException {
        if (title.equals(" ") || body.equals(" ") || (title.equals(" ") && body.equals(" "))) {
            throw new EmptyTextException();
        } else {
            Entry entry = p.newEntry(title, body);
            checkEntryInArray(entry);
            hm.put(entry, p);
        }
    }

    //REQUIRES: newEntry is not in journalArray
    //EFFECTS: Calls addToJournal if the newEntry doesn't exist in the array,
    //         does nothing otherwise.
    private void checkEntryInArray(Entry newEntry) {
        if (!entryArray.contains(newEntry)) {
            addToJournal(newEntry);
        }
    }

    //MODIFIES: this
    //EFFECTS: Add new journal entry to the journalArray
    private void addToJournal(Entry newEntry) {
        entryArray.add(newEntry);
    }

    //EFFECTS: Retrieve user input for suggested edits of the journal entry.
    public void editEntry() {
        userEditInput();

        System.out.println("Are there more edits you want to make?");
        String option = retrieveNextLine();

        if (option == "YES") {
            userEditInput();
        }
    }

    //EFFECTS: Contains the user input and calls save entry
    protected void userEditInput() {
        System.out.println("Which entry do you want to revise?");
        String reviseTitle = retrieveNextLine();
        System.out.println("Do you want to edit the TITLE or the BODY?");
        String editOption = retrieveNextLine();
        System.out.println("What do you want to change it to?");
        String change = retrieveNextLine();

        updateEntry(reviseTitle, editOption, change);
    }

    //MODIFIES: journalArray or this
    //EFFECTS: Updates information corresponding to changes happening in the entry
    public void updateEntry(String reviseTitle, String editOption, String change) {
        for(int i = 0; i<entryArray.size(); i++) {
            if (entryArray.get(i).title.equals(reviseTitle)) {
                switch(editOption) {
                    case("TITLE") : entryArray.get(i).updateTitle(change);
                    case("BODY") : entryArray.get(i).updateBody(change);
                }
            }
        }
    }

    //EFFECTS: returns entry found in array with string.
    public Entry findEntry(String str) {
        for(int i = 0; i<entryArray.size(); i++)
            if (entryArray.get(i).title.equals(str))
                return entryArray.get(i);

        return null;
    }

    //REQUIRES: entryToRemove is the entry being removed
    //EFFECTS: Calls the deletion functions in their respective areas
    private void removeFromJournal(Entry entryToRemove, Person p, Map hm) {
        entryArray.remove(entryToRemove);
        p.removeEntry(p);
        hm.remove(entryToRemove);
        System.out.println(entryToRemove.title + " has successfully been deleted.");
        System.out.println("You now have " + p.getEntries() + " entries.");
    }

    //EFFECTS: Takes user input for either a title, body, or entry to remove
    protected String retrieveNextLine() {
        return super.retrieveNextLine();
    }
}
