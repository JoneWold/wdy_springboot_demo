package com.wdy.springboot.biz.wdy.maths;

import java.util.Scanner;

/**
 * @author wgch
 * @date 2020/11/26
 */
public class WdyMath07 {

    public static void main(String[] args) {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("【程序7】   题目：输入一行字符，分别统计出其中英文字母、空格、数字和其它字符的个数。");
        System.out.println("--------------------------------------------------------------------");
        math07();
    }

    /**
     * 1.程序分析：利用for循环语句,if条件语句。
     */
    private static void math07() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一行字符：");
        String line = sc.nextLine();
        char[] chars = line.toCharArray();
        int letterSum = 0;
        int blankSum = 0;
        int numberSum = 0;
        int otherSum = 0;
        for (char aChar : chars) {
            if (Character.isLetter(aChar)) {
                letterSum++;
            } else if (Character.isSpaceChar(aChar)) {
                blankSum++;
            } else if (Character.isDigit(aChar)) {
                numberSum++;
            } else {
                otherSum++;
            }
        }
        System.out.println("---------------------");
        System.out.println("字母个数：" + letterSum);
        System.out.println("空格个数：" + blankSum);
        System.out.println("数字个数：" + numberSum);
        System.out.println("其他字符个数：" + otherSum);
    }


}
