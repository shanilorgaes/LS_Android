
package com.orgaes.ls.RETROFIT_NEW.FriendsList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FriendsList_Data {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("photo_url")
    @Expose
    private String photoUrl;
    @SerializedName("total_coins")
    @Expose
    private String totalCoins;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("group_code")
    @Expose
    private String groupCode;
    @SerializedName("photo_approval_status")
    @Expose
    private String photoApprovalStatus;
    @SerializedName("coin")
    @Expose
    private String coin;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("is_active")
    @Expose
    private String isActive;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FriendsList_Data() {
    }

    /**
     * 
     * @param photoUrl
     * @param gender
     * @param phone
     * @param name
     * @param totalCoins
     * @param photo
     * @param photoApprovalStatus
     * @param id
     * @param isActive
     * @param email
     * @param groupCode
     * @param coin
     */
    public FriendsList_Data(String id, String name, String photoUrl, String totalCoins, String phone, String email, String gender, String groupCode, String photoApprovalStatus, String coin, String photo, String isActive) {
        super();
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.totalCoins = totalCoins;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.groupCode = groupCode;
        this.photoApprovalStatus = photoApprovalStatus;
        this.coin = coin;
        this.photo = photo;
        this.isActive = isActive;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getTotalCoins() {
        return totalCoins;
    }

    public void setTotalCoins(String totalCoins) {
        this.totalCoins = totalCoins;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getPhotoApprovalStatus() {
        return photoApprovalStatus;
    }

    public void setPhotoApprovalStatus(String photoApprovalStatus) {
        this.photoApprovalStatus = photoApprovalStatus;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
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

}
