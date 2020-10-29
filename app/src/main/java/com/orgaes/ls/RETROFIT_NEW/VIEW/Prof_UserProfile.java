
package com.orgaes.ls.RETROFIT_NEW.VIEW;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prof_UserProfile {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("photo_url")
    @Expose
    private String photoUrl;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("self_info")
    @Expose
    private String selfInfo;
    @SerializedName("photo_approval_status")
    @Expose
    private String photoApprovalStatus;
    @SerializedName("profession")
    @Expose
    private String profession;
    @SerializedName("register_date")
    @Expose
    private String registerDate;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("group_code")
    @Expose
    private String groupCode;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("coin")
    @Expose
    private String coin;
    @SerializedName("active_luck")
    @Expose
    private String activeLuck;
    @SerializedName("total_luck")
    @Expose
    private String totalLuck;
    @SerializedName("voucher")
    @Expose
    private String voucher;
    @SerializedName("share_luck")
    @Expose
    private String shareLuck;
    @SerializedName("wallet")
    @Expose
    private String wallet;
    @SerializedName("share_coin")
    @Expose
    private String shareCoin;
    @SerializedName("friends")
    @Expose
    private String friends;
    @SerializedName("exchanges")
    @Expose
    private String exchanges;
    @SerializedName("chat")
    @Expose
    private String chat;
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
    public Prof_UserProfile() {
    }

    /**
     * 
     * @param profession
     * @param shareLuck
     * @param wallet
     * @param gender
     * @param totalLuck
     * @param voucher
     * @param activeLuck
     * @param mobile
     * @param exchanges
     * @param photo
     * @param photoApprovalStatus
     * @param isActive
     * @param friends
     * @param photoUrl
     * @param selfInfo
     * @param phone
     * @param chat
     * @param name
     * @param id
     * @param shareCoin
     * @param email
     * @param registerDate
     * @param groupCode
     * @param coin
     */
    public Prof_UserProfile(String id, String name, String photoUrl, String mobile, String email, String selfInfo, String photoApprovalStatus, String profession, String registerDate, String gender, String groupCode, String phone, String coin, String activeLuck, String totalLuck, String voucher, String shareLuck, String wallet, String shareCoin, String friends, String exchanges, String chat, String photo, String isActive) {
        super();
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.mobile = mobile;
        this.email = email;
        this.selfInfo = selfInfo;
        this.photoApprovalStatus = photoApprovalStatus;
        this.profession = profession;
        this.registerDate = registerDate;
        this.gender = gender;
        this.groupCode = groupCode;
        this.phone = phone;
        this.coin = coin;
        this.activeLuck = activeLuck;
        this.totalLuck = totalLuck;
        this.voucher = voucher;
        this.shareLuck = shareLuck;
        this.wallet = wallet;
        this.shareCoin = shareCoin;
        this.friends = friends;
        this.exchanges = exchanges;
        this.chat = chat;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSelfInfo() {
        return selfInfo;
    }

    public void setSelfInfo(String selfInfo) {
        this.selfInfo = selfInfo;
    }

    public String getPhotoApprovalStatus() {
        return photoApprovalStatus;
    }

    public void setPhotoApprovalStatus(String photoApprovalStatus) {
        this.photoApprovalStatus = photoApprovalStatus;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getActiveLuck() {
        return activeLuck;
    }

    public void setActiveLuck(String activeLuck) {
        this.activeLuck = activeLuck;
    }

    public String getTotalLuck() {
        return totalLuck;
    }

    public void setTotalLuck(String totalLuck) {
        this.totalLuck = totalLuck;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getShareLuck() {
        return shareLuck;
    }

    public void setShareLuck(String shareLuck) {
        this.shareLuck = shareLuck;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getShareCoin() {
        return shareCoin;
    }

    public void setShareCoin(String shareCoin) {
        this.shareCoin = shareCoin;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public String getExchanges() {
        return exchanges;
    }

    public void setExchanges(String exchanges) {
        this.exchanges = exchanges;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
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
