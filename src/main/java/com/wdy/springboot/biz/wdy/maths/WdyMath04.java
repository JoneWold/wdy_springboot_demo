package com.wdy.springboot.biz.wdy.maths;

import java.util.Scanner;

/**
 * @author wgch
 * @date 2020/11/25
 */
public class WdyMath04 {

    /**
     * 1.程序分析：对n进行分解质因数，应先找到一个最小的质数i，然后按下述步骤完成：
     * (1)如果这个质数恰等于n，则说明分解质因数的过程已经结束，打印出即可。
     * (2)如果n > i，但n能被i整除，则应打印出i的值，并用n除以i的商,作为新的正整数你,重复执行第一步。
     * (3)如果n不能被i整除，则用i+1作为i的值,重复执行第一步。
     */
    public static void main(String[] args) {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("【程序4】   题目：将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5。");
        System.out.println("--------------------------------------------------------------------");
        math04();
    }

    private static void math04() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个正整数：");
        long num = scanner.nextLong();
        System.out.print("分解质因数：" + num + " = ");
        fenJie(num);
    }


    private static void fenJie(Long num) {
        // 2 3 5 7...
        for (int i = 2; i <= num; i++) {
            if (num % i == 0) {
                System.out.print(i);
                if (num != i) {
                    System.out.print("*");
                }
                fenJie(num / i);
            }
        }
        System.exit(0);
    }

}
