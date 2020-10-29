
package com.orgaes.ls.RETROFIT_NEW.Home_New;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Home_New_Ad {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("voice_url")
    @Expose
    private String voiceUrl;
    @SerializedName("ad_url")
    @Expose
    private String adUrl;
    @SerializedName("link_url")
    @Expose
    private String linkUrl;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("base_coin_count")
    @Expose
    private String baseCoinCount;
    @SerializedName("base_coin_time")
    @Expose
    private String baseCoinTime;
    @SerializedName("luck_status")
    @Expose
    private String luckStatus;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Home_New_Ad() {
    }

    /**
     * 
     * @param voiceUrl
     * @param duration
     * @param baseCoinTime
     * @param luckStatus
     * @param linkUrl
     * @param name
     * @param adUrl
     * @param id
     * @param baseCoinCount
     */
    public Home_New_Ad(String id, String voiceUrl, String adUrl, String linkUrl, String duration, String baseCoinCount, String baseCoinTime, String luckStatus, String name) {
        super();
        this.id = id;
        this.voiceUrl = voiceUrl;
        this.adUrl = adUrl;
        this.linkUrl = linkUrl;
        this.duration = duration;
        this.baseCoinCount = baseCoinCount;
        this.baseCoinTime = baseCoinTime;
        this.luckStatus = luckStatus;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getBaseCoinCount() {
        return baseCoinCount;
    }

    public void setBaseCoinCount(String baseCoinCount) {
        this.baseCoinCount = baseCoinCount;
    }

    public String getBaseCoinTime() {
        return baseCoinTime;
    }

    public void setBaseCoinTime(String baseCoinTime) {
        this.baseCoinTime = baseCoinTime;
    }

    public String getLuckStatus() {
        return luckStatus;
    }

    public void setLuckStatus(String luckStatus) {
        this.luckStatus = luckStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
