package com.example.sungeun.youandiconnect01;

import java.util.HashMap;

/**
 * Created by sungeun on 2017-04-30.
 */

public class User {
    String uid;
    int age;
    String username;

    public User(int age, String uid, String username) {
        this.age = age;
        this.uid = uid;
        this.username = username;
    }

    public HashMap<String, Object> updateUserInfo() {

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("uid", uid);
        hashMap.put("age", age);
        hashMap.put("name", username);

        return hashMap;
    }
}
