package tests;

import exceptions.EmptyTextException;
import exceptions.FullJournalException;
import model.Entry;
import model.Journal;
import model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;

public class testExceptions {
    Person testPerson;
    Entry test;
    Entry test2;
    Journal testJournal;
    List<Entry> testEntryList;
    String testTitle;
    String testBody;
    String testEmptyTitle;
    String testEmptyBody;
    Map<Person, ArrayList<Entry>> testMap;

    @BeforeEach
    public void setup() {
        testPerson = new Person("Test");
        test = new Entry("TESTING", "As a test, it ensures that the code is running as expected");
        test2 = new Entry("Word", "As a test of words");
        testEntryList = new ArrayList<>();
        testTitle = "A very suspicious thing";
        testBody = "Confused thoughts swirl around in an understandable wonder";
        testEmptyTitle = "";
        testEmptyBody = "";
    }

    @Test
    public void testEmptyTextExceptionWithText() {
        try {
            testJournal.createNew(testTitle, testBody, testMap, testPerson);
        } catch (EmptyTextException e) {
            System.out.println("We pass! No exception failing here");
        }
    }

    @Test
    public void testEmptyTextExceptionWithoutTitle() {
        try {
            testJournal.createNew(testEmptyTitle, testBody, testMap, testPerson);
        } catch (EmptyTextException e) {
            fail("Cannot create new entry without a title.");
        }
    }

    @Test
    public void testEmptyTextExceptionWithoutBody() {
        try {
            testJournal.createNew(testTitle, testEmptyBody, testMap, testPerson);
        } catch (EmptyTextException e) {
            fail("Cannot create new entry without a body.");
        }
    }

    @Test
    public void testEmptyTextExceptionWithoutBothTitleAndBody() {
        try {
            testJournal.createNew(testEmptyTitle, testEmptyBody, testMap, testPerson);
        } catch (EmptyTextException e) {
            fail("Cannot create new entry without a title or body");
        }
    }

    @Test
    public void testFullJournalExceptionWithNoEntries() {
        try {
            testPerson.newEntry(testPerson);
        } catch (FullJournalException e) {
            System.out.println("Wrong exception");
        }
    }

    @Test
    public void testFullJournalExceptionWithOneEntry() {
        try {
            testJournal.createNew(testTitle, testBody, testMap, testPerson);
        } catch (EmptyTextException e) {
            System.out.println("Wrong exception");
        }

        try {
            testPerson.newEntry(testPerson);
        } catch (FullJournalException e) {
            System.out.println("Wrong exception");
        }
    }

    @Test
    public void TestFullJournalExceptionWithFiveEntries() {
        try {
            testJournal.createNew("a", "b", testMap, testPerson);
        } catch (EmptyTextException e) {

        }
        try {
            testJournal.createNew("c", "d", testMap, testPerson);
        } catch (EmptyTextException e) {

        }
        try {
            testJournal.createNew("e","f", testMap, testPerson);
        } catch (EmptyTextException e) {

        }
        try {
            testJournal.createNew("g", "h", testMap, testPerson);
        } catch (EmptyTextException e) {

        }
        try {
            testJournal.createNew("i", "j", testMap, testPerson);
        } catch (EmptyTextException e) {

        }

        try {
            testPerson.newEntry(testPerson);
        } catch (FullJournalException e) {
            fail("Cannot create a new journal when the journal is full!");
        }
    }
}
