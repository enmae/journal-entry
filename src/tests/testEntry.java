package tests;

import exceptions.EmptyTextException;
import exceptions.FullJournalException;
import model.Journal;
import model.Person;
import model.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import exceptions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testEntry {
    Person testPerson;
    Entry test;
    Entry test2;
    Journal testJournal;
    List<Entry> testEntryList;
    String testTitle;
    String testBody;
    Map<Person, ArrayList<Entry>> testMap;

    @BeforeEach
    public void setup() {
        testPerson = new Person("Test");
        test = new Entry("TESTING", "As a test, it ensures that the code is running as expected");
        test2 = new Entry("Word", "As a test of words");
        testEntryList = new ArrayList<>();
        testTitle = "A very suspicious thing";
        testBody = "Confused thoughts swirl around in an understandable wonder";
        testMap = new HashMap<>();
    }

    @Test
    public void testNewEntryWithOneEntry() {
        try {
            testJournal.createNew(testTitle, testBody, testMap, testPerson);
        } catch (EmptyTextException e) {

        }
        try {
            testPerson.newEntry(testPerson);
        } catch (FullJournalException e) {

        }
        assertEquals(testPerson.getEntries(),1);
    }

    @Test
    public void testNewEntryWithThreeDiffEntries() {
        try {
            testPerson.newEntry(testPerson);
        } catch (FullJournalException e) {

        }
        try {
            testJournal.createNew(testTitle, testBody, testMap, testPerson);
        } catch (EmptyTextException e) {

        }
        try {
            testPerson.newEntry(testPerson);
        } catch (FullJournalException e) {

        }
        try {
            testJournal.createNew("TESTING", "As a test, it ensures that the code is running as expected", testMap, testPerson);
        } catch (EmptyTextException e) {

        }
        try {
            testPerson.newEntry(testPerson);
        } catch (FullJournalException e) {

        }
        try {
            testJournal.createNew("Word", "As a test of words", testMap, testPerson);
        } catch (EmptyTextException e) {

        }

        assertEquals(testPerson.getEntries(),3);
    }

    //Testing the adding of the Journal entry to the list
    @Test
    public void testViewEntryWithOneEntry() {
        testEntryList.add(test);

        assertEquals(testEntryList.size(), 1);
    }

    //Testing the adding of the journal entry to the list with more than one
    @Test
    public void testViewEntryWithFiveEntries() {
        testEntryList.add(test);
        testEntryList.add(test);
        testEntryList.add(test);
        testEntryList.add(test);
        testEntryList.add(test);

        assertEquals(testEntryList.size(), 5);
    }

    //NEEDS: A test of the view entries of the list - how 'assert' for outputs?

    @Test
    public void testDeleteEntryWithNoTitle() {
        testEntryList.add(test);

        testJournal = new Journal("Test", testEntryList);
        testJournal.deleteEntry("", testPerson, testMap);
        assertEquals(testEntryList.size(), 1);
    }

    @Test
    public void testDeleteEntryWithWrongTitle() {
        try {
            testJournal.createNew(testTitle, testBody, testMap, testPerson);
        } catch (EmptyTextException e) {

        }
        testEntryList.add(test);

        testJournal = new Journal("Test", testEntryList);
        testJournal.deleteEntry("Test this", testPerson, testMap);
        assertEquals(testEntryList.size(), 1);
    }

    @Test
    public void testDeleteEntryWithCorrectTitle() {
        try {
            testJournal.createNew(testTitle, testBody, testMap, testPerson);
        } catch (EmptyTextException e) {

        }

        testJournal = new Journal("Test", testEntryList);
        testJournal.deleteEntry("TESTING", testPerson, testMap);
        assertEquals(testEntryList.size(), 0);
    }

    @Test
    public void testDeleteEntryWithWrongTitleFiveEntries() {
        Entry test3 = new Entry("SFKDJ", "asdfkjlja");
        Entry test4 = new Entry(testTitle, "sdjfasdf");
        Entry test5 = new Entry("ASdlj", "Sdflkasjf");

        testEntryList.add(test);
        testEntryList.add(test2);
        testEntryList.add(test3);
        testEntryList.add(test4);
        testEntryList.add(test5);

        try {
            testPerson.newEntry(testPerson);
        } catch (FullJournalException e) {

        }
        try {
            testPerson.newEntry(testPerson);
        } catch (FullJournalException e) {

        }
        try {
            testPerson.newEntry(testPerson);
        } catch (FullJournalException e) {

        }
        try {
            testPerson.newEntry(testPerson);
        } catch (FullJournalException e) {

        }
        try {
            testPerson.newEntry(testPerson);
        } catch (FullJournalException e) {

        }

        testJournal = new Journal("Test", testEntryList);
        testJournal.deleteEntry("Test this", testPerson, testMap);
        assertEquals(testEntryList.size(), 5);
    }

    @Test
    public void testDeleteEntryWithCorrectTitleFiveEntries() {
        Entry test3 = new Entry("SFKDJ", "asdfkjlja");
        Entry test4 = new Entry(testTitle, "sdjfasdf");
        Entry test5 = new Entry("ASdlj", "Sdflkasjf");

        testEntryList.add(test);
        testEntryList.add(test2);
        testEntryList.add(test3);
        testEntryList.add(test4);
        testEntryList.add(test5);

        try {
            testPerson.newEntry(testPerson);
        } catch (FullJournalException e) {

        }
        try {
            testPerson.newEntry(testPerson);
        } catch (FullJournalException e) {

        }
        try {
            testPerson.newEntry(testPerson);
        } catch (FullJournalException e) {

        }
        try {
            testPerson.newEntry(testPerson);
        } catch (FullJournalException e) {

        }
        try {
            testPerson.newEntry(testPerson);
        } catch (FullJournalException e) {

        }

        testJournal = new Journal("Test", testEntryList);
        testJournal.deleteEntry("TESTING", testPerson, testMap);
        assertEquals(testEntryList.size(), 5);
    }

    @Test
    public void testEditEntryWithOneChange() {
        testEntryList.add(test);

        Entry expectedResult = new Entry("Testing", "As a test, it ensures that the code is running as expected");
        testJournal.updateEntry("TESTING", "TITLE", expectedResult.title);

        assertEquals("TESTING", test.title);
    }
}
