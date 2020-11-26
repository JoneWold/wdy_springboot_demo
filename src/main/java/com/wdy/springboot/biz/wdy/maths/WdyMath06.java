package com.wdy.springboot.biz.wdy.maths;

import java.util.Scanner;

/**
 * @author wgch
 * @date 2020/11/25
 */
public class WdyMath06 {

    public static void main(String[] args) {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("【程序6】   题目：输入两个正整数m和n，求其最大公约数和最小公倍数。");
        System.out.println("--------------------------------------------------------------------");
        math06();
    }


    /**
     * 1.程序分析：利用辗除法。（欧几里得算法又称辗转相除法，是指用于计算两个非负整数a，b的最大公约数。）
     * 《3139，2117》最大公约数：73
     * 3139 ÷ 2117 —————> 余 1022
     * 2117 ÷ 1022 ——————> 余 73
     * 1022 ÷ 73 ——————> 余 0
     * 《3139，2117》最小公倍数：73×43×29 = 91031
     * 空73|3139  2117
     * 占位————————————
     * 占位占位43   29
     */
    private static void math06() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个正整数：");
        int m = sc.nextInt();
        System.out.println("请再输入一个正整数：");
        int n = sc.nextInt();
        int maxDivisor = getMaxDivisor(m, n);
        System.out.println("最大公约数：" + maxDivisor);
        System.out.println("最小公倍数：" + m * n / maxDivisor);
    }

    /**
     * 获取最大公约数
     */
    private static int getMaxDivisor(int m, int n) {
        if (n > m) {
            int t = n;
            n = m;
            m = t;
        }
        while (n != 0) {
            if (m == n) {
                return m;
            } else {
                int mod = m % n;
                m = n;
                n = mod;
            }
        }
        return m;
    }


}
