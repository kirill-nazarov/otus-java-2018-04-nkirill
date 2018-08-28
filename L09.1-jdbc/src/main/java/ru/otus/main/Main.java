package ru.otus.main;

import ru.otus.base.DBService;
import ru.otus.base.UsersDataSet;
import ru.otus.transaction.DBServicePreparedTransactional;


public class Main {
    public static void main(String[] args) throws Exception {
        new Main().run();
    }

    private void run() throws Exception {
        try (DBService dbService = new DBServicePreparedTransactional()) {
            System.out.println(dbService.getMetaData());
            dbService.prepareTables();
            UsersDataSet user1 = new UsersDataSet("John", 22);
            UsersDataSet user2 = new UsersDataSet("Ivan", 30);
            dbService.save(user1);
            dbService.save(user2);
            UsersDataSet user1fromDb = dbService.load(user1.getId(), UsersDataSet.class);
            UsersDataSet user2fromDb = dbService.load(user2.getId(), UsersDataSet.class);
            System.out.println("User1 data loaded from DB:" + user1fromDb.toString());
            System.out.println("User2 data loaded from DB:" + user2fromDb.toString());
            dbService.deleteTables();
        }
    }
}
