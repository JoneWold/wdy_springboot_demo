package com.wdy.springboot.biz.wdy.maths;

import java.util.Scanner;

/**
 * 【程序4】   题目：将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5。
 */
public class WdyMath04 {

    /**
     * 1.程序分析：对n进行分解质因数，应先找到一个最小的质数i，然后按下述步骤完成：
     * (1)如果这个质数恰等于n，则说明分解质因数的过程已经结束，打印出即可。
     * (2)如果n > i，但n能被i整除，则应打印出i的值，并用n除以i的商,作为新的正整数你,重复执行第一步。
     * (3)如果n不能被i整除，则用i+1作为i的值,重复执行第一步。
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个正整数：");
        int num = scanner.nextInt();
        System.out.print("你输入的正整数是" + num + " = ");
        fenJie(num);
    }

    private static void fenJie(int num) {
        for (int i = 2; i < num; i++) {
            if (num != i) {

            }
        }

    }

}
