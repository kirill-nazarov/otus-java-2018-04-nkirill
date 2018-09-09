package ru.otus.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class LoginServlet extends HttpServlet {

    public static final String LOGIN_PARAMETER_NAME = "login";
    public static final String PASSWORD_PARAMETER_NAME = "password";
    private static final String LOGIN_VARIABLE_NAME = "login";
    private static final String PASSWORD_VARIABLE_NAME = "password";
    private static final String ADMIN_AUTHORIZED_VARIABLE_NAME = "adminAuthorized";
    private static final String LOGIN_PAGE_TEMPLATE = "login.html";

    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "pazzword";

    private final TemplateProcessor templateProcessor;


    @SuppressWarnings("WeakerAccess")
    public LoginServlet(TemplateProcessor templateProcessor) {
        this.templateProcessor = templateProcessor;
    }

    @SuppressWarnings("WeakerAccess")
    public LoginServlet() throws IOException {
        this(new TemplateProcessor());
    }

    private String getPage(String login, String pass, boolean adminAuthorized) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(LOGIN_VARIABLE_NAME, login == null ? "" : login);
        pageVariables.put(PASSWORD_VARIABLE_NAME, pass == null ? "" : pass);
        pageVariables.put(ADMIN_AUTHORIZED_VARIABLE_NAME, adminAuthorized);

        return templateProcessor.getPage(LOGIN_PAGE_TEMPLATE, pageVariables);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute("Authorized", false);

        String requestLogin = request.getParameter(LOGIN_PARAMETER_NAME);
        String requestPassword = request.getParameter(PASSWORD_PARAMETER_NAME);

        if (requestLogin != null) {
            request.getSession().setAttribute("login", requestLogin);
        }

        if (requestPassword != null) {
            request.getSession().setAttribute("password", requestPassword);
        }

        if (requestLogin != null && requestPassword != null) {
            if (requestLogin.equals(ADMIN_LOGIN) && requestPassword.equals(ADMIN_PASSWORD)) {
                this.authorizeAdmin(request);
            }
        }

        setOK(response);
        String log = (String) request.getSession().getAttribute("login");
        String pass = (String) request.getSession().getAttribute("password");
        boolean adminAuthorized = (boolean) session.getAttribute("Authorized");
        String page = getPage(log, pass, adminAuthorized); //save to the page
        response.getWriter().println(page);
    }


    private void authorizeAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("Authorized", true);
    }

    private void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
