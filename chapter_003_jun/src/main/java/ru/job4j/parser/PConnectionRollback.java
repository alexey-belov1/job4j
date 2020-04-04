package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PConnectionRollback {
    private static final Logger LOG = LogManager.getLogger(PConnectionRollback.class.getName());

    private Connection init(PConfig config) {
        Connection connection = null;
        try {
            Class.forName(config.get("jdbc.driver"));
            connection = DriverManager.getConnection(
                    config.get("jdbc.url"),
                    config.get("jdbc.username"),
                    config.get("jdbc.password")
            );
            LOG.info("Connection completed successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            LOG.error(e.getMessage(), e);
        }

        return connection;
    }

    public static Connection create(PConfig config, boolean setRollback) {
        Connection connection = new PConnectionRollback().init(config);
        try {
            connection.setAutoCommit(!setRollback);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

        return (Connection) Proxy.newProxyInstance(
                PConnectionRollback.class.getClassLoader(),
                new Class[] {
                        Connection.class
                },
                (proxy, method, args) -> {
                    Object rsl = null;
                    if ("close".equals(method.getName())) {
                        if (setRollback) {
                            connection.rollback();
                        }
                        connection.close();
                    } else {
                        rsl = method.invoke(connection, args);
                    }
                    return rsl;
                }
        );
    }
}