
package com.orgaes.ls.RETROFIT_NEW.InstantLuck_Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Instant_LuckDetail {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("client_user_fname")
    @Expose
    private String clientUserFname;
    @SerializedName("client_user_lname")
    @Expose
    private String clientUserLname;
    @SerializedName("collection_center")
    @Expose
    private String collectionCenter;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Instant_LuckDetail() {
    }

    /**
     * 
     * @param image
     * @param clientId
     * @param endDate
     * @param name
     * @param collectionCenter
     * @param description
     * @param clientUserLname
     * @param id
     * @param clientUserFname
     */
    public Instant_LuckDetail(String id, String clientId, String name, String image, String description, String endDate, String clientUserFname, String clientUserLname, String collectionCenter) {
        super();
        this.id = id;
        this.clientId = clientId;
        this.name = name;
        this.image = image;
        this.description = description;
        this.endDate = endDate;
        this.clientUserFname = clientUserFname;
        this.clientUserLname = clientUserLname;
        this.collectionCenter = collectionCenter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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

    public String getCollectionCenter() {
        return collectionCenter;
    }

    public void setCollectionCenter(String collectionCenter) {
        this.collectionCenter = collectionCenter;
    }

}
