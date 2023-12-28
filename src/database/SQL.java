package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQL {
    private String query;
    private Connection connection;

    public SQL(String query, Connection connection) {
        this.query = query;
        this.connection = connection;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ResultSet execQuery() throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = this.connection.prepareStatement(this.query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return statement.executeQuery();
    }

    public void stampaSelect(ResultSet resultSet, String[] columns) throws SQLException {

        List<String> values = new ArrayList<>();
        int offset = columns.length;

        while (resultSet.next()) {
            for(int i=0; i<columns.length; i++)
                values.add(resultSet.getString(columns[i]));
        }

        for(int i=0; i<values.size(); i++){

            if(i % offset == 0)
                System.out.println();

            System.out.print(values.get(i) + " ");

        }
    }
}
