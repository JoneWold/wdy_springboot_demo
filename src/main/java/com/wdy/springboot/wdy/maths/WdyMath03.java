package com.wdy.springboot.wdy.maths;

/**
 * 【程序3】   题目：打印出所有的 水仙花数 ，所谓 水仙花数 是指一个三位数，其各位数字立方和等于该数本身。
 * 例如：153是一个 水仙花数 ，因为153=1的三次方＋5的三次方＋3的三次方。
 */
public class WdyMath03 {
    /**
     * 1.程序分析：利用for循环控制100-999个数，每个数分解出个位，十位，百位。
     */
    public static void main(String[] args) {

        System.out.print("所有的水仙花数：");
        for (int i = 100; i < 999; i++) {
            int ge = i % 10;
            //int shi = (i%100)/10;
            int shi = i / 10 % 10;
            int bai = i / 100;
            if (i == ge * ge * ge + shi * shi * shi + bai * bai * bai) {
                System.out.print(i + " ");
            }
        }
    }
}

// 所有的水仙花数：153 370 371 407

