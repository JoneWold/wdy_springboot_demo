package com.wdy.springboot.biz.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 * 自定义springboot内嵌tomcat
 *
 * @author wgch
 * @date 2020/7/10
 */
public class TomcatServer {


    public static void run() {
        // 源于springboot源码
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(9090);

        tomcat.addWebapp("/tom", "d:\\zl\\");
        try {
            tomcat.start();
            // 让tomcat 阻塞，等待用户的请求
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

    }
}
