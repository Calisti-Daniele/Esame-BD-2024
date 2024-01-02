package gui;

import database.SQL;
import database.Query;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Alterna il colore delle righe
                if (row % 2 == 0) {
                    rendererComponent.setBackground(Color.WHITE);
                } else {
                    rendererComponent.setBackground(Color.LIGHT_GRAY);
                }

                return rendererComponent;
            }
        });

        setTitle("Progetto Daniele Calisti e Vincenzo Davide");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial",Font.PLAIN, 14));
        createButtons();

        setLayout(new BorderLayout());
        add(createButtonPanel(), BorderLayout.NORTH);
        add(label, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void createButtons() {
        Query queryManager = new Query();
        int numberOfQueries = queryManager.getNumQueries();
        buttons = new JButton[numberOfQueries];

        for (int i = 0; i < numberOfQueries; i++) {
            buttons[i] = new JButton("Query " + (i + 1));
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 14)); // Modificato il font
            buttons[i].setForeground(Color.BLUE); // Cambiato il colore del testo
            buttons[i].setBackground(Color.WHITE); // Cambiato il colore di sfondo
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
