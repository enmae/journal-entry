package model;

//import exceptions.EmptyTextException;
//import exceptions.FullJournalException;
import interfaces.Saveable;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;
//import java.util.Map;
import java.util.Scanner;
import static ui.Main.entryArray;

public class Entry extends LineRetriever implements Saveable, Serializable {
    protected String date;
    public String title;
    protected String body;
    public Scanner scanner = new Scanner(System.in);

    //MODIFIES: this
    //EFFECTS: Create new journal entry at a date
    public Entry(String title, String body) {
        this.date = new Date().toString();
        this.title = title;
        this.body = body;
    }

    //MODIFIES: this
    //EFFECTS: Create new journal entry at a date
    public Entry(String title, String body, String date) {
        this.date = date;
        this.title = title;
        this.body = body;
    }

    //EFFECTS: Updates an entry's title
    public void updateTitle(String str) { this.title = str; }

    //EFFECTS: Updates an entry's body
    public void updateBody(String str) { this.body = str; }

    //EFFECTS: Return date
    public String getDate() { return this.date; }

    //EFFECTS: Return body
    public String getBody() { return this.body; }

    //EFFECTS: Calls openThisEntry to send collected user inputted title to a function.
    public void openEntry() {
        String openTitle = scanner.nextLine();
        openThisEntry(openTitle);
    }

    //EFFECTS: Takes user input for either a title, body, or entry to remove
    protected String retrieveNextLine() {
        return super.retrieveNextLine();
    }

    //EFFECTS: Outputs all needed information about an entry
    protected void openThisEntry(String title) {
        for (int i = 0; i < entryArray.size(); i++) {
            if (entryArray.get(i).title.equals(title)) {
                System.out.println(entryArray.get(i).title);
                System.out.println("Created on " + entryArray.get(i).date);
                System.out.println("Reads:" + entryArray.get(i).body);
            }
        }
    }

    //EFFECTS: Displays all entries in journalArray
    public void viewEntries() {
        System.out.println("All entries entered:");

        for(Entry e:entryArray) {
            System.out.println(e.title + " created at " + e.date);
        }

        System.out.println("Would you like to open an entry?");
        String answer = retrieveNextLine();

        if (answer == "YES") {
            System.out.println("Which entry would you like to open?");
            openEntry();
        }
    }

    //EFFECTS: Saves data to a text file.
    @Override
    public void save(Person p, Journal j) throws IOException {
        try {
            PrintWriter writer = new PrintWriter("src/save.txt","UTF-8");

            writer.println("Name: "+ p.name.trim());
            writer.println("Number of Entries: "+p.getEntries());
            writer.println("Journal title: " + j.getTitle().trim());

            for (int i = 0; i < entryArray.size(); i++) {
                writer.println("Date: " + entryArray.get(i).date.trim());
                writer.println("Title: " + entryArray.get(i).title.trim());
                writer.println("Body: " + entryArray.get(i).body.trim());
                writer.println("==============");
            }

            writer.close(); } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }

        System.out.println("Your information has been saved!");
    }
}
