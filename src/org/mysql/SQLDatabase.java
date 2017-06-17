package org.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    public static Connection getDatabase(String database_url, String database_username, String database_password) {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + database_url, database_username, database_password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}

