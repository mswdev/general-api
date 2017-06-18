package org.web.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sphiinx on 6/17/2017.
 */
public abstract class SQLDatabaseConnector {

    protected static Connection DATABASE_CONNECTION;

    /**
     * Sets the database connection
     *
     * @param database_connection The database connection to set.
     * */
    public static void setConnection(Connection database_connection) {
        DATABASE_CONNECTION = database_connection;
    }

    /**
     * Gets the database connection.
     *
     * @return The database connection.
     **/
    public Connection getDatabaseConnection() {
        return DATABASE_CONNECTION;
    }

    /**
     * Queries the specified database table for the specified column data.
     *
     * @param database_table       The database table.
     * @param database_column      The database column.
     * @param database_column_data The data to search for in the column.
     * @param query_limit          The limit to query for.
     * @return The ResultSet from the query; null otherwise.
     */
    public static ResultSet queryDatabase(String database_table, String database_column, String database_column_data, int query_limit) throws SQLException {
        if (DATABASE_CONNECTION == null)
            return null;

        final Statement STATEMENT = DATABASE_CONNECTION.createStatement();
        final String QUERY = "SELECT * FROM " + database_table + " WHERE " + database_column + " = '" + database_column_data + "' LIMIT " + query_limit;
        return STATEMENT.executeQuery(QUERY);
    }


    /**
     * Queries the specified data then gets the specified column data with the specified limit and column index.
     *
     * @param database_table       The database table.
     * @param database_column      The database column.
     * @param database_column_data The data to search for in the column.
     * @param database_column_index The column index to get.
     * @param query_limit          The limit to query for.
     *
     * @return An object list containing the queried data.
     */
    public static List<Object> getQueriedData(String database_table, String database_column, String database_column_data, int database_column_index, int query_limit) throws SQLException {
        if (DATABASE_CONNECTION == null)
            return null;

        final List<Object> QUERIED_DATA = new ArrayList<>();
        final ResultSet RESULT_SET = SQLDatabaseConnector.queryDatabase(database_table, database_column, database_column_data, query_limit);
        if (RESULT_SET == null)
            return null;

        while (RESULT_SET.next())
            QUERIED_DATA.add(RESULT_SET.getObject(database_column_index));

        return QUERIED_DATA;
    }

}

