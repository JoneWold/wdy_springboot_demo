package com.wdy.springboot.biz.tomcat;

import com.wdy.springboot.biz.yml.service.AppConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author wgch
 * @date 2020/7/10
 */
public class WdyWebApplication implements WebApplicationInitializer {


    @Override
    public void onStartup(javax.servlet.ServletContext servletCxt) throws ServletException {
        System.out.println("tomcat ----- init -----");
        // Load Spring web application configuration
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(AppConfig.class);
        ac.refresh();

        // Create and register the DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletCxt.addServlet("ds", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("*.do");
    }
}
