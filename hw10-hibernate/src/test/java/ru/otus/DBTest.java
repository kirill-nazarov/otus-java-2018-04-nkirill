package ru.otus;

import org.junit.Test;
import ru.otus.datasets.AddressDataSet;
import ru.otus.datasets.PhoneDataSet;
import ru.otus.datasets.UserDataSet;
import ru.otus.dbservice.DBService;
import ru.otus.dbservice.DBServiceHibernateImpl;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class DBTest {

    DBService dbService = new DBServiceHibernateImpl();


    @Test
    public void testSave() {
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

        UserDataSet user1fromDb = dbService.readByName(user1.getName());
        assertEquals(user1, user1fromDb);
        assertTrue(user1.getName().equals(user1fromDb.getName()));
        assertTrue(user1.getAge().equals(user1fromDb.getAge()));
        assertTrue(user1.getAddress().equals(user1fromDb.getAddress()));
        List<PhoneDataSet> phones = user1.getPhones();
        List<PhoneDataSet> phonesFromDB = user1fromDb.getPhones();
        assertTrue(phones.size() == phonesFromDB.size());
        for (int i = 0; i < phones.size(); i++) {
            assertTrue(phones.get(i).equals(phonesFromDB.get(i)));
        }


    }

}
