package ru.otus;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.otus.cache.CacheEngine;
import ru.otus.datasets.UserDataSet;
import ru.otus.servlet.AdminServlet;
import ru.otus.servlet.LoginServlet;


//use WebServerHelper for embedded server testing
public class WebServerHelper {

    private final static int PORT = 8090;
    private final static String PUBLIC_HTML = "public_html";

    public WebServerHelper(CacheEngine<Long, UserDataSet> cache) throws Exception {

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new AdminServlet(cache)), "/admin");
        context.addServlet(LoginServlet.class, "/login");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }
}
