package com.bingoabin.date;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @Author: xubin34
 * @Date: 2022/3/30 5:10 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class DateCompare {
    public static void main(String[] args){
        String date1 = "2022-08-27 00:00:00";
        String date2 = "2022-03-30 00:00:00";
        System.out.println(dayDiff(date1,date2,"yyyy-MM-dd HH:mm:ss"));
    }


    public static long dayDiff(String date1, String date2,String format) {
        SimpleDateFormat formater = new SimpleDateFormat(format);
        long diff=0l;
        try {
            long d1 = formater.parse(date1).getTime();
            long d2 = formater.parse(date2).getTime();
            //diff=(Math.abs(d1-d2) / (1000 * 60 * 60 * 24));
            diff=(d1-d2)/(1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }

    @Test
    public void test(){
        String dateStr1 = "2022-08-27 00:00:00";

        LocalDate parse = LocalDate.now();
        LocalDate parse1 = LocalDate.parse(dateStr1.substring(0,10));
        long between = ChronoUnit.DAYS.between(parse, parse1);//4
        System.out.println(between);
    }
}
