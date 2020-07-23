package com.fenmiao.demo.test;


import com.fenmiao.demo.entity.Result;

import java.util.regex.Pattern;

public class CodeTest02 {
    public static void main(String[] args) {

        System.out.println(System.currentTimeMillis());
        System.out.println((long) (Math.random() * (10)));
        System.out.println(System.currentTimeMillis() + (long) (Math.random() * (30)));

        System.out.println(isBase64("chw"));
        System.out.println("99999"+"\n"+"((");
        System.out.println();
    }

    private static boolean isBase64(String str) {
        String base64Pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
        return Pattern.matches(base64Pattern, str);
    }
}
