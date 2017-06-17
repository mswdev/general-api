package org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Sphiinx on 6/17/2017.
 */
public class TestConnect {

    public boolean testConnect(String url, String username, String password) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}

