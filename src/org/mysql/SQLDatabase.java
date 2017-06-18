package org.mysql;

import java.sql.*;

/**
 * Created by Sphiinx on 6/17/2017.
 */
public class SQLDatabase {

    /**
     * Gets the database connection.
     *
     * @param database_url      The database URL to get the connection from.
     * @param database_username The database username.
     * @param database_password The database password.
     * @return The database connection.
     */
    public static Connection getDatabase(String database_url, String database_username, String database_password) throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + database_url, database_username, database_password);
    }

    /**
     * Queries the database table for the specified column.
     *
     * @param database_connection The database connection.
     * @param database_table The database table.
     * @param database_column The database column.
     *
     * @return The ResultSet from the query.
     * */
    public static ResultSet queryDatabase(Connection database_connection, String database_table, String database_column) throws SQLException {
        final Statement STATEMENT = database_connection.createStatement();
        final String QUERY = "SELECT " + database_table + " FROM " + database_column;
        return STATEMENT.executeQuery(QUERY);
    }

}

