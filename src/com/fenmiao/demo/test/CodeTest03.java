package com.fenmiao.demo.test;

import com.fenmiao.demo.entity.User;

import java.util.*;

public class CodeTest03 {
    public static void main(String[] args) {
        test();
    }

    public static void test() {


        List<User> list = new ArrayList<>();
        HashMap<Object, Object> map = new HashMap<>();
        list.forEach(a -> {
            for (int i = 0; i < list.size(); i++) {
                if (a.getName().equals(list.get(i).getName())) {
                    if (a.getScore() > list.get(i).getScore()) {
                        map.put(a.getName(), a.getScore());
                    }
                }
            }
            map.computeIfAbsent(a.getName(), k -> a.getScore());
        });

        System.out.println(map.keySet());
        System.out.println(map.values());
    }
}
