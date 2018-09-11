package ru.otus.servlet;

import ru.otus.cache.CacheEngine;
import ru.otus.datasets.UserDataSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminServlet extends HttpServlet {

    private static final String ADMIN_PAGE_TEMPLATE = "admin.html";
    private static final String ADMIN_PAGE_TEMPLATE_UNAUTHORIZED = "unauthorized.html";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String AUTHORIZED = "Authorized";
    private static final String CONTENT_TYPE = "text/html;charset=utf-8";

    private final TemplateProcessor templateProcessor;
    private CacheEngine<Long, UserDataSet> cache;

    @SuppressWarnings("WeakerAccess")
    public AdminServlet(TemplateProcessor templateProcessor) {
        this.templateProcessor = templateProcessor;
    }

    @SuppressWarnings("WeakerAccess")
    public AdminServlet(CacheEngine<Long, UserDataSet> cache) throws IOException {
        this(new TemplateProcessor());
        this.cache = cache;
    }


    private String getPage(String login, String pass) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(LOGIN, login == null ? "" : login);
        pageVariables.put(PASSWORD, pass == null ? "" : pass);
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
            boolean authorized;
            authorized = (boolean) session.getAttribute(AUTHORIZED);
            if (authorized) {
                response.setContentType(CONTENT_TYPE);
                String page = templateProcessor.getPage(ADMIN_PAGE_TEMPLATE, cache.getCacheInfo());
                response.getWriter().println(page);
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            }
        }

        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_OK);
        String log = (String) request.getSession().getAttribute(LOGIN);
        String pass = (String) request.getSession().getAttribute(PASSWORD);
        String page = getPage(log, pass); //save to the page
        response.getWriter().println(page);


    }
}
