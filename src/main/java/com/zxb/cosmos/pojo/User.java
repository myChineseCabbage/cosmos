package com.zxb.cosmos.pojo;

public class User {

    private String uid;
    private String userName;

    public User() {
    }

    public User(String uid, String userName) {
        this.uid = uid;
        this.userName = userName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
