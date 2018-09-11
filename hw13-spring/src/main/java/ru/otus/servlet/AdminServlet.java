package ru.otus.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.otus.cache.CacheEngine;
import ru.otus.datasets.AddressDataSet;
import ru.otus.datasets.PhoneDataSet;
import ru.otus.datasets.UserDataSet;
import ru.otus.dbservice.DBService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminServlet extends HttpServlet {

    private static final String ADMIN_PAGE_TEMPLATE = "admin.html";
    private static final String ADMIN_PAGE_TEMPLATE_UNAUTHORIZED = "unauthorized.html";
    private static final String AUTHORIZED = "Authorized";
    private static final String ADMIN_AUTHORIZED = "adminAuthorized";
    private static final String USER_DATA = "userData";
    private static final String CONTENT_TYPE = "text/html;charset=utf-8";

    private static Logger logger = LoggerFactory.getLogger(AdminServlet.class);

    @Autowired
    private TemplateProcessor templateProcessor;

    @Autowired
    private CacheEngine<Long, UserDataSet> cache;

    @Autowired
    private DBService dbService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    private String getPage(boolean adminAuthorized) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(ADMIN_AUTHORIZED, adminAuthorized);
        return templateProcessor.getPage(ADMIN_PAGE_TEMPLATE_UNAUTHORIZED, pageVariables);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute(AUTHORIZED) != null) {
            boolean authorized = (boolean) session.getAttribute(AUTHORIZED);
            if (authorized) {
                response.setContentType(CONTENT_TYPE);
                Map<String, Object> pageVariables = new HashMap<>();
                pageVariables.putAll(cache.getCacheInfo());
                pageVariables.put(USER_DATA, testDbService().toString());
                String page = templateProcessor.getPage(ADMIN_PAGE_TEMPLATE, pageVariables);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(page);
                return;
            }
        }
        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_OK);
        if (session.getAttribute(AUTHORIZED) == null) {
            session.setAttribute(AUTHORIZED, false);
        }
        boolean adminAuthorized = (boolean) session.getAttribute(AUTHORIZED);
        String page = getPage(adminAuthorized); //save to the page
        response.getWriter().println(page);
    }

    private UserDataSet testDbService() {

        AddressDataSet address1 = new AddressDataSet("Ulica Gogolya");
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
        UserDataSet userFromDB = dbService.read(user1.getId());
        logger.info("DBService read user from DB data:" + user1.toString());
        return userFromDB;

    }
}
