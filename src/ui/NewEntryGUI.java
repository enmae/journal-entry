package ui;

import exceptions.EmptyTextException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewEntryGUI extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JLabel bodyLabel;
    private JLabel guiIntro;
    private JLabel emptyLabel;
    private JTextField titleField;
    private JTextField bodyField;
    private JButton btnCancel;

    public NewEntryGUI() {
        super("New Entry Creation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(new GridLayout(8,1));

        JButton btnCreate = new JButton("Create Entry");
        btnCancel = new JButton("Cancel");
        guiIntro = new JLabel("Enter the following information to create a new entry.");
        titleLabel = new JLabel("Title:" );
        bodyLabel = new JLabel("Body:");
        emptyLabel = new JLabel("");
        titleField = new JTextField(10);
        bodyField = new JTextField(10);

        btnCreate.setActionCommand("newEntry");
        btnCreate.addActionListener(this);
        btnCancel.setActionCommand("cancel");
        btnCancel.addActionListener(this);

        add(guiIntro);
        add(titleLabel);
        add(titleField);
        add(bodyLabel);
        add(bodyField);
        add(emptyLabel);
        add(btnCreate);
        add(btnCancel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("newEntry") && !titleField.getText().equals("") && !bodyField.getText().equals("")) {
            try {
                Main.journal.createNew(titleField.getText(), bodyField.getText(), Main.entryMap, Main.author);
                int currentEntries = Main.author.getEntries() + 1;
                Main.author.setEntries(currentEntries);
                GUI.btnUpdateName.doClick();
                setVisible(false);
            } catch (EmptyTextException e1) {
                System.out.println("Error: Empty text found.");
            }
        }

        if(e.getActionCommand().equals("cancel"))
            setVisible(false);
    }

//    public static void main(String[] args) {
//        new NewEntryGUI();
//    }
}
