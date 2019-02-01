package ui;

import model.Entry;
import model.Person;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI extends JFrame implements ActionListener, ListSelectionListener {
        private JLabel numberOfEntriesLabel;
        private JLabel welcomeLabel;
        private JLabel viewLabel;
        private JLabel nameLabel;
        private JTextField nameField;
        private JList<String> entryJList;
        private DefaultListModel entriesModel;
        private JButton btnOpen;
        private JButton btnDelete;
        private JButton btnSave;
        private JButton btnLoad;
        public static JButton btnUpdateName;
        private String entriesValue;
        private Entry selectedEntry;

        public GUI() {
            //Creating a window
            super("Journal Program");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            //Size of the window
            setPreferredSize(new Dimension(400, 300));
            entriesModel = new DefaultListModel();
            entriesModel.addElement(" ");
            setResizable(true);

            //This is something
            ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
            setLayout(new FlowLayout());

            //Buttons
            JButton btnCreate = new JButton("New Entry");
            btnUpdateName = new JButton("Update Name");
            btnOpen = new JButton("Open Selected");
            btnOpen.setEnabled(false);
            btnDelete = new JButton("Delete Selected");
            btnDelete.setEnabled(false);
            btnSave = new JButton("Save Entries");
            btnLoad = new JButton("Load Entries");

            //Actions and listeners for the button
            btnUpdateName.setActionCommand("updateName");
            btnUpdateName.addActionListener(this);
            btnCreate.setActionCommand("createNewEntry");
            btnCreate.addActionListener(this);
            btnOpen.setActionCommand("open");
            btnOpen.addActionListener(this);
            btnDelete.setActionCommand("delete");
            btnDelete.addActionListener(this);
            btnSave.setActionCommand("save");
            btnSave.addActionListener(this);
            btnLoad.setActionCommand("load");
            btnLoad.addActionListener(this);

            //Creating labels and textbox
            welcomeLabel = new JLabel("Welcome to your journal! What's your name?");
            nameLabel = new JLabel("Name: ");
            viewLabel = new JLabel("View Entries:");
            //Columns is how wide the textbox is.
            nameField = new JTextField(10);
            entriesValue = String.valueOf(Main.entryArray.size());
            numberOfEntriesLabel = new JLabel("Total Entries: " + entriesValue);

            //loop to iterate over all individual entries to show them in GUI made by user
            setEntriesModel();

            entryJList = new JList<>(entriesModel);
            entryJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            entryJList.setLayoutOrientation(JList.VERTICAL);
            entryJList.addListSelectionListener(this);
            entryJList.setSelectedIndex(0);
            entryJList.setVisibleRowCount(5);

            JScrollPane listScroller = new JScrollPane(entryJList);
            listScroller.setPreferredSize(new Dimension(250, 80));

            //Adding elements to the window
            add(welcomeLabel);
            add(nameLabel);
            add(nameField);
            add(btnUpdateName);
            add(numberOfEntriesLabel);
            add(viewLabel);
            add(entryJList);
            add(btnCreate);
            add(btnOpen);
            add(btnDelete);
            add(btnSave);
            add(btnLoad);
            //Packaging the window so that it is presentable?? I think
            pack();
            //Setting where the location would appear to be in the computer space
            setLocationRelativeTo(null);
            //So we can view the window and not resize the window. Makes sense
            setVisible(true);
            setResizable(false);
        }

        //EFFECTS: Performs actions!
        public void actionPerformed(ActionEvent e) {
            //EFFECTS: Making a new author when a name is first entered
            if(e.getActionCommand().equals("updateName") && nameLabel.getText() == "Name: ")
                Main.author = new Person(nameField.getText());

            //EFFECTS: Updating the author's name on action update name
            if(e.getActionCommand().equals("updateName")) {
                nameLabel.setText("Name: " + nameField.getText());
                Main.author.setName(nameField.getText());
            }

            //EFFECTS: Opening up a new window to create a new entry
            if(e.getActionCommand().equals("createNewEntry"))
                new NewEntryGUI();

            if(e.getActionCommand().equals("open"))
                new OpenGUI(selectedEntry);

            if(e.getActionCommand().equals("delete"))
                new ConfirmGUI(selectedEntry);

            if(e.getActionCommand().equals("save")) {
                try {
                    Main.entryArray.get(0).save(Main.author, Main.journal);
                } catch (IOException e1) {
                    System.out.println("Error saving entries.");
                }
            }

            if(e.getActionCommand().equals("load")) {
                try {
                    Main.load();
                    setEntriesModel();
                    nameLabel.setText(Main.author.name);
                    nameField.setText(Main.author.name);
                    btnUpdateName.doClick();
                } catch (IOException e1) {
                    System.out.println("Error loading entries");
                }
            }

            numberOfEntriesLabel.setText("Total Entries: " + String.valueOf(Main.author.getEntries()));
            setEntriesModel();
        }

        public static void main(String[] args) {
            new GUI();
        }

        public void setEntriesModel() {
            entriesModel.clear();
            for (Entry e:Main.entryArray)
                entriesModel.addElement(e.title);

//            entryJList = new JList<>(entriesModel);
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting() == false) {
                if (entryJList.getSelectedIndex() == -1) {
                    btnOpen.setEnabled(false);
                    btnDelete.setEnabled(false);
                } else {
                    selectedEntry = Main.entryArray.get(e.getFirstIndex());
                    btnOpen.setEnabled(true);
                    btnDelete.setEnabled(true);
                }
            }
        }
}
