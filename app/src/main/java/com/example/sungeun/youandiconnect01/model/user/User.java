package com.example.sungeun.youandiconnect01.model.user;

import java.util.HashMap;
import java.util.List;

public class User {
    private String uid;
    private String name;
    private List<List<String>> coach;
    private UserInfo info;

    public User(UserInfo info, String name, String uid) {
        this.info = info;
        this.name = name;
        this.uid = uid;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<List<String>> getCoach() {
        return this.coach;
    }

    public void setCoach(List<List<String>> coach) {
        this.coach = coach;
    }

    public UserInfo getInfo() {
        return this.info;
    }

    public void setInfo(UserInfo info) {
        this.info = info;
    }

    public HashMap<String, Object> newUser() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("uid", uid);
        hashMap.put("name", name);
        hashMap.put("info", info);

        return hashMap;
    }
}
