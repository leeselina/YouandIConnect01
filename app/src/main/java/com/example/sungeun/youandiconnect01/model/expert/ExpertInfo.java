package com.example.sungeun.youandiconnect01.model.expert;

public class ExpertInfo {
    private String brief_history;
    private String address;
    private long certification_number;
    private long resident_number;

    public ExpertInfo(String brief_history, String address, long certification_number, long resident_number) {
        this.brief_history = brief_history;
        this.address = address;
        this.certification_number = certification_number;
        this.resident_number = resident_number;
    }

    public String getBrief_history() {
        return this.brief_history;
    }

    public void setBrief_history(String brief_history) {
        this.brief_history = brief_history;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCertification_number() {
        return this.certification_number;
    }

    public void setCertification_number(long certification_number) {
        this.certification_number = certification_number;
    }

    public long getResident_number() {
        return this.resident_number;
    }

    public void setResident_number(long resident_number) {
        this.resident_number = resident_number;
    }
}
