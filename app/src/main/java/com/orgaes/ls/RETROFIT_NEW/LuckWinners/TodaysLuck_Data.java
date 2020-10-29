
package com.orgaes.ls.RETROFIT_NEW.LuckWinners;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodaysLuck_Data {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("ad_id")
    @Expose
    private String adId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("type")
    @Expose
    private String type;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TodaysLuck_Data() {
    }

    /**
     * 
     * @param image
     * @param adId
     * @param id
     * @param type
     */
    public TodaysLuck_Data(String id, String adId, String image, String type) {
        super();
        this.id = id;
        this.adId = adId;
        this.image = image;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
