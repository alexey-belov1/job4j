package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Set;

public class PSQL implements AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(PSQL.class.getName());

    private Connection connection;

    public PSQL(Connection connection) {
        this.connection = connection;
    }

    public void addInBase(Set<Vacancy> vacancies) {
        try {
            PreparedStatement st = connection.prepareStatement("insert into vacancy(name, text, link) values(?,?,?)");
            for (Vacancy v : vacancies) {
                st.setString(1, v.getName());
                st.setString(2, v.getText());
                st.setString(3, v.getLink());
                st.addBatch();
            }
            st.executeBatch();
            LOG.info("Data added to the database.");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public LocalDateTime getLastDate() {
        LocalDateTime lastDate = null;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select date from log");
            while (rs.next()) {
                lastDate = rs.getTimestamp("date").toLocalDateTime();
            }
            if (lastDate == null) {
                lastDate = LocalDateTime.of(LocalDateTime.now().getYear(), 1, 1, 0, 0, 0);
            }
            LOG.info(String.format("Last launch date: %s", lastDate.toString()));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return lastDate;
    }

    public void saveDate() {
        try {
            PreparedStatement st = connection.prepareStatement("insert into log(date) values(?)");
            LocalDateTime curDate = LocalDateTime.now();
            st.setTimestamp(1, Timestamp.valueOf(curDate));
            st.execute();
            LOG.info(String.format("Launch date saved: %s", curDate.toString()));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
                LOG.info("Close database connection.");
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }
}
