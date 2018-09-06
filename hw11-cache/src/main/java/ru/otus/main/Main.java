package ru.otus.main;

import ru.otus.datasets.UsersDataSet;
import ru.otus.dbservice.DBService;
import ru.otus.dbservice.DBServiceCached;


public class Main {
    public static void main(String[] args) throws Exception {
        new Main().run();
    }

    private void run() throws Exception {
        try (DBService dbService = new DBServiceCached()) {
            System.out.println(dbService.getMetaData());
            dbService.prepareTables(UsersDataSet.class);
            UsersDataSet user1 = new UsersDataSet("John", 22);
            UsersDataSet user2 = new UsersDataSet("Ivan", 30);
            UsersDataSet user3 = new UsersDataSet("Anton", 35);
            dbService.save(user1);
            dbService.save(user2);
            dbService.save(user3);
            UsersDataSet user1fromDb = dbService.load(user1.getId(), UsersDataSet.class);
            UsersDataSet user2fromDb = dbService.load(user2.getId(), UsersDataSet.class);
            UsersDataSet user3fromDb = dbService.load(user3.getId(), UsersDataSet.class);
            System.out.println("User1 data loaded from DB:" + user1fromDb.toString());
            System.out.println("User2 data loaded from DB:" + user2fromDb.toString());
            System.out.println("User3 data loaded from DB:" + user3fromDb.toString());
            dbService.deleteTables(UsersDataSet.class);
        }
    }
}
