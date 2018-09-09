package ru.otus.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.otus.cache.CacheEngine;
import ru.otus.cache.CacheEngineImpl;
import ru.otus.datasets.AddressDataSet;
import ru.otus.datasets.PhoneDataSet;
import ru.otus.datasets.UserDataSet;
import ru.otus.dbservice.DBService;
import ru.otus.dbservice.DBServiceCachedImpl;
import ru.otus.dbservice.DBServiceHibernateImpl;
import ru.otus.servlet.AdminServlet;
import ru.otus.servlet.LoginServlet;
import ru.otus.servlet.TemplateProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    private final static int PORT = 8090;
    private final static String PUBLIC_HTML = "public_html";

    public static void main(String[] args) throws Exception {

        //DBService
        CacheEngine<Long, UserDataSet> cache = new CacheEngineImpl<>(5);
        DBService dbService = new DBServiceCachedImpl(cache);

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

        UserDataSet dataSet = dbService.read(user1.getId());
        System.out.println(dataSet);


        //Web Server
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new AdminServlet(cache)), "/admin");
        context.addServlet(LoginServlet.class, "/login");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();

        dbService.shutdown();
    }
}
