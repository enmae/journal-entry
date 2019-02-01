package tests;

import exceptions.FullJournalException;
import model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testPerson {
    Person test;

    @BeforeEach
    public void setup() {
        test = new Person("Test");
    }

    //Testing the get entries function initially
    @Test
    public void testGetEntries() {
        assertEquals(test.getEntries(),0);
    }

    //Testing the new entries function with one value
    @Test
    public void testNewEntryOnce() {
        try {
            test.newEntry(test);
        } catch (FullJournalException e) {

        }

        assertEquals(test.getEntries(), 1);
    }

    //Testing the new entries function with three values
    @Test
    public void testNewEntryThreeTimes() {
        try {
            test.newEntry(test);
        } catch (FullJournalException e) {

        }
        try {
            test.newEntry(test);
        } catch (FullJournalException e) {

        }
        try {
            test.newEntry(test);
        } catch (FullJournalException e) {

        }

        assertEquals(test.getEntries(),3);
    }
}
