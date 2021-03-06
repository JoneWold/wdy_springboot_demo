package com.wdy.springboot.v1.controller;

import com.wdy.springboot.vo.request.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wgch
 * @date 2019/12/25 18:11
 */
@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping("/say")
    public String say() {
        return "Hello Spring Boot";
    }


    @GetMapping("/thyme")
    public String thyme(Map<String, Object> map) {
        // 给request域中放welcome
        map.put("welcome", "少时诵诗书");

        List<ProductDto> prods = new ArrayList<>();
        prods.add(new ProductDto("a", 100.0, 10));
        prods.add(new ProductDto("b", 200.0, 20));
        prods.add(new ProductDto("b", 300.0, 30));
        map.put("prods", prods);

        // 转到thymeleaf.html
        return "thymeleaf";
    }


    @GetMapping("/jsp")
    public String jspDemo(Map<String, Object> map) {
        map.put("name", "张三");
        // application.properties中需要配置全局参数 前缀 + 后缀
        return "jspIndex";
    }
}
