package ru.otus.main;

import ru.otus.datasets.AddressDataSet;
import ru.otus.datasets.PhoneDataSet;
import ru.otus.datasets.UserDataSet;
import ru.otus.dbservice.DBService;
import ru.otus.dbservice.DBServiceHibernateImpl;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        DBService dbService = new DBServiceHibernateImpl();

        String status = dbService.getLocalStatus();
        System.out.println("Status: " + status);

        AddressDataSet address1 = new AddressDataSet("Ulica  Novoselov");
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

        System.out.println(user1);

        dbService.save(user1);

        AddressDataSet address2 = new AddressDataSet("Ulica Gogolya");
        List<PhoneDataSet> ivanPhonesList = new ArrayList<>();
        PhoneDataSet phone1 = new PhoneDataSet("7 707 124 55 67");
        PhoneDataSet phone2 = new PhoneDataSet("7 708 124 54 69");
        ivanPhonesList.add(phone1);
        ivanPhonesList.add(phone2);

        UserDataSet user2 = new UserDataSet("Ivan", 25, address2, ivanPhonesList);

        dbService.save(user2);

        UserDataSet dataSet = dbService.read(1);
        System.out.println(dataSet);

        dataSet = dbService.readByName("Ivan");
        System.out.println(dataSet);

        List<UserDataSet> dataSets = dbService.readAll();
        for (UserDataSet userDataSet : dataSets) {
            System.out.println(userDataSet);
        }

        dbService.shutdown();
    }
}
