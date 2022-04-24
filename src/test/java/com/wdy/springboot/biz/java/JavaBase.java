package com.wdy.springboot.biz.java;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Scanner;

class JavaBase {

    @Test
    void num() {
        System.out.println(21.0 / 0);  //输出结果 Infinity（无穷大）
        System.out.println(21 / 0);     //除数为零，编译错误
        char c = 'a';
        byte b = (byte) c;
        System.out.println(b);

    }

    @Test
    void scanner() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextInt();
    }


    @Test
    void test() {
        DateTime time = DateUtil.parse("2021.03", "yyyy.MM");
        Date newDate = DateUtil.offset(time, DateField.YEAR, -2);
        String a0192C = DateUtil.format(newDate, "yyyy.MM");
        System.out.println(a0192C);
        //常用偏移 DateTime newDate2 = DateUtil.offsetDay(date, 3); Assert.assertEquals("2017-03-04 22:33:23", newDate2.toString());
        //常用偏移 DateTime newDate3 = DateUtil.offsetHour(date, -3); Assert.assertEquals("2017-03-01 19:33:23", newDate3.toString());

    }

}
