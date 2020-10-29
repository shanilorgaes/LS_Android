
package com.orgaes.ls.RETROFIT_NEW.LeaderBoardProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LB_Prof_UserProfile {

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
    @SerializedName("photo_approval_status")
    @Expose
    private String photoApprovalStatus;
    @SerializedName("self_info")
    @Expose
    private Object selfInfo;
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
    @SerializedName("total_luck")
    @Expose
    private String totalLuck;
    @SerializedName("share_luck")
    @Expose
    private String shareLuck;
    @SerializedName("chat")
    @Expose
    private String chat;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @SerializedName("total_friends")
    @Expose
    private String totalFriends;
    @SerializedName("total_lucks")
    @Expose
    private String totalLucks;
    @SerializedName("coin_in_hand")
    @Expose
    private String coinInHand;
    @SerializedName("active_lucks")
    @Expose
    private String activeLucks;
    @SerializedName("vouchers")
    @Expose
    private String vouchers;
    @SerializedName("shared_coins")
    @Expose
    private String sharedCoins;
    @SerializedName("exchanged_coins")
    @Expose
    private String exchangedCoins;
    @SerializedName("wallet_money")
    @Expose
    private String walletMoney;
    @SerializedName("shared_lucks")
    @Expose
    private String sharedLucks;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LB_Prof_UserProfile() {
    }

    /**
     * 
     * @param shareLuck
     * @param gender
     * @param voucher
     * @param totalLuck
     * @param activeLuck
     * @param photoApprovalStatus
     * @param isActive
     * @param photoUrl
     * @param selfInfo
     * @param totalLucks
     * @param exchangedCoins
     * @param sharedLucks
     * @param id
     * @param email
     * @param groupCode
     * @param totalFriends
     * @param wallet
     * @param mobile
     * @param exchanges
     * @param photo
     * @param vouchers
     * @param friends
     * @param phone
     * @param chat
     * @param activeLucks
     * @param name
     * @param coinInHand
     * @param sharedCoins
     * @param shareCoin
     * @param walletMoney
     * @param coin
     */
    public LB_Prof_UserProfile(String id, String name, String photoUrl, String mobile, String email, String photoApprovalStatus, Object selfInfo, String gender, String groupCode, String phone, String coin, String activeLuck, String voucher, String wallet, String shareCoin, String friends, String exchanges, String totalLuck, String shareLuck, String chat, String photo, String isActive, String totalFriends, String totalLucks, String coinInHand, String activeLucks, String vouchers, String sharedCoins, String exchangedCoins, String walletMoney, String sharedLucks) {
        super();
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.mobile = mobile;
        this.email = email;
        this.photoApprovalStatus = photoApprovalStatus;
        this.selfInfo = selfInfo;
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
        this.totalLuck = totalLuck;
        this.shareLuck = shareLuck;
        this.chat = chat;
        this.photo = photo;
        this.isActive = isActive;
        this.totalFriends = totalFriends;
        this.totalLucks = totalLucks;
        this.coinInHand = coinInHand;
        this.activeLucks = activeLucks;
        this.vouchers = vouchers;
        this.sharedCoins = sharedCoins;
        this.exchangedCoins = exchangedCoins;
        this.walletMoney = walletMoney;
        this.sharedLucks = sharedLucks;
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

    public String getPhotoApprovalStatus() {
        return photoApprovalStatus;
    }

    public void setPhotoApprovalStatus(String photoApprovalStatus) {
        this.photoApprovalStatus = photoApprovalStatus;
    }

    public Object getSelfInfo() {
        return selfInfo;
    }

    public void setSelfInfo(Object selfInfo) {
        this.selfInfo = selfInfo;
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

    public String getTotalLuck() {
        return totalLuck;
    }

    public void setTotalLuck(String totalLuck) {
        this.totalLuck = totalLuck;
    }

    public String getShareLuck() {
        return shareLuck;
    }

    public void setShareLuck(String shareLuck) {
        this.shareLuck = shareLuck;
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

    public String getTotalFriends() {
        return totalFriends;
    }

    public void setTotalFriends(String totalFriends) {
        this.totalFriends = totalFriends;
    }

    public String getTotalLucks() {
        return totalLucks;
    }

    public void setTotalLucks(String totalLucks) {
        this.totalLucks = totalLucks;
    }

    public String getCoinInHand() {
        return coinInHand;
    }

    public void setCoinInHand(String coinInHand) {
        this.coinInHand = coinInHand;
    }

    public String getActiveLucks() {
        return activeLucks;
    }

    public void setActiveLucks(String activeLucks) {
        this.activeLucks = activeLucks;
    }

    public String getVouchers() {
        return vouchers;
    }

    public void setVouchers(String vouchers) {
        this.vouchers = vouchers;
    }

    public String getSharedCoins() {
        return sharedCoins;
    }

    public void setSharedCoins(String sharedCoins) {
        this.sharedCoins = sharedCoins;
    }

    public String getExchangedCoins() {
        return exchangedCoins;
    }

    public void setExchangedCoins(String exchangedCoins) {
        this.exchangedCoins = exchangedCoins;
    }

    public String getWalletMoney() {
        return walletMoney;
    }

    public void setWalletMoney(String walletMoney) {
        this.walletMoney = walletMoney;
    }

    public String getSharedLucks() {
        return sharedLucks;
    }

    public void setSharedLucks(String sharedLucks) {
        this.sharedLucks = sharedLucks;
    }

}
