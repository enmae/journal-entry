package ui;

import model.Entry;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmGUI extends JFrame implements ActionListener {
    private JLabel guiIntro;
    private JButton btnYes;
    private JButton btnNo;
    private Entry entryToRemove;

    public ConfirmGUI(Entry e) {
        super("Delete Confirmation Center");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new GridLayout(5, 2));

        guiIntro = new JLabel("Are you sure you want to delete this entry?");
        btnYes = new JButton("Yes");
        btnNo = new JButton("No");
        entryToRemove = e;

        btnYes.setActionCommand("yes");
        btnYes.addActionListener(this);
        btnNo.setActionCommand("no");
        btnNo.addActionListener(this);

        add(guiIntro);
        add(btnNo);
        add(btnYes);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("no"))
            setVisible(false);

        if (e.getActionCommand().equals("yes")) {
            Main.journal.deleteEntry(entryToRemove.title, Main.author, Main.entryMap);
            GUI.btnUpdateName.doClick();
            setVisible(false);
        }
    }

//    public static void main(String[] args) { new ConfirmGUI(new Entry("","")); }
}
