import database.DatabaseConnection;
import database.SQL;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try {

            Connection connection = DatabaseConnection.getConnection();

            SQL sql = new SQL("",connection);

            sql.setQuery("SELECT * FROM utenti");

            ResultSet resultSet = sql.execQuery();

            sql.stampaSelect(resultSet, new String[]{"id", "nome"});

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}