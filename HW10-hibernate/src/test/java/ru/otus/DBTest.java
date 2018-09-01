package ru.otus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.otus.base.DBService;
import ru.otus.base.UsersDataSet;
import ru.otus.connection.DBServicePreparedTransactional;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class DBTest {

    DBService dbService = new DBServicePreparedTransactional();


    @Before
    public void prepareTables() throws SQLException {
        dbService.prepareTables(UsersDataSet.class);
    }

    @After
    public void cleanTables() throws SQLException {
        dbService.deleteTables(UsersDataSet.class);
    }

    @Test
    public void testSave() throws SQLException {
        UsersDataSet user1 = new UsersDataSet("John", 22);
        UsersDataSet user2 = new UsersDataSet("Ivan", 30);
        dbService.save(user1);
        UsersDataSet user1fromDb = dbService.load(user1.getId(), UsersDataSet.class);
        assertEquals(user1, user1fromDb);
        assertTrue(user1.equals(user1fromDb));
        assertFalse(user2.equals(user1fromDb));

    }

}
