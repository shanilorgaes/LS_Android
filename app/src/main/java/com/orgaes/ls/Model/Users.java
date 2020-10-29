package com.orgaes.ls.Model;

public class Users {

   private String id;
    private String username;
    private String userImage;
    private String userid;

    public Users(String id, String username, String userImage, String userid) {
        this.id = id;
        this.username = username;
        this.userImage = userImage;
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public Users() {
    }
}
