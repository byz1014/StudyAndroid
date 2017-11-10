package com.example.byz.studyandroid.bean;

/**
 * Created by byz on 2017/11/10.
 */

public class LeftInfo {
    private String message;
    private int resources;

    public LeftInfo(String message, int resources) {
        this.message = message;
        this.resources = resources;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        this.resources = resources;
    }
}
