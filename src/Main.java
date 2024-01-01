import database.DatabaseConnection;
import database.Query;
import database.SQL;
import gui.InterfacciaGrafica;

import javax.swing.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {

        Connection connection = DatabaseConnection.getConnection();

        SQL sql = new SQL("",connection);

        SwingUtilities.invokeLater(() -> {
            InterfacciaGrafica application = new InterfacciaGrafica(sql);
            application.setVisible(true);
        });
    }
}