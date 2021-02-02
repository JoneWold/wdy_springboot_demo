package com.wdy.springboot.wdy.maths;

import cn.hutool.core.date.DateUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * 计算一个时间到现在有多少年多少天
 */
public class DayYearUptoNow {

    public static void main(String[] args) {

        int fromYear, fromMonth, fromDay;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请依次输入年月日：");
        fromYear = scanner.nextInt();
        fromMonth = scanner.nextInt();
        fromDay = scanner.nextInt();

        while (fromYear < 1 || fromMonth < 1 || fromMonth > 12 || fromDay < 1 || fromDay > 31) {
            System.out.println("输入错误，请依次输入正确的年月日：");
            fromYear = scanner.nextInt();
            fromMonth = scanner.nextInt();
            fromDay = scanner.nextInt();
        }

        Calendar c = Calendar.getInstance();
        int toYear = DateUtil.year(new Date());
        int toMonth = DateUtil.month(new Date()) + 1;
        int toDay = DateUtil.dayOfMonth(new Date());

        //相差多少年
        int resultYear = toYear - fromYear;
        //处于相同年份
        int newYear = fromYear + resultYear;

        int from = getDay(newYear, fromMonth, fromDay);
        int to = getDay(toYear, toMonth, toDay);
        int resultDay = to - from;

        System.out.print(fromYear + "年" + fromMonth + "月" + fromDay + "日到现在相差有  " + resultYear + " 年 " + resultDay + " 天");
    }

    /**
     * 判断day是当前year的第几天
     */
    private static int getDay(int year, int month, int day) {
        int count = 0;
        int days = 0;
        if (month > 0 && month < 13) {
            for (int i = 1; i <= month; i++) {
                switch (i) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        days = 31;
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        days = 30;
                        break;
                    case 2: {
                        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                            days = 29;
                        } else {
                            days = 28;
                        }
                        break;
                    }
                    default:
                }
                count = count + days;
            }
            count = count + day;
        }
        return count;
    }

}
