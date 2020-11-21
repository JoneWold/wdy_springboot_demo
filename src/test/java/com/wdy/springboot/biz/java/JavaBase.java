package com.wdy.springboot.biz.java;

import org.junit.jupiter.api.Test;

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


}
