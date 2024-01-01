package gui;

import database.Query;
import database.SQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ButtonListener implements ActionListener {

    private int index;
    private SQL sql;
    private JLabel descQuery;

    private JTable table;

    private JScrollPane scrollPane;

    public ButtonListener(int index, SQL sql, JLabel descQuery, JTable table, JScrollPane scrollPane) {
        this.index = index;
        this.sql = sql;
        this.descQuery = descQuery;
        this.table = table;
        this.scrollPane = scrollPane;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        try{

            Query q = new Query();

            sql.setQuery(q.getQuery(index));

            ResultSet result = sql.execQuery();

            String desc = q.getDesc(index) + "<br><br><i>" + q.getQuery(index) + "</i>";

            descQuery.setText(desc);

            String[] columnNames = q.getColumns(index);
            String[][] data = sql.getData(result, columnNames);

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            table.setModel(model);

            // Rimuovi l'header esistente
            scrollPane.setColumnHeaderView(null);

            // Imposta l'header della tabella con i nomi delle colonne
            JTableHeader header = table.getTableHeader();
            scrollPane.setColumnHeaderView(header);

            // Riaggiungi la tabella al pannello di scorrimento
            scrollPane.setViewportView(table);

        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
