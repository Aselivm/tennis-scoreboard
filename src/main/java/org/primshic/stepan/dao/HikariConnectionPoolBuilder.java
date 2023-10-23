package org.primshic.stepan.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLException;
import java.sql.Connection;

public class HikariConnectionPoolBuilder implements ConnectionBuilder {
    private static final HikariDataSource dataSource;
    private static final HikariConfig config = new HikariConfig();

    static{
        try {
           Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        config.setDriverClassName("org.h2.Driver");
        config.setJdbcUrl("jdbc:h2:mem:scoreboard");
        config.setUsername("aselium");
        config.setPassword("2816");
        config.setConnectionTimeout(50000);
        config.setMaximumPoolSize(100);
        dataSource = new HikariDataSource(config);
    }
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
