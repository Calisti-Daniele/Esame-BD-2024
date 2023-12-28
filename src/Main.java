import database.DatabaseConnection;
import database.Query;
import database.SQL;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try {

            Connection connection = DatabaseConnection.getConnection();

            SQL sql = new SQL("",connection);

            Query q = new Query();

            sql.setQuery(q.getQuery(3));

            ResultSet resultSet = sql.execQuery();

            sql.stampaSelect(resultSet, q.getColumns(3));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}