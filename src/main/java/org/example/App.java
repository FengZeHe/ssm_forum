package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.example.AppConfig;
import org.example.config.WebConfig;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class App {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        int port = 8080;
        tomcat.setBaseDir(".tomcat");

        Connector connector = new Connector();
        connector.setPort(port);
        tomcat.getService().addConnector(connector);

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class);
        rootContext.refresh();

        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.setParent(rootContext);
        webContext.register(WebConfig.class);

        Context context = tomcat.addContext("", null);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);

        // ✅ 只 add 一次，拿到wrapper设置启动加载
        Wrapper wrapper = Tomcat.addServlet(context, "dispatcher", dispatcherServlet);
        wrapper.setLoadOnStartup(1);

        // 只做映射，不要再addServlet
        context.addServletMappingDecoded("/", "dispatcher");

        tomcat.start();
        System.out.println("✅ Tomcat Started on 8080");
        System.out.println("👉 http://127.0.0.1:8080/api/ping");
        tomcat.getServer().await();
    }
}
