package ui;

import model.Entry;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenGUI extends JFrame implements ActionListener {
    private JLabel guiIntro;
    private JLabel entryTitle;
    private JLabel entryBody;
    private JLabel entryDate;
    private JLabel entryAuthor;
    private JLabel emptyLabel;
    private JButton btnEdit;
    private JButton btnCancel;
    private JRadioButton btnTitle;
    private JRadioButton btnBody;
    private String title;
    private String option;
    private ButtonGroup group;

    public OpenGUI(Entry e) {
        super("Selected Entry");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new GridLayout(10, 1));

        emptyLabel = new JLabel("");
        guiIntro = new JLabel("These are the details of the selected entry:");
        btnEdit = new JButton("Edit");
        btnCancel = new JButton("Cancel");
        entryTitle = new JLabel("Title: " + e.title);
        entryDate = new JLabel("Created Date: " + e.getDate());
        entryBody = new JLabel("Body: " + e.getBody());
        entryAuthor = new JLabel("Author: " + Main.author.name);

        btnTitle = new JRadioButton("Edit Title");
        btnTitle.setActionCommand("editTitle");
        btnTitle.setSelected(true);
        btnBody = new JRadioButton("Edit Body");
        btnBody.setActionCommand("editBody");
        option = "title";
        title = e.title;

        group = new ButtonGroup();
        group.add(btnTitle);
        group.add(btnBody);

        btnEdit.setActionCommand("edit");
        btnEdit.addActionListener(this);
        btnTitle.addActionListener(this);
        btnBody.addActionListener(this);
        btnCancel.setActionCommand("cancel");
        btnCancel.addActionListener(this);

        add(guiIntro);
        add(entryTitle);
        add(entryDate);
        add(entryBody);
        add(entryAuthor);
        add(emptyLabel);
        add(btnTitle);
        add(btnBody);
        add(btnEdit);
        add(btnCancel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("edit")) {
           new EditGUI(title, option);

           if (option == "title")
               entryTitle.setText("Title: " + Main.journal.findEntry(title).title);
           else if (option == "body")
               entryBody.setText("Body: " + Main.journal.findEntry(title).getBody());

           setVisible(false);
        }

        if (e.getActionCommand().equals("editTitle"))
            option = "title";

        if (e.getActionCommand().equals("editBody"))
            option = "body";

        if(e.getActionCommand().equals("cancel"))
            setVisible(false);
    }

//    public static void main(String[] args) { new OpenGUI(new Entry("","")); }
}
