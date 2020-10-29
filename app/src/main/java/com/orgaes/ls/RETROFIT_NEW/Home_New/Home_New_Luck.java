
package com.orgaes.ls.RETROFIT_NEW.Home_New;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Home_New_Luck {

    @SerializedName("luck_id")
    @Expose
    private String luckId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("time")
    @Expose
    private String time;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Home_New_Luck() {
    }

    /**
     * 
     * @param image
     * @param luckId
     * @param name
     * @param time
     */
    public Home_New_Luck(String luckId, String name, String image, String time) {
        super();
        this.luckId = luckId;
        this.name = name;
        this.image = image;
        this.time = time;
    }

    public String getLuckId() {
        return luckId;
    }

    public void setLuckId(String luckId) {
        this.luckId = luckId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
