
package com.orgaes.ls.RETROFIT_NEW.View_LUCK_EXCHANGE_DATA;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class View_LuckDetail {

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
    @SerializedName("unit_count")
    @Expose
    private String unitCount;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("unit_coin_cost")
    @Expose
    private String unitCoinCost;
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

    /**
     * No args constructor for use in serialization
     * 
     */
    public View_LuckDetail() {
    }

    /**
     * 
     * @param image
     * @param luck
     * @param clientUserImage
     * @param endDate
     * @param unitCount
     * @param description
     * @param edition
     * @param clientUserLname
     * @param totalCount
     * @param clientUserFname
     * @param unit
     * @param unitCoinCost
     * @param collectionCenter
     * @param startDate
     */
    public View_LuckDetail(String clientUserFname, String clientUserLname, String clientUserImage, String luck, String image, String description, String totalCount, String unitCount, String unit, String unitCoinCost, String collectionCenter, String edition, String startDate, String endDate) {
        super();
        this.clientUserFname = clientUserFname;
        this.clientUserLname = clientUserLname;
        this.clientUserImage = clientUserImage;
        this.luck = luck;
        this.image = image;
        this.description = description;
        this.totalCount = totalCount;
        this.unitCount = unitCount;
        this.unit = unit;
        this.unitCoinCost = unitCoinCost;
        this.collectionCenter = collectionCenter;
        this.edition = edition;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(String unitCount) {
        this.unitCount = unitCount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitCoinCost() {
        return unitCoinCost;
    }

    public void setUnitCoinCost(String unitCoinCost) {
        this.unitCoinCost = unitCoinCost;
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

}
