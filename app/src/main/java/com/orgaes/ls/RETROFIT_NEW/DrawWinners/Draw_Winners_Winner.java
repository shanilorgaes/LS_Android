
package com.orgaes.ls.RETROFIT_NEW.DrawWinners;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Draw_Winners_Winner {

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("photo_url")
    @Expose
    private String photoUrl;
    @SerializedName("draw_date")
    @Expose
    private String drawDate;
    @SerializedName("draw_time")
    @Expose
    private String drawTime;
    @SerializedName("enroll_fee")
    @Expose
    private String enrollFee;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("rank")
    @Expose
    private String rank;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Draw_Winners_Winner() {
    }

    /**
     * 
     * @param image
     * @param photoUrl
     * @param enrollFee
     * @param drawTime
     * @param name
     * @param rank
     * @param type
     * @param drawDate
     */
    public Draw_Winners_Winner(String image, String name, String photoUrl, String drawDate, String drawTime, String enrollFee, String type, String rank) {
        super();
        this.image = image;
        this.name = name;
        this.photoUrl = photoUrl;
        this.drawDate = drawDate;
        this.drawTime = drawTime;
        this.enrollFee = enrollFee;
        this.type = type;
        this.rank = rank;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(String drawDate) {
        this.drawDate = drawDate;
    }

    public String getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(String drawTime) {
        this.drawTime = drawTime;
    }

    public String getEnrollFee() {
        return enrollFee;
    }

    public void setEnrollFee(String enrollFee) {
        this.enrollFee = enrollFee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

}
