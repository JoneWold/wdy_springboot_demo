package com.wdy.springboot.biz.wdy.maths;

import java.util.Scanner;

/**
 * @author wgch
 * @date 2020/11/25
 */
public class WdyMath05 {


    public static void main(String[] args) {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("【程序5】   题目：利用条件运算符的嵌套来完成此题：学习成绩=90分的同学用A表示，60-89分之间的用B表示，60分以下的用C表示。");
        System.out.println("--------------------------------------------------------------------");
        math05();
    }

    /**
     * 1.程序分析：(a>b)?a:b这是条件运算符的基本例子。
     */
    private static void math05() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入N的值：");
        int num = in.nextInt();
        String value = (num >= 90) ? "A" : (60 <= num ? "B" : "C");
        System.out.println("等级结果：" + value);
    }
}
