package gui;

import database.Query;
import database.SQL;
import gui.ButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfacciaGrafica extends JFrame {

    private JButton[] buttons;
    private JLabel label = new JLabel("Selezionare una query da eseguire...");
    private JTable table = new JTable();
    private JScrollPane scrollPane = new JScrollPane(table);

    private SQL sql;

    public InterfacciaGrafica(SQL sql) {

        this.sql = sql;

        // Imposta le proprietà della finestra principale
        setTitle("Progetto Daniele Calisti e Vincenzo Davide");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label.setPreferredSize(new Dimension(800, 80));
        // Crea i bottoni e aggiungili al pannello
        createButtons();

        // Crea il layout della finestra
        setLayout(new BorderLayout());
        add(createButtonPanel(), BorderLayout.NORTH);
        add(label, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void createButtons() {
        Query q = new Query();
        int nButtons = q.getNumQueries();
        buttons = new JButton[nButtons];

        for (int i = 0; i < nButtons; i++) {
            buttons[i] = new JButton("Query " + (i + 1));
            buttons[i].addActionListener(new ButtonListener(i, sql, label, table, scrollPane));
        }
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        for (JButton button : buttons) {
            buttonPanel.add(button);
        }
        return buttonPanel;
    }


}
