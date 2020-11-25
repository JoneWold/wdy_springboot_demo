package com.wdy.springboot.biz.wdy.maths;

/**
 * 【程序2】   题目：判断1-200之间有多少个素数，并输出所有素数。
 */
public class WdyMath02 {
    /**
     * 1.程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除，则表明此数不是素数，反之是素数。
     */
    public static void main(String[] args) {

        int count = 0;
        StringBuilder buffer = new StringBuilder();
        for (int i = 2; i <= 200; i++) {
            boolean flag = true;
            for (int j = 2; j < Math.sqrt(i); j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
                buffer.append(String.valueOf(i)).append(" ");
            }
        }
        System.out.println(">>>1-200之间有 " + count + " 素数<<<");
        System.out.println(buffer.toString());
    }
}
/**
 * >>>101-200之间有 23 素数<<<
 * 101 103 107 109 113 121 127 131 137 139 149 151 157 163 167 169 173 179 181 191 193 197 199
 */
