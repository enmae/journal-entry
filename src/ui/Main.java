package ui;

import interfaces.Loadable;
import model.Entry;
import model.Journal;
import model.LineRetriever;
import model.Person;
import observers.WebReader;

import java.util.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends LineRetriever implements Loadable {
    public static Person author;
    List<String> userOption = new ArrayList<>();
    public List<Person> peopleArray = new ArrayList<>();
    public static ArrayList<Entry> entryArray = new ArrayList<>();
    private String option = "";
    public static Map<Person, ArrayList<Entry>> entryMap = new HashMap<>();
    public static Journal journal = new Journal("Journal", null);

    public Main () throws IOException {
        //variables used for operations
        String name;

        //Creating new author
        outputStartUp();
        name = super.retrieveNextLine();
        createPerson(name);
//        boolean exists = checkPerson(name);

        load();
        author.addObservers(author);

        //Performing an action
        getOptionFromUser();

        System.out.println(userOption);
    }

    //EFFECTS: Loads all data from save file into program
    public static void load() throws IOException {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/save.txt"));
            System.out.println(lines);

            int index = 0;
            int entries;

            String name = lines.get(0).substring(5);
            author = new Person(name);
            entries = Integer.parseInt(lines.get(1).substring(18).trim());

            author.setEntries(entries);
            String journalName = lines.get(2).substring(14);

            for (String line : lines) {
                String sDate;
                String sTitle;
                String sBody;

                if (line.substring(0,5).equals("Date:")) {
                    sDate = lines.get(index).substring(5);
                    sTitle = lines.get(index+1).substring(6);
                    sBody = lines.get(index+2).substring(5);

                    Entry e = new Entry(sTitle, sBody, sDate);
                    entryArray.add(e);
                } else if (line.substring(0,6).equals("Title:")) {
                    index++;
                    continue;
                }
                else if (line.substring(0,5).equals("Body:")) {
                    index++;
                    continue;
                }
                else if (line.equals("==============")) {
                    index++;
                    continue;
                }

                index++;
            }

            entryMap.put(author, entryArray);
            journal = new Journal(journalName, entryArray);
        } catch (Exception e) {
            System.out.println("Failed to load data");
        }
    }

    //REQUIRES: Option having a string value
    //EFFECTS: For different options, calls different functions a part of the journal entry app
    private void checkOption() throws IOException {
        Entry e = new Entry("", "");

        switch (option) {
            case ("NEW") : journal.newEntry(this.author, entryMap);
                break;
            case ("EDIT") : journal.editEntry();
                break;
            case ("SAVE") : e.save(this.author, this.journal);
                break;
            case ("DELETE") : journal.deleteEntry(this.author, entryMap);
                break;
            case ("CLOSE") : java.lang.System.exit(0);
            default : {
                showEntries();
                e.viewEntries();
                System.out.println("==============================");
                break;
            }
        }

        getOptionFromUser();
    }

    //REQUIRES: Person name in string value
    //EFFECTS: Calls person constructor and notifies user an author was created
    public void createPerson(String name) {
        this.author = new Person(name);
        peopleArray.add(this.author);
        System.out.println("New author " + this.author.name + " created. Welcome " + this.author.name + "!");
        showEntries();
    }

    //EFFECTS: A program to hold personal journal entries.
    public static void main(String[] args) throws IOException {
        new GUI();
        new Main();
    }

    //EFFECTS: Output messages for user interface
    public void outputStartUp() {
        WebReader wr = new WebReader();
        try {
            wr.main("hi");
        } catch (IOException e) {
            System.out.println("Error printing web message");
        }
        System.out.println("Welcome to your personal journal!");
        System.out.println("==============================");
        System.out.println("Please enter your name:");
    }

    //MODIFIES: this
    //EFFECTS: Retrieving option selection from user
    public void getOptionFromUser() throws IOException {
        System.out.println("Select an option: NEW, EDIT, SAVE, DELETE, VIEW or CLOSE");
        this.option = super.retrieveNextLine();

        checkOption();
    }

    //EFFECTS: Check if the person already exists or not in the peopleArray
//    private boolean checkPerson(String name) {
//        for (int i = 0; i < peopleArray.size(); i++) {
//            if(peopleArray.get(i).name.equals(name)) {
//                welcomePerson(name);
//                return true;
//            }
//        }
//
//        createPerson(name);
//        return false;
//    }

    //EFFECTS: Welcomes the user back to the journal
//    private void welcomePerson(String name) {
//        System.out.println("Welcome back to your journal " + name + " !");
//    }

    //EFFECTS: Displays entries that are currently in the system.
    public void showEntries() {
        System.out.println("You have " + this.author.getEntries() +" journal entries.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Main main = (Main) o;
        return Objects.equals(entryMap, main.entryMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryMap);
    }
}