package com.fenmiao.demo.test;

import com.fenmiao.demo.entity.User;

import java.util.*;

public class CodeTest03 {
    public static void main(String[] args) {
        test();
    }

    public static void test() {


        List<User> list = new ArrayList<>();
        User user1 = new User("127", 2);
        User user2 = new User("126", 4);
        User user3 = new User("124", 2);
        User user4 = new User("121", 2);
        User user5 = new User("123", 5);
        User user6 = new User("121", 3);

        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        list.add(user6);
        HashMap<Object, Object> map = new HashMap<>();
        list.forEach(a -> {
            for (User user : list) {
                if (a.getName().equals(user.getName())) {
                    if (a.getScore() > user.getScore()) {
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
