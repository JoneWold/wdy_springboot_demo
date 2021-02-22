package com.wdy.springboot.wdy.maths;

import java.util.Scanner;

/**
 * @author wgch
 * @date 2020/11/26
 */
public class WdyMath08 {


    public static void main(String[] args) {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("【程序8】   题目：求s = a + aa + aaa + aaaa + aa...a的值，其中a是一个数字。例如2 + 22 + 222 + 2222 + 22222(此时共有5个数相加)，几个数相加有键盘控制。");
        System.out.println("--------------------------------------------------------------------");
        math08();
    }

    /**
     * 1.程序分析：关键是计算出每一项的值。
     * 可以利用： 1*10+1=11    11*10+1=111  111*10+1=1111
     */
    private static void math08() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入a的值：");
        int a = scanner.nextInt();
        System.out.println("请输入共几个数相加：");
        int index = scanner.nextInt();
        int sum = 0;
        int temp = 0;
        for (int i = 1; i <= index; i++) {
            temp += a;
            a = a * 10;
            sum += temp;
            System.out.print(temp + " ");
        }
        System.err.println("总和：" + sum);
    }


}
