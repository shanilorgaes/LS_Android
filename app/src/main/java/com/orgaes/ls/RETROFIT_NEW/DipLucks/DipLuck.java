
package com.orgaes.ls.RETROFIT_NEW.DipLucks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DipLuck {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("enroll_type")
    @Expose
    private String enrollType;
    @SerializedName("enroll_fee")
    @Expose
    private String enrollFee;
    @SerializedName("draw_date")
    @Expose
    private String drawDate;
    @SerializedName("draw_time")
    @Expose
    private String drawTime;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DipLuck() {
    }

    /**
     * 
     * @param enrollType
     * @param image
     * @param enrollFee
     * @param drawTime
     * @param name
     * @param description
     * @param id
     * @param type
     * @param drawDate
     */
    public DipLuck(String id, String name, String image, String description, String type, String enrollType, String enrollFee, String drawDate, String drawTime) {
        super();
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.type = type;
        this.enrollType = enrollType;
        this.enrollFee = enrollFee;
        this.drawDate = drawDate;
        this.drawTime = drawTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEnrollType() {
        return enrollType;
    }

    public void setEnrollType(String enrollType) {
        this.enrollType = enrollType;
    }

    public String getEnrollFee() {
        return enrollFee;
    }

    public void setEnrollFee(String enrollFee) {
        this.enrollFee = enrollFee;
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

}
