package ru.otus.servlet;

import ru.otus.cache.CacheEngine;
import ru.otus.cache.CacheEngineImpl;
import ru.otus.datasets.UserDataSet;

import javax.servlet.ServletException;
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

    private static final String LOGIN_VARIABLE_NAME = "login";
    private static final String PASSWORD_VARIABLE_NAME = "password";

    private final TemplateProcessor templateProcessor;
    private CacheEngine<Long, UserDataSet> cache = new CacheEngineImpl<>(5);

    @SuppressWarnings("WeakerAccess")
    public AdminServlet() throws IOException {
        this(new TemplateProcessor());
    }

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
        pageVariables.put(LOGIN_VARIABLE_NAME, login == null ? "" : login);
        pageVariables.put(PASSWORD_VARIABLE_NAME, pass == null ? "" : pass);
        return templateProcessor.getPage(ADMIN_PAGE_TEMPLATE_UNAUTHORIZED, pageVariables);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(true);
        if (session.getAttribute("Authorized") != null) {
            boolean authorized;
            authorized = (boolean) session.getAttribute("Authorized");
            if (authorized) {
                response.setContentType("text/html;charset=utf-8");
                String page = templateProcessor.getPage(ADMIN_PAGE_TEMPLATE, cache.getCacheInfo());
                response.getWriter().println(page);
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            }
        }

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        String log = (String) request.getSession().getAttribute("login");
        String pass = (String) request.getSession().getAttribute("password");
        String page = getPage(log, pass); //save to the page
        response.getWriter().println(page);


    }
}
