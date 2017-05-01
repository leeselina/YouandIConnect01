package com.example.sungeun.youandiconnect01.model;

import java.util.HashMap;

/**
 * Created by baghyeongi on 2017. 4. 7..
 */

public class Chat {
    private String mName;
    private String mMessage;
    private String mUid;

    public Chat() {
        // Needed for Firebase
    }

    public Chat(String name, String message, String uid) {
        mName = name;
        mMessage = message;
        mUid = uid;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getUid() {
        return mUid;
    }

    public void setUid(String uid) {
        mUid = uid;
    }

    public HashMap<String, Object> newChat() {
        HashMap<String, Object> newMap = new HashMap<>();
        newMap.put("name", mName);
        newMap.put("message", mMessage);
        newMap.put("uid", mUid);
        return newMap;
    }

}