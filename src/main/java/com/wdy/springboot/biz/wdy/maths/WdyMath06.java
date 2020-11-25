package com.wdy.springboot.biz.wdy.maths;

import java.util.Scanner;

/**
 * @author wgch
 * @date 2020/11/25
 */
public class WdyMath06 {

    /**
     * 1.程序分析：利用辗除法。
     */
    public static void main(String[] args) {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("【程序6】   题目：输入两个正整数m和n，求其最大公约数和最小公倍数。");
        System.out.println("--------------------------------------------------------------------");
        math06();
    }

    private static void math06() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个正整数：");
        int m = sc.nextInt();
        System.out.println("请再输入一个正整数：");
        int n = sc.nextInt();


    }

}
