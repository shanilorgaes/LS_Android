
package com.orgaes.ls.RETROFIT_NEW.Fetc_ProfileData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfile_data_Module {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("photo_url")
    @Expose
    private String photoUrl;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("self_info")
    @Expose
    private Object selfInfo;
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
    @SerializedName("lb_no_of_coins")
    @Expose
    private String lbNoOfCoins;
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
    @SerializedName("luck_win_count")
    @Expose
    private String luckWinCount;
    @SerializedName("totalchanks")
    @Expose
    private String totalchanks;
    @SerializedName("activeLuckInHand")
    @Expose
    private String activeLuckInHand;
    @SerializedName("totoalvoucher")
    @Expose
    private String totoalvoucher;
    @SerializedName("coinSUM")
    @Expose
    private String coinSUM;
    @SerializedName("luckCOUNT")
    @Expose
    private String luckCOUNT;
    @SerializedName("walletsum")
    @Expose
    private String walletsum;
    @SerializedName("total_coin_exchages")
    @Expose
    private String totalCoinExchages;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserProfile_data_Module() {
    }

    /**
     * 
     * @param shareLuck
     * @param gender
     * @param totalLuck
     * @param voucher
     * @param lbNoOfCoins
     * @param activeLuck
     * @param photoApprovalStatus
     * @param isActive
     * @param photoUrl
     * @param selfInfo
     * @param coinSUM
     * @param totalCoinExchages
     * @param totoalvoucher
     * @param id
     * @param email
     * @param registerDate
     * @param groupCode
     * @param profession
     * @param walletsum
     * @param luckWinCount
     * @param wallet
     * @param luckCOUNT
     * @param exchanges
     * @param photo
     * @param friends
     * @param phone
     * @param activeLuckInHand
     * @param chat
     * @param name
     * @param totalchanks
     * @param shareCoin
     * @param coin
     */
    public UserProfile_data_Module(String id, String name, String photoUrl, String phone, String email, Object selfInfo, String photoApprovalStatus, String profession, String registerDate, String gender, String groupCode, String lbNoOfCoins, String coin, String activeLuck, String totalLuck, String voucher, String shareLuck, String wallet, String shareCoin, String friends, String exchanges, String chat, String photo, String isActive, String luckWinCount, String totalchanks, String activeLuckInHand, String totoalvoucher, String coinSUM, String luckCOUNT, String walletsum, String totalCoinExchages) {
        super();
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.phone = phone;
        this.email = email;
        this.selfInfo = selfInfo;
        this.photoApprovalStatus = photoApprovalStatus;
        this.profession = profession;
        this.registerDate = registerDate;
        this.gender = gender;
        this.groupCode = groupCode;
        this.lbNoOfCoins = lbNoOfCoins;
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
        this.luckWinCount = luckWinCount;
        this.totalchanks = totalchanks;
        this.activeLuckInHand = activeLuckInHand;
        this.totoalvoucher = totoalvoucher;
        this.coinSUM = coinSUM;
        this.luckCOUNT = luckCOUNT;
        this.walletsum = walletsum;
        this.totalCoinExchages = totalCoinExchages;
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

    public Object getSelfInfo() {
        return selfInfo;
    }

    public void setSelfInfo(Object selfInfo) {
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

    public String getLbNoOfCoins() {
        return lbNoOfCoins;
    }

    public void setLbNoOfCoins(String lbNoOfCoins) {
        this.lbNoOfCoins = lbNoOfCoins;
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

    public String getLuckWinCount() {
        return luckWinCount;
    }

    public void setLuckWinCount(String luckWinCount) {
        this.luckWinCount = luckWinCount;
    }

    public String getTotalchanks() {
        return totalchanks;
    }

    public void setTotalchanks(String totalchanks) {
        this.totalchanks = totalchanks;
    }

    public String getActiveLuckInHand() {
        return activeLuckInHand;
    }

    public void setActiveLuckInHand(String activeLuckInHand) {
        this.activeLuckInHand = activeLuckInHand;
    }

    public String getTotoalvoucher() {
        return totoalvoucher;
    }

    public void setTotoalvoucher(String totoalvoucher) {
        this.totoalvoucher = totoalvoucher;
    }

    public String getCoinSUM() {
        return coinSUM;
    }

    public void setCoinSUM(String coinSUM) {
        this.coinSUM = coinSUM;
    }

    public String getLuckCOUNT() {
        return luckCOUNT;
    }

    public void setLuckCOUNT(String luckCOUNT) {
        this.luckCOUNT = luckCOUNT;
    }

    public String getWalletsum() {
        return walletsum;
    }

    public void setWalletsum(String walletsum) {
        this.walletsum = walletsum;
    }

    public String getTotalCoinExchages() {
        return totalCoinExchages;
    }

    public void setTotalCoinExchages(String totalCoinExchages) {
        this.totalCoinExchages = totalCoinExchages;
    }

}
