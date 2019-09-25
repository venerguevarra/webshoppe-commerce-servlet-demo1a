package com.webshoppe.ecommerce.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionManager {

    public JdbcConnectionManager() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        try {
            final Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/webshoppe", "user", "pass");
            return connection;
        } catch (SQLException e) {
            throw e;
        }
    }

}
