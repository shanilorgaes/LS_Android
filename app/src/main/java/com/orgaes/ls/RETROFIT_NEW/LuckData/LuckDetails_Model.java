
package com.orgaes.ls.RETROFIT_NEW.LuckData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LuckDetails_Model {

    @SerializedName("ad_media")
    @Expose
    private String adMedia;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("coin_supply")
    @Expose
    private String coinSupply;
    @SerializedName("client_user_fname")
    @Expose
    private String clientUserFname;
    @SerializedName("client_user_lname")
    @Expose
    private String clientUserLname;
    @SerializedName("client_user_image")
    @Expose
    private String clientUserImage;
    @SerializedName("luck")
    @Expose
    private String luck;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("total_count")
    @Expose
    private String totalCount;
    @SerializedName("collection_center")
    @Expose
    private String collectionCenter;
    @SerializedName("edition")
    @Expose
    private String edition;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("redeem_date")
    @Expose
    private String redeemDate;
    @SerializedName("enroll_fee")
    @Expose
    private Object enrollFee;
    @SerializedName("participant_min_limit")
    @Expose
    private Object participantMinLimit;
    @SerializedName("winner_limit")
    @Expose
    private Object winnerLimit;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LuckDetails_Model() {
    }

    /**
     * 
     * @param image
     * @param coinSupply
     * @param luck
     * @param participantMinLimit
     * @param clientUserImage
     * @param endDate
     * @param description
     * @param edition
     * @param winnerLimit
     * @param clientUserLname
     * @param adMedia
     * @param totalCount
     * @param clientUserFname
     * @param duration
     * @param redeemDate
     * @param enrollFee
     * @param collectionCenter
     * @param startDate
     */
    public LuckDetails_Model(String adMedia, String duration, String coinSupply, String clientUserFname, String clientUserLname, String clientUserImage, String luck, String image, String description, String totalCount, String collectionCenter, String edition, String startDate, String endDate, String redeemDate, Object enrollFee, Object participantMinLimit, Object winnerLimit) {
        super();
        this.adMedia = adMedia;
        this.duration = duration;
        this.coinSupply = coinSupply;
        this.clientUserFname = clientUserFname;
        this.clientUserLname = clientUserLname;
        this.clientUserImage = clientUserImage;
        this.luck = luck;
        this.image = image;
        this.description = description;
        this.totalCount = totalCount;
        this.collectionCenter = collectionCenter;
        this.edition = edition;
        this.startDate = startDate;
        this.endDate = endDate;
        this.redeemDate = redeemDate;
        this.enrollFee = enrollFee;
        this.participantMinLimit = participantMinLimit;
        this.winnerLimit = winnerLimit;
    }

    public String getAdMedia() {
        return adMedia;
    }

    public void setAdMedia(String adMedia) {
        this.adMedia = adMedia;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCoinSupply() {
        return coinSupply;
    }

    public void setCoinSupply(String coinSupply) {
        this.coinSupply = coinSupply;
    }

    public String getClientUserFname() {
        return clientUserFname;
    }

    public void setClientUserFname(String clientUserFname) {
        this.clientUserFname = clientUserFname;
    }

    public String getClientUserLname() {
        return clientUserLname;
    }

    public void setClientUserLname(String clientUserLname) {
        this.clientUserLname = clientUserLname;
    }

    public String getClientUserImage() {
        return clientUserImage;
    }

    public void setClientUserImage(String clientUserImage) {
        this.clientUserImage = clientUserImage;
    }

    public String getLuck() {
        return luck;
    }

    public void setLuck(String luck) {
        this.luck = luck;
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

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getCollectionCenter() {
        return collectionCenter;
    }

    public void setCollectionCenter(String collectionCenter) {
        this.collectionCenter = collectionCenter;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRedeemDate() {
        return redeemDate;
    }

    public void setRedeemDate(String redeemDate) {
        this.redeemDate = redeemDate;
    }

    public Object getEnrollFee() {
        return enrollFee;
    }

    public void setEnrollFee(Object enrollFee) {
        this.enrollFee = enrollFee;
    }

    public Object getParticipantMinLimit() {
        return participantMinLimit;
    }

    public void setParticipantMinLimit(Object participantMinLimit) {
        this.participantMinLimit = participantMinLimit;
    }

    public Object getWinnerLimit() {
        return winnerLimit;
    }

    public void setWinnerLimit(Object winnerLimit) {
        this.winnerLimit = winnerLimit;
    }

}
