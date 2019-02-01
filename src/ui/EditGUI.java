package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditGUI extends JFrame implements ActionListener {
    private JLabel optionLabel;
    private JLabel guiIntro;
    private JLabel emptyLabel;
    private JTextField editField;
    private String editOption;
    private String editTitle;

    public EditGUI(String title, String option) {
        super("Edit Entry Station");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new GridLayout(7, 1));

        JButton btnEdit = new JButton("Edit Entry");
        guiIntro = new JLabel("Enter the following information to edit the " + option + ".");

        if (option.toLowerCase() == "title")
            optionLabel = new JLabel("Title:");
        else if (option.toLowerCase() == "body")
            optionLabel = new JLabel("Body:");
        else
            optionLabel = new JLabel("Please select an edit option from the previous window.");

        editOption = option.toUpperCase();
        editTitle = title;
        emptyLabel = new JLabel("");
        editField = new JTextField(10);

        btnEdit.setActionCommand("edit");
        btnEdit.addActionListener(this);

        add(guiIntro);
        add(optionLabel);
        add(editField);
        add(emptyLabel);
        add(btnEdit);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("edit") && !editField.getText().equals("")) {
            Main.journal.updateEntry(editTitle, editOption, editField.getText());
            GUI.btnUpdateName.doClick();
            setVisible(false);
        }
    }

//    public static void main(String[] args) { new EditGUI("",""); }
}
