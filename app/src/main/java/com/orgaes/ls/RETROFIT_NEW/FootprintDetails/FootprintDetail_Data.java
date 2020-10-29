
package com.orgaes.ls.RETROFIT_NEW.FootprintDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FootprintDetail_Data {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("accept_date")
    @Expose
    private String acceptDate;
    @SerializedName("accept_time")
    @Expose
    private String acceptTime;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("self_info")
    @Expose
    private String selfInfo;
    @SerializedName("pic")
    @Expose
    private String pic;
    @SerializedName("photo_approval_status")
    @Expose
    private String photoApprovalStatus;
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
    @SerializedName("voucher")
    @Expose
    private String voucher;
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
    @SerializedName("coin_no")
    @Expose
    private String coinNo;
    @SerializedName("ads_title")
    @Expose
    private String adsTitle;
    @SerializedName("edition")
    @Expose
    private String edition;
    @SerializedName("luck")
    @Expose
    private String luck;
    @SerializedName("luck_pic")
    @Expose
    private String luckPic;
    @SerializedName("type")
    @Expose
    private String type;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FootprintDetail_Data() {
    }

    /**
     * 
     * @param date
     * @param acceptDate
     * @param luck
     * @param gender
     * @param voucher
     * @param activeLuck
     * @param adsTitle
     * @param edition
     * @param photoApprovalStatus
     * @param pic
     * @param isActive
     * @param type
     * @param coinNo
     * @param selfInfo
     * @param groupCode
     * @param wallet
     * @param acceptTime
     * @param mobile
     * @param exchanges
     * @param photo
     * @param friends
     * @param phone
     * @param luckPic
     * @param chat
     * @param name
     * @param time
     * @param shareCoin
     * @param coin
     */
    public FootprintDetail_Data(String date, String time, String acceptDate, String acceptTime, String name, String mobile, String selfInfo, String pic, String photoApprovalStatus, String gender, String groupCode, String phone, String coin, String activeLuck, String voucher, String wallet, String shareCoin, String friends, String exchanges, String chat, String photo, String isActive, String coinNo, String adsTitle, String edition, String luck, String luckPic, String type) {
        super();
        this.date = date;
        this.time = time;
        this.acceptDate = acceptDate;
        this.acceptTime = acceptTime;
        this.name = name;
        this.mobile = mobile;
        this.selfInfo = selfInfo;
        this.pic = pic;
        this.photoApprovalStatus = photoApprovalStatus;
        this.gender = gender;
        this.groupCode = groupCode;
        this.phone = phone;
        this.coin = coin;
        this.activeLuck = activeLuck;
        this.voucher = voucher;
        this.wallet = wallet;
        this.shareCoin = shareCoin;
        this.friends = friends;
        this.exchanges = exchanges;
        this.chat = chat;
        this.photo = photo;
        this.isActive = isActive;
        this.coinNo = coinNo;
        this.adsTitle = adsTitle;
        this.edition = edition;
        this.luck = luck;
        this.luckPic = luckPic;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSelfInfo() {
        return selfInfo;
    }

    public void setSelfInfo(String selfInfo) {
        this.selfInfo = selfInfo;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPhotoApprovalStatus() {
        return photoApprovalStatus;
    }

    public void setPhotoApprovalStatus(String photoApprovalStatus) {
        this.photoApprovalStatus = photoApprovalStatus;
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

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
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

    public String getCoinNo() {
        return coinNo;
    }

    public void setCoinNo(String coinNo) {
        this.coinNo = coinNo;
    }

    public String getAdsTitle() {
        return adsTitle;
    }

    public void setAdsTitle(String adsTitle) {
        this.adsTitle = adsTitle;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getLuck() {
        return luck;
    }

    public void setLuck(String luck) {
        this.luck = luck;
    }

    public String getLuckPic() {
        return luckPic;
    }

    public void setLuckPic(String luckPic) {
        this.luckPic = luckPic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
