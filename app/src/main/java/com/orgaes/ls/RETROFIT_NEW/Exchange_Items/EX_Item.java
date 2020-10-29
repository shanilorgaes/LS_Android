
package com.orgaes.ls.RETROFIT_NEW.Exchange_Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EX_Item {

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
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("client_user_fname")
    @Expose
    private String clientUserFname;
    @SerializedName("client_user_lname")
    @Expose
    private String clientUserLname;

    /**
     * No args constructor for use in serialization
     * 
     */
    public EX_Item() {
    }

    /**
     * 
     * @param image
     * @param unit
     * @param endDate
     * @param unitCoinCost
     * @param unitCount
     * @param name
     * @param collectionCenter
     * @param description
     * @param clientUserLname
     * @param id
     * @param clientUserFname
     */
    public EX_Item(String id, String name, String image, String description, String unitCount, String unit, String unitCoinCost, String collectionCenter, String endDate, String clientUserFname, String clientUserLname) {
        super();
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.unitCount = unitCount;
        this.unit = unit;
        this.unitCoinCost = unitCoinCost;
        this.collectionCenter = collectionCenter;
        this.endDate = endDate;
        this.clientUserFname = clientUserFname;
        this.clientUserLname = clientUserLname;
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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

}
