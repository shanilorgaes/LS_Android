
package com.orgaes.ls.RETROFIT_NEW.Home_Api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdsRandom {

    @SerializedName("ads_id")
    @Expose
    private String adsId;
    @SerializedName("ads_type")
    @Expose
    private String adsType;
    @SerializedName("ads_file")
    @Expose
    private String adsFile;
    @SerializedName("ads_view_time")
    @Expose
    private String adsViewTime;
    @SerializedName("ads_voice")
    @Expose
    private String adsVoice;
    @SerializedName("ads_voice_file")
    @Expose
    private String adsVoiceFile;
    @SerializedName("ads_link_image")
    @Expose
    private String adsLinkImage;
    @SerializedName("ads_coin_collection_time")
    @Expose
    private String adsCoinCollectionTime;
    @SerializedName("ads_coin_collection_no")
    @Expose
    private String adsCoinCollectionNo;
    @SerializedName("ads_luck_status")
    @Expose
    private String adsLuckStatus;
    @SerializedName("ads_default_status")
    @Expose
    private String adsDefaultStatus;
    @SerializedName("lTimer")
    @Expose
    private List<LTimer> lTimer = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AdsRandom() {
    }

    /**
     * 
     * @param adsViewTime
     * @param lTimer
     * @param adsType
     * @param adsLinkImage
     * @param adsFile
     * @param adsCoinCollectionTime
     * @param adsVoice
     * @param adsId
     * @param adsDefaultStatus
     * @param adsCoinCollectionNo
     * @param adsVoiceFile
     * @param adsLuckStatus
     */
    public AdsRandom(String adsId, String adsType, String adsFile, String adsViewTime, String adsVoice, String adsVoiceFile, String adsLinkImage, String adsCoinCollectionTime, String adsCoinCollectionNo, String adsLuckStatus, String adsDefaultStatus, List<LTimer> lTimer) {
        super();
        this.adsId = adsId;
        this.adsType = adsType;
        this.adsFile = adsFile;
        this.adsViewTime = adsViewTime;
        this.adsVoice = adsVoice;
        this.adsVoiceFile = adsVoiceFile;
        this.adsLinkImage = adsLinkImage;
        this.adsCoinCollectionTime = adsCoinCollectionTime;
        this.adsCoinCollectionNo = adsCoinCollectionNo;
        this.adsLuckStatus = adsLuckStatus;
        this.adsDefaultStatus = adsDefaultStatus;
        this.lTimer = lTimer;
    }

    public String getAdsId() {
        return adsId;
    }

    public void setAdsId(String adsId) {
        this.adsId = adsId;
    }

    public String getAdsType() {
        return adsType;
    }

    public void setAdsType(String adsType) {
        this.adsType = adsType;
    }

    public String getAdsFile() {
        return adsFile;
    }

    public void setAdsFile(String adsFile) {
        this.adsFile = adsFile;
    }

    public String getAdsViewTime() {
        return adsViewTime;
    }

    public void setAdsViewTime(String adsViewTime) {
        this.adsViewTime = adsViewTime;
    }

    public String getAdsVoice() {
        return adsVoice;
    }

    public void setAdsVoice(String adsVoice) {
        this.adsVoice = adsVoice;
    }

    public String getAdsVoiceFile() {
        return adsVoiceFile;
    }

    public void setAdsVoiceFile(String adsVoiceFile) {
        this.adsVoiceFile = adsVoiceFile;
    }

    public String getAdsLinkImage() {
        return adsLinkImage;
    }

    public void setAdsLinkImage(String adsLinkImage) {
        this.adsLinkImage = adsLinkImage;
    }

    public String getAdsCoinCollectionTime() {
        return adsCoinCollectionTime;
    }

    public void setAdsCoinCollectionTime(String adsCoinCollectionTime) {
        this.adsCoinCollectionTime = adsCoinCollectionTime;
    }

    public String getAdsCoinCollectionNo() {
        return adsCoinCollectionNo;
    }

    public void setAdsCoinCollectionNo(String adsCoinCollectionNo) {
        this.adsCoinCollectionNo = adsCoinCollectionNo;
    }

    public String getAdsLuckStatus() {
        return adsLuckStatus;
    }

    public void setAdsLuckStatus(String adsLuckStatus) {
        this.adsLuckStatus = adsLuckStatus;
    }

    public String getAdsDefaultStatus() {
        return adsDefaultStatus;
    }

    public void setAdsDefaultStatus(String adsDefaultStatus) {
        this.adsDefaultStatus = adsDefaultStatus;
    }

    public List<LTimer> getLTimer() {
        return lTimer;
    }

    public void setLTimer(List<LTimer> lTimer) {
        this.lTimer = lTimer;
    }

}
