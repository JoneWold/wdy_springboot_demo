package com.wdy.springboot.biz.wdy.maths;

/**
 * JAVA经典算法50题
 * 【程序1】   题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子(每隔两个月就开始生兔子)，
 * 小兔子长到第四个月后(第五个月起)每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
 */
public class WdyMath01 {

    /**
     * 1.程序分析：兔子的规律为数列1,1,2,3,5,8,13,21....
     * 从第三项开始，第三个数是前面两个数之和
     */
    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            System.out.print(func(i) + ",");
        }

    }

    /**
     * 回调函数
     */
    private static int func(int i) {
        if (i == 1 || i == 2) {
            return 1;
        } else {
            return func(i - 2) + func(i - 1);
        }
    }
}

// 1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765对
