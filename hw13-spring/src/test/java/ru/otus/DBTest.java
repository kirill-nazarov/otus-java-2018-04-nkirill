package ru.otus;

import org.junit.Test;
import ru.otus.cache.CacheEngine;
import ru.otus.cache.CacheEngineImpl;
import ru.otus.datasets.AddressDataSet;
import ru.otus.datasets.PhoneDataSet;
import ru.otus.datasets.UserDataSet;
import ru.otus.dbservice.DBService;
import ru.otus.dbservice.DBServiceCachedImpl;

import java.util.ArrayList;
import java.util.List;

public class DBTest {


    private CacheEngine<Long, UserDataSet> cache = new CacheEngineImpl<>(5);
    DBService dbService = new DBServiceCachedImpl(TestDBHelper.getConfiguration(), cache);


    @Test
    public void testSave() throws Exception {
        AddressDataSet address1 = new AddressDataSet("Ulica Vokzalnaya");
        List<PhoneDataSet> antonPhonesList = new ArrayList<>();
        PhoneDataSet phoneNum1 = new PhoneDataSet("7 705 555 34 87");
        PhoneDataSet phoneNum2 = new PhoneDataSet("7 703 454 54 23");
        antonPhonesList.add(phoneNum1);
        antonPhonesList.add(phoneNum2);

        UserDataSet user1 = new UserDataSet();
        user1.setName("Anton");
        user1.setAge(32);
        user1.setAddress(address1);
        user1.setPhones(antonPhonesList);

        dbService.save(user1);

        AddressDataSet address2 = new AddressDataSet("Ulica Gogolya");
        List<PhoneDataSet> ivanPhonesList = new ArrayList<>();
        PhoneDataSet phone1 = new PhoneDataSet("7 707 124 55 67");
        PhoneDataSet phone2 = new PhoneDataSet("7 708 124 54 69");
        ivanPhonesList.add(phone1);
        ivanPhonesList.add(phone2);

        UserDataSet user2 = new UserDataSet("Ivan", 25, address2, ivanPhonesList);

        dbService.save(user2);

        //Create more users

        UserDataSet user3 = new UserDataSet(user1);
        UserDataSet user4 = new UserDataSet(user1);
        UserDataSet user5 = new UserDataSet(user1);
        UserDataSet user6 = new UserDataSet(user1);
        UserDataSet user7 = new UserDataSet(user1);
        UserDataSet user8 = new UserDataSet(user1);
        UserDataSet user9 = new UserDataSet(user1);
        UserDataSet user10 = new UserDataSet(user1);

        dbService.save(user3);
        dbService.save(user4);
        dbService.save(user5);
        dbService.save(user6);
        dbService.save(user7);
        dbService.save(user8);
        dbService.save(user9);
        dbService.save(user10);

        //Read users data from db
        dbService.read(user1.getId());
        dbService.read(user2.getId());
        dbService.read(user3.getId());
        dbService.read(user4.getId());
        dbService.read(user5.getId());
        dbService.read(user6.getId());
        dbService.read(user7.getId());
        dbService.read(user8.getId());
        dbService.read(user9.getId());
        dbService.read(user10.getId());

    }

}
