package com.example.sungeun.youandiconnect01.model.user;

public class UserInfo {
    private String date;
    private String address;
    private String method;
    private int weight;
    private int recent;
    private int height;

    public UserInfo(String date, String address, String method, int weight, int recent, int height) {
        this.date = date;
        this.address = address;
        this.method = method;
        this.weight = weight;
        this.recent = recent;
        this.height = height;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getRecent() {
        return this.recent;
    }

    public void setRecent(int recent) {
        this.recent = recent;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
