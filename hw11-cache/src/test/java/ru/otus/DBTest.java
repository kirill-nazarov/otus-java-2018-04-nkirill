package ru.otus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.otus.dbservice.DBService;
import ru.otus.datasets.UsersDataSet;
import ru.otus.dbservice.DBServiceCached;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class DBTest {

    DBService dbService = new DBServiceCached(5, ConnectionHelperTestDB.getConnection());
    DBService dbService2 = new DBServiceCached(10, ConnectionHelperTestDB.getConnection());


    @Before
    public void prepareTables() throws SQLException {
        dbService.prepareTables(UsersDataSet.class);
    }

    @After
    public void cleanTables() throws SQLException {
        dbService.deleteTables(UsersDataSet.class);
    }

    @Test
    public void testSaveCachedSomeValues() throws SQLException {
        System.out.println(dbService.getMetaData());
        System.out.println("Cache size 5 elements");
        System.out.println("--------------------------------------");
        UsersDataSet user1 = new UsersDataSet("John", 22);
        UsersDataSet user2 = new UsersDataSet("Ivan", 30);
        UsersDataSet user3 = new UsersDataSet("Logan", 24);
        UsersDataSet user4 = new UsersDataSet("Roman", 36);
        UsersDataSet user5 = new UsersDataSet("Igor", 18);
        UsersDataSet user6 = new UsersDataSet("Yaroslav", 38);
        UsersDataSet user7 = new UsersDataSet("Rustam", 27);
        UsersDataSet user8 = new UsersDataSet("Olga", 25);
        UsersDataSet user9 = new UsersDataSet("Kseniya", 29);
        UsersDataSet user10 = new UsersDataSet("Vyacheslav", 23);

        dbService.save(user1);
        dbService.save(user2);
        dbService.save(user3);
        dbService.save(user4);
        dbService.save(user5);
        dbService.save(user6);
        dbService.save(user7);
        dbService.save(user8);
        dbService.save(user9);
        dbService.save(user10);

        UsersDataSet user1fromDb = dbService.load(user1.getId(), UsersDataSet.class);
        UsersDataSet user2fromDb = dbService.load(user2.getId(), UsersDataSet.class);
        UsersDataSet user3fromDb = dbService.load(user3.getId(), UsersDataSet.class);
        UsersDataSet user4fromDb = dbService.load(user4.getId(), UsersDataSet.class);
        UsersDataSet user5fromDb = dbService.load(user5.getId(), UsersDataSet.class);
        UsersDataSet user6fromDb = dbService.load(user6.getId(), UsersDataSet.class);
        UsersDataSet user7fromDb = dbService.load(user7.getId(), UsersDataSet.class);
        UsersDataSet user8fromDb = dbService.load(user8.getId(), UsersDataSet.class);
        UsersDataSet user9fromDb = dbService.load(user9.getId(), UsersDataSet.class);
        UsersDataSet user10fromDb = dbService.load(user10.getId(), UsersDataSet.class);

        System.out.println("--------------------------------------");
    }

    @Test
    public void testSaveFullCache() throws SQLException {
        System.out.println(dbService2.getMetaData());
        System.out.println("Cache size 10 elements");
        System.out.println("--------------------------------------");

        UsersDataSet user1 = new UsersDataSet("John", 22);
        UsersDataSet user2 = new UsersDataSet("Ivan", 30);
        UsersDataSet user3 = new UsersDataSet("Logan", 24);
        UsersDataSet user4 = new UsersDataSet("Roman", 36);
        UsersDataSet user5 = new UsersDataSet("Igor", 18);
        UsersDataSet user6 = new UsersDataSet("Yaroslav", 38);
        UsersDataSet user7 = new UsersDataSet("Rustam", 27);
        UsersDataSet user8 = new UsersDataSet("Olga", 25);
        UsersDataSet user9 = new UsersDataSet("Kseniya", 29);
        UsersDataSet user10 = new UsersDataSet("Vyacheslav", 23);


        dbService2.save(user1);
        dbService2.save(user2);
        dbService2.save(user3);
        dbService2.save(user4);
        dbService2.save(user5);
        dbService2.save(user6);
        dbService2.save(user7);
        dbService2.save(user8);
        dbService2.save(user9);
        dbService2.save(user10);


        UsersDataSet readUser1 = dbService2.load(user1.getId(), UsersDataSet.class);
        UsersDataSet readUser2 = dbService2.load(user2.getId(), UsersDataSet.class);
        UsersDataSet readUser3 = dbService2.load(user3.getId(), UsersDataSet.class);
        UsersDataSet readUser4 = dbService2.load(user4.getId(), UsersDataSet.class);
        UsersDataSet readUser5 = dbService2.load(user5.getId(), UsersDataSet.class);
        UsersDataSet readUser6 = dbService2.load(user6.getId(), UsersDataSet.class);
        UsersDataSet readUser7 = dbService2.load(user7.getId(), UsersDataSet.class);
        UsersDataSet readUser8 = dbService2.load(user8.getId(), UsersDataSet.class);
        UsersDataSet readUser9 = dbService2.load(user9.getId(), UsersDataSet.class);
        UsersDataSet readUser10 = dbService2.load(user10.getId(), UsersDataSet.class);

        System.out.println("--------------------------------------");

    }

}
