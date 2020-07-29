package com.fenmiao.demo.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) throws ParseException {
        HashMap map = new HashMap();
        map.put("name","score");
        System.out.println(map.keySet());

        timeCal();
    }

    public static void timeCal() throws ParseException {

        Date past = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 00:00:00");
        Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-07-23 13:00:00");

        System.out.println(now.getTime()-past.getTime());

    }
}
