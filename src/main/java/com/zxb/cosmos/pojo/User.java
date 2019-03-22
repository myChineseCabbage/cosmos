package com.zxb.cosmos.pojo;


public class User {


    private String uid;
    private String userName; //登录用户名
    private String password; //用户密码
    private String userNick; //用户别名
    private String salt; //盐
    private String createTime; //创建时间
    private String state; //使用状态


    /**
     * 密码盐
     * @return
     */
    public  String getCredentialsSalt(){
        return  this.userName;
    }

    public User() {
    }

    public User(String uid, String userName, String password, String userNick, String salt, String createTime, String state) {
        this.uid = uid;
        this.userName = userName;
        this.password = password;
        this.userNick = userNick;
        this.salt = salt;
        this.createTime = createTime;
        this.state = state;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userNick='" + userNick + '\'' +
                ", salt='" + salt + '\'' +
                ", createTime='" + createTime + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
