package ru.job4j.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.tracker.TrackerSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connection;
    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class.getName());

    public StoreSQL(Config config) {
        this.config = config;
    }

    public void init() {
        try {
            this.connection = DriverManager.getConnection(config.get("url"));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void createTableEntry() {
        String sqlCreate = "create table if not exists entry(id integer);";
        String sqlDelete = "delete from entry;";
        try {
            Statement st = this.connection.createStatement();
            st.execute(sqlCreate);
            st.execute(sqlDelete);
            connection.setAutoCommit(false);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void generate(int size) {
        try {
            PreparedStatement st = connection.prepareStatement("insert into entry(id) values(?)");
            for (int i = 1; i <= size; i++) {
                st.setInt(1, i);
                st.addBatch();
            }
            st.executeBatch();
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (Exception e1) {
                    LOG.error(e1.getMessage(), e1);
                }
            }
            LOG.error(e.getMessage(), e);
        }
    }

    public List<Entry> load() {
        List<Entry> list = new ArrayList<Entry>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select id from entry");
            while (rs.next()) {
                list.add(new Entry(rs.getInt("id")));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return list;
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
