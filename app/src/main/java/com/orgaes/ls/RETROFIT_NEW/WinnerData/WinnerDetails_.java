
package com.orgaes.ls.RETROFIT_NEW.WinnerData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WinnerDetails_ {

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
    private String selfInfo;
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
    @SerializedName("edition")
    @Expose
    private Object edition;
    @SerializedName("rank")
    @Expose
    private Object rank;
    @SerializedName("wallet_money")
    @Expose
    private String wallet_money;
    @SerializedName("shared_lucks")
    @Expose
    private String shared_lucks;

    /**
     * No args constructor for use in serialization
     * 
     */
    public WinnerDetails_() {
    }

    /**
     * 
     * @param shareLuck
     * @param luck
     * @param gender
     * @param clientUserImage
     * @param voucher
     * @param totalLuck
     * @param activeLuck
     * @param description
     * @param edition
     * @param photoApprovalStatus
     * @param clientUserLname
     * @param isActive
     * @param clientUserFname
     * @param photoUrl
     * @param selfInfo
     * @param totalLucks
     * @param rank
     * @param id
     * @param email
     * @param groupCode
     * @param totalFriends
     * @param image
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
     * @param coin
     */
    public WinnerDetails_(String id, String name, String photoUrl, String mobile, String email, String photoApprovalStatus, String selfInfo, String gender, String groupCode, String phone, String coin, String activeLuck, String voucher, String wallet, String shareCoin, String friends, String exchanges, String totalLuck, String shareLuck, String chat, String photo, String isActive, String totalFriends, String totalLucks, String coinInHand, String activeLucks, String vouchers, String sharedCoins, String clientUserFname, String clientUserLname, String clientUserImage, String luck, String image, String description, Object edition, Object rank) {
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
        this.clientUserFname = clientUserFname;
        this.clientUserLname = clientUserLname;
        this.clientUserImage = clientUserImage;
        this.luck = luck;
        this.image = image;
        this.description = description;
        this.edition = edition;
        this.rank = rank;
    }

    public String getWallet_money() {
        return wallet_money;
    }

    public void setWallet_money(String wallet_money) {
        this.wallet_money = wallet_money;
    }

    public String getShared_lucks() {
        return shared_lucks;
    }

    public void setShared_lucks(String shared_lucks) {
        this.shared_lucks = shared_lucks;
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

    public String getSelfInfo() {
        return selfInfo;
    }

    public void setSelfInfo(String selfInfo) {
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

    public Object getEdition() {
        return edition;
    }

    public void setEdition(Object edition) {
        this.edition = edition;
    }

    public Object getRank() {
        return rank;
    }

    public void setRank(Object rank) {
        this.rank = rank;
    }

}
