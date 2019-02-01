package model;

import java.util.Scanner;

public abstract class LineRetriever {
    public Scanner scanner = new Scanner(System.in);

    //EFFECTS: Scans next line in console
    protected String retrieveNextLine() {
        return scanner.nextLine();
    }
}
