import database.DatabaseConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Esempio di esecuzione di una query
            String sql = "SELECT * FROM utenti";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // Leggi i dati dal risultato della query
                String colonna1 = resultSet.getString("id");
                String colonna2 = resultSet.getString("nome");

                // Fai qualcosa con i dati
                System.out.println(colonna1 + ", " + colonna2);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}