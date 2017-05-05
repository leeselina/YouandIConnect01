package com.example.sungeun.youandiconnect01.model.expert;

import java.util.HashMap;

public class Expert {
    private String uid;
    private String name;
    private String description;
    private ExpertInfo info;

    public Expert(String uid, String name, String description, ExpertInfo info) {
        this.uid = uid;
        this.name = name;
        this.description = description;
        this.info = info;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExpertInfo getInfo() {
        return this.info;
    }

    public void setInfo(ExpertInfo info) {
        this.info = info;
    }

    public HashMap<String, Object> newExpert(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("uid", uid);
        hashMap.put("name", name);
        hashMap.put("description", description);
        hashMap.put("info", info);

        return hashMap;
    }
}
