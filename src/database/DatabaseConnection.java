package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() {
        Connection connection = null;

        try {
            // Carica il driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Imposta le informazioni di connessione al tuo database
            String url = "jdbc:mysql://localhost:3306/esamebd";
            String username = "admin_esame";
            String password = "basiDati2024!";

            // Effettua la connessione
            connection = DriverManager.getConnection(url, username, password);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Gestionare l'eccezione in modo appropriato
        }

        return connection;
    }
}
