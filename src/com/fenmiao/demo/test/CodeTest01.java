package com.fenmiao.demo.test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class CodeTest01 {
    public static void main(String[] args) {
        System.out.println(String.format("%s停车场","分秒"));
        double param = 19.99;
        System.out.println(new DecimalFormat("0.00").format((float) param /100));
        System.out.println(new BigDecimal(param).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue());
        System.out.println(String.format("%s:???","2"));
        String[] s = "dkjf-lskf-j".split("-");
        System.out.println(s[0]);
        System.out.println(LocalDateTime.now()/*.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))*/);

    }
}
