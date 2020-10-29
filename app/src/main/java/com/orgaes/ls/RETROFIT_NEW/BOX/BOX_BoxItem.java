
package com.orgaes.ls.RETROFIT_NEW.BOX;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BOX_BoxItem {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("edition")
    @Expose
    private String edition;
    @SerializedName("item_id")
    @Expose
    private String itemId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("collection_center")
    @Expose
    private String collectionCenter;
    @SerializedName("client_user_fname")
    @Expose
    private String clientUserFname;
    @SerializedName("client_user_lname")
    @Expose
    private String clientUserLname;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("type")
    @Expose
    private String type;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BOX_BoxItem() {
    }

    /**
     * 
     * @param itemId
     * @param image
     * @param endDate
     * @param name
     * @param collectionCenter
     * @param description
     * @param clientUserLname
     * @param id
     * @param type
     * @param clientUserFname
     * @param status
     */
    public BOX_BoxItem(String id, String itemId, String name, String image, String description, String status, String collectionCenter, String clientUserFname, String clientUserLname, String endDate, String type) {
        super();
        this.id = id;
        this.itemId = itemId;
        this.name = name;
        this.image = image;
        this.description = description;
        this.status = status;
        this.collectionCenter = collectionCenter;
        this.clientUserFname = clientUserFname;
        this.clientUserLname = clientUserLname;
        this.endDate = endDate;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCollectionCenter() {
        return collectionCenter;
    }

    public void setCollectionCenter(String collectionCenter) {
        this.collectionCenter = collectionCenter;
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
