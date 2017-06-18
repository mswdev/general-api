package org.mysql;

import java.sql.*;

/**
 * Created by Sphiinx on 6/17/2017.
 */
public class SQLDatabase {

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

