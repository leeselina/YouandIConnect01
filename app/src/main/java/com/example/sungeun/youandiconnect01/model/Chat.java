package com.example.sungeun.youandiconnect01.model;

import java.util.HashMap;

public class Chat {
    private boolean isUser;
    private String content;
    private String time;
    private String uid;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean getIsUser() {
        return this.isUser;
    }

    public void setIsUser(boolean isUser) {
        this.isUser = isUser;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public HashMap<String, Object> newChat() {
        HashMap<String, Object> newMap = new HashMap<>();
        newMap.put("isUser", isUser);
        newMap.put("name", name);
        newMap.put("uid", uid);
        newMap.put("time", time);
        newMap.put("content", content);
        return newMap;
    }



}
