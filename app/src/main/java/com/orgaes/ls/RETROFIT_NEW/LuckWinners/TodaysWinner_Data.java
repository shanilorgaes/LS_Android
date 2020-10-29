
package com.orgaes.ls.RETROFIT_NEW.LuckWinners;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodaysWinner_Data {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("photo_url")
    @Expose
    private String photoUrl;
    @SerializedName("photo_approval_status")
    @Expose
    private String photoApprovalStatus;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @SerializedName("type")
    @Expose
    private String type;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TodaysWinner_Data() {
    }

    /**
     * 
     * @param photoUrl
     * @param photo
     * @param photoApprovalStatus
     * @param id
     * @param isActive
     * @param type
     */
    public TodaysWinner_Data(String id, String photoUrl, String photoApprovalStatus, String photo, String isActive, String type) {
        super();
        this.id = id;
        this.photoUrl = photoUrl;
        this.photoApprovalStatus = photoApprovalStatus;
        this.photo = photo;
        this.isActive = isActive;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhotoApprovalStatus() {
        return photoApprovalStatus;
    }

    public void setPhotoApprovalStatus(String photoApprovalStatus) {
        this.photoApprovalStatus = photoApprovalStatus;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
