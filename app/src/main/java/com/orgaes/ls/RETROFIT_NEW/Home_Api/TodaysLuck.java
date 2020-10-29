
package com.orgaes.ls.RETROFIT_NEW.Home_Api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodaysLuck {

    @SerializedName("coin_supply")
    @Expose
    private String coinSupply;
    @SerializedName("client_user_image")
    @Expose
    private String clientUserImage;
    @SerializedName("client_user_fname")
    @Expose
    private String clientUserFname;
    @SerializedName("client_user_lname")
    @Expose
    private String clientUserLname;
    @SerializedName("no_luck")
    @Expose
    private String noLuck;
    @SerializedName("ads_id")
    @Expose
    private String adsId;
    @SerializedName("ads_coin_collection_time")
    @Expose
    private String adsCoinCollectionTime;
    @SerializedName("ads_coin_collection_no")
    @Expose
    private String adsCoinCollectionNo;
    @SerializedName("gift_description")
    @Expose
    private String giftDescription;
    @SerializedName("ads_type")
    @Expose
    private String adsType;
    @SerializedName("ads_view_time")
    @Expose
    private String adsViewTime;
    @SerializedName("ads_file")
    @Expose
    private String adsFile;
    @SerializedName("item_name")
    @Expose
    private String itemName;
    @SerializedName("edition_name")
    @Expose
    private String editionName;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("redeem_date")
    @Expose
    private String redeemDate;
    @SerializedName("collection_center")
    @Expose
    private String collectionCenter;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("design")
    @Expose
    private String design;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TodaysLuck() {
    }

    /**
     * 
     * @param coinSupply
     * @param clientUserImage
     * @param endDate
     * @param editionName
     * @param noLuck
     * @param clientUserLname
     * @param type
     * @param clientUserFname
     * @param adsId
     * @param itemName
     * @param redeemDate
     * @param adsViewTime
     * @param adsType
     * @param collectionCenter
     * @param adsCoinCollectionTime
     * @param adsFile
     * @param adsCoinCollectionNo
     * @param giftDescription
     */
    public TodaysLuck(String coinSupply, String clientUserImage, String clientUserFname, String clientUserLname, String noLuck, String adsId, String adsCoinCollectionTime, String adsCoinCollectionNo, String giftDescription, String adsType, String adsViewTime, String adsFile, String itemName, String editionName, String endDate, String redeemDate, String collectionCenter, String type) {
        super();
        this.coinSupply = coinSupply;
        this.clientUserImage = clientUserImage;
        this.clientUserFname = clientUserFname;
        this.clientUserLname = clientUserLname;
        this.noLuck = noLuck;
        this.adsId = adsId;
        this.adsCoinCollectionTime = adsCoinCollectionTime;
        this.adsCoinCollectionNo = adsCoinCollectionNo;
        this.giftDescription = giftDescription;
        this.adsType = adsType;
        this.adsViewTime = adsViewTime;
        this.adsFile = adsFile;
        this.itemName = itemName;
        this.editionName = editionName;
        this.endDate = endDate;
        this.redeemDate = redeemDate;
        this.collectionCenter = collectionCenter;
        this.type = type;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getCoinSupply() {
        return coinSupply;
    }

    public void setCoinSupply(String coinSupply) {
        this.coinSupply = coinSupply;
    }

    public String getClientUserImage() {
        return clientUserImage;
    }

    public void setClientUserImage(String clientUserImage) {
        this.clientUserImage = clientUserImage;
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

    public String getNoLuck() {
        return noLuck;
    }

    public void setNoLuck(String noLuck) {
        this.noLuck = noLuck;
    }

    public String getAdsId() {
        return adsId;
    }

    public void setAdsId(String adsId) {
        this.adsId = adsId;
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

    public String getGiftDescription() {
        return giftDescription;
    }

    public void setGiftDescription(String giftDescription) {
        this.giftDescription = giftDescription;
    }

    public String getAdsType() {
        return adsType;
    }

    public void setAdsType(String adsType) {
        this.adsType = adsType;
    }

    public String getAdsViewTime() {
        return adsViewTime;
    }

    public void setAdsViewTime(String adsViewTime) {
        this.adsViewTime = adsViewTime;
    }

    public String getAdsFile() {
        return adsFile;
    }

    public void setAdsFile(String adsFile) {
        this.adsFile = adsFile;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getEditionName() {
        return editionName;
    }

    public void setEditionName(String editionName) {
        this.editionName = editionName;
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

    public String getCollectionCenter() {
        return collectionCenter;
    }

    public void setCollectionCenter(String collectionCenter) {
        this.collectionCenter = collectionCenter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
