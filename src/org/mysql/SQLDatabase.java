package org.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sphiinx on 6/17/2017.
 */
public class SQLDatabase {

    /**
     * Queries the specified database table for the specified column data.
     *
     * @param database_connection  The database connection.
     * @param database_table       The database table.
     * @param database_column      The database column.
     * @param database_column_data The data to search for in the column.
     * @param query_limit          The limit to query for.
     * @return The ResultSet from the query; null otherwise.
     */
    public static ResultSet queryDatabase(Connection database_connection, String database_table, String database_column, String database_column_data, int query_limit) throws SQLException {
        if (database_connection == null)
            return null;

        final Statement STATEMENT = database_connection.createStatement();
        final String QUERY = "SELECT * FROM " + database_table + " WHERE " + database_column + " = '" + database_column_data + "' LIMIT " + query_limit;
        return STATEMENT.executeQuery(QUERY);
    }


    /**
     * Queries the specified data then gets the specified column data with the specified limit.
     *
     * @param database_connection  The database connection.
     * @param database_table       The database table.
     * @param database_column      The database column.
     * @param database_column_data The data to search for in the column.
     * @param query_limit          The limit to query for.
     *
     * @return An object list containing the queried data.
     */
    public static List<Object> getQueriedData(Connection database_connection, String database_table, String database_column, String database_column_data, int query_limit) throws SQLException {
        if (database_connection == null)
            return null;

        final List<Object> QUERIED_DATA = new ArrayList<>();
        final ResultSet RESULT_SET = SQLDatabase.queryDatabase(database_connection, database_table, database_column, database_column_data, query_limit);
        while (RESULT_SET.next())
            QUERIED_DATA.add(RESULT_SET.getObject(database_column_data));

        return QUERIED_DATA;
    }


    /**
     * Queries the specified data then gets the specified column data with a limit of 1.
     *
     * @param database_connection  The database connection.
     * @param database_table       The database table.
     * @param database_column      The database column.
     * @param database_column_data The data to search for in the column.
     *
     * @return An object containing the queried data.
     */
    public static Object getQueriedData(Connection database_connection, String database_table, String database_column, String database_column_data) throws SQLException {
        if (database_connection == null)
            return null;

        return getQueriedData(database_connection, database_table, database_column, database_column_data, 1).toString();
    }

}

