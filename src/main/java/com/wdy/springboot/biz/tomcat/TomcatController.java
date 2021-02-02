package com.wdy.springboot.biz.tomcat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wgch
 * @date 2020/7/10
 */
@Controller
public class TomcatController {

    @RequestMapping("/index.do")
    public void index() {
        System.out.println("index");
    }
}
