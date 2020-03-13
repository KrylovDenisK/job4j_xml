package ru.job4j.xslt;

import ru.job4j.xslt.entries.Entry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connect;

    public StoreSQL(Config config) {
        this.config = config;
    }

    /**
     * connect to database
     */
    public boolean init() {
        try {
            connect = DriverManager.getConnection(config.get("url"));
            try (Statement statement = connect.createStatement()) {
                statement.execute("create table if not exists entry (field integer)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connect != null;
    }

    public Connection getConnection() {
        if (Objects.isNull(connect)) {
            init();
        }
        return this.connect;
    }
    /**
     Generation of values​and their recording in the database
     */
    public void generate(int size) throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        try (Statement statement = connection.createStatement()) {
            statement.addBatch("delete from entry");
            for (int i = 1; i <= size; i++) {
                statement.addBatch("insert into entry values (" + i + ")");
            }
            statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * saving the contents of the database in list
     * @return
     */
    public List<Entry> load() {
        List<Entry> result = new ArrayList<>();
        Connection connection = getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select field from entry");
            while (resultSet.next()) {
                result.add(new Entry().setField(resultSet.getInt("field")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public void close() throws Exception {
        if (Objects.nonNull(connect)) {
            connect.close();
        }
    }
}