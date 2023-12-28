import database.DatabaseConnection;
import database.SQL;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try {

            Connection connection = DatabaseConnection.getConnection();

            SQL sql = new SQL("",connection);

            sql.setQuery("Select nome, quantita FROM farmaci WHERE (quantita > 2 OR prezzoVendita > 20) AND data_scadenza > CURDATE()");

            ResultSet resultSet = sql.execQuery();

            sql.stampaSelect(resultSet, new String[]{"nome", "quantita"});

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}