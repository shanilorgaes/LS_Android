
package com.orgaes.ls.RETROFIT_NEW.LeaderBoard_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeaderboardUser {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("coins")
    @Expose
    private String coins;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("photo_url")
    @Expose
    private String photoUrl;
    @SerializedName("photo_approval_status")
    @Expose
    private String photoApprovalStatus;
    @SerializedName("photo_lock_status")
    @Expose
    private String photoLockStatus;
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("total_lucks")
    @Expose
    private String totalLucks;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LeaderboardUser() {
    }

    /**
     * 
     * @param photoUrl
     * @param photoLockStatus
     * @param coins
     * @param totalLucks
     * @param name
     * @param rank
     * @param photoApprovalStatus
     * @param userId
     */
    public LeaderboardUser(String userId, String coins, String name, String photoUrl, String photoApprovalStatus, String photoLockStatus, String rank, String totalLucks) {
        super();
        this.userId = userId;
        this.coins = coins;
        this.name = name;
        this.photoUrl = photoUrl;
        this.photoApprovalStatus = photoApprovalStatus;
        this.photoLockStatus = photoLockStatus;
        this.rank = rank;
        this.totalLucks = totalLucks;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
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

    public String getPhotoApprovalStatus() {
        return photoApprovalStatus;
    }

    public void setPhotoApprovalStatus(String photoApprovalStatus) {
        this.photoApprovalStatus = photoApprovalStatus;
    }

    public String getPhotoLockStatus() {
        return photoLockStatus;
    }

    public void setPhotoLockStatus(String photoLockStatus) {
        this.photoLockStatus = photoLockStatus;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTotalLucks() {
        return totalLucks;
    }

    public void setTotalLucks(String totalLucks) {
        this.totalLucks = totalLucks;
    }

}
