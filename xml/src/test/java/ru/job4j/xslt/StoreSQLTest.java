package ru.job4j.xslt;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.xslt.entries.Entry;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StoreSQLTest {
    private Config config = new Config();
    @Before
    public void sqlInit() {
        config.init();
    }
    @Test
    public void init() {
        try (StoreSQL storeSQL = new StoreSQL(config)) {
            storeSQL.init();
            assertThat(storeSQL.init(), is(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenGenerateCountRowsThenReturnSum() {
        int expected = 35;
        try (StoreSQL storeSQL = new StoreSQL(config)) {
            storeSQL.init();
            Connection connection = storeSQL.getConnection();
            try (Statement statement = connection.createStatement()) {
                storeSQL.generate(expected);
                ResultSet resultSet = statement.executeQuery("select count() FROM ENTRY");
                int result = 0;
                while (resultSet.next()) {
                    result = resultSet.getInt(1);
                }
                assertThat(result, is(expected));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void whenLoadThenResultTrue() {
        List<Entry> expected = new ArrayList<>();
        int size = 35;
        for (int i = 1; i <= size; i++) {
            expected.add(new Entry().setField(i));
        }
        try (StoreSQL storeSQL = new StoreSQL(config)) {
            storeSQL.init();
            storeSQL.generate(size);
            List<Entry> result = storeSQL.load();
            assertThat(result, is(expected));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}