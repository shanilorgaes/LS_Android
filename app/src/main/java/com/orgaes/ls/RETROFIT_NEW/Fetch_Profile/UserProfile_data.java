
package com.orgaes.ls.RETROFIT_NEW.Fetch_Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfile_data {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_photo")
    @Expose
    private String userPhoto;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_wishlist_id")
    @Expose
    private String userWishlistId;
    @SerializedName("user_self_info")
    @Expose
    private String userSelfInfo;
    @SerializedName("user_photo_approval_status")
    @Expose
    private String userPhotoApprovalStatus;
    @SerializedName("user_profession")
    @Expose
    private String userProfession;
    @SerializedName("user_wishlist_updated_date")
    @Expose
    private String userWishlistUpdatedDate;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("agegroup_id")
    @Expose
    private String agegroupId;
    @SerializedName("lb_no_of_coins")
    @Expose
    private String lbNoOfCoins;
    @SerializedName("privacy_status")
    @Expose
    private String privacyStatus;
    @SerializedName("prof_phone_number")
    @Expose
    private String profPhoneNumber;
    @SerializedName("prof_coin")
    @Expose
    private String profCoin;
    @SerializedName("prof_active_luck")
    @Expose
    private String profActiveLuck;
    @SerializedName("prof_total_luck")
    @Expose
    private String profTotalLuck;
    @SerializedName("prof_voucher")
    @Expose
    private String profVoucher;
    @SerializedName("prof_total_share_lucks")
    @Expose
    private String profTotalShareLucks;
    @SerializedName("prof_wallet")
    @Expose
    private String profWallet;
    @SerializedName("prof_total_shares_coin")
    @Expose
    private String profTotalSharesCoin;
    @SerializedName("prof_total_chanks")
    @Expose
    private String profTotalChanks;
    @SerializedName("prof_total_exchanges")
    @Expose
    private String profTotalExchanges;
    @SerializedName("prof_chat")
    @Expose
    private String profChat;
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
    public UserProfile_data() {
    }

    /**
     * 
     * @param profTotalShareLucks
     * @param userPhoto
     * @param gender
     * @param userPhone
     * @param userSelfInfo
     * @param lbNoOfCoins
     * @param privacyStatus
     * @param profWallet
     * @param userWishlistId
     * @param profVoucher
     * @param userPhotoApprovalStatus
     * @param profPhoneNumber
     * @param coinSUM
     * @param totalCoinExchages
     * @param totoalvoucher
     * @param userEmail
     * @param profTotalLuck
     * @param profChat
     * @param walletsum
     * @param luckWinCount
     * @param profTotalExchanges
     * @param luckCOUNT
     * @param profTotalChanks
     * @param agegroupId
     * @param userName
     * @param userId
     * @param profTotalSharesCoin
     * @param userProfession
     * @param activeLuckInHand
     * @param profActiveLuck
     * @param profCoin
     * @param totalchanks
     * @param userWishlistUpdatedDate
     */
    public UserProfile_data(String userId, String userName, String userPhoto, String userPhone, String userEmail, String userWishlistId, String userSelfInfo, String userPhotoApprovalStatus, String userProfession, String userWishlistUpdatedDate, String gender, String agegroupId, String lbNoOfCoins, String privacyStatus, String profPhoneNumber, String profCoin, String profActiveLuck, String profTotalLuck, String profVoucher, String profTotalShareLucks, String profWallet, String profTotalSharesCoin, String profTotalChanks, String profTotalExchanges, String profChat, String luckWinCount, String totalchanks, String activeLuckInHand, String totoalvoucher, String coinSUM, String luckCOUNT, String walletsum, String totalCoinExchages) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.userPhoto = userPhoto;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userWishlistId = userWishlistId;
        this.userSelfInfo = userSelfInfo;
        this.userPhotoApprovalStatus = userPhotoApprovalStatus;
        this.userProfession = userProfession;
        this.userWishlistUpdatedDate = userWishlistUpdatedDate;
        this.gender = gender;
        this.agegroupId = agegroupId;
        this.lbNoOfCoins = lbNoOfCoins;
        this.privacyStatus = privacyStatus;
        this.profPhoneNumber = profPhoneNumber;
        this.profCoin = profCoin;
        this.profActiveLuck = profActiveLuck;
        this.profTotalLuck = profTotalLuck;
        this.profVoucher = profVoucher;
        this.profTotalShareLucks = profTotalShareLucks;
        this.profWallet = profWallet;
        this.profTotalSharesCoin = profTotalSharesCoin;
        this.profTotalChanks = profTotalChanks;
        this.profTotalExchanges = profTotalExchanges;
        this.profChat = profChat;
        this.luckWinCount = luckWinCount;
        this.totalchanks = totalchanks;
        this.activeLuckInHand = activeLuckInHand;
        this.totoalvoucher = totoalvoucher;
        this.coinSUM = coinSUM;
        this.luckCOUNT = luckCOUNT;
        this.walletsum = walletsum;
        this.totalCoinExchages = totalCoinExchages;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserWishlistId() {
        return userWishlistId;
    }

    public void setUserWishlistId(String userWishlistId) {
        this.userWishlistId = userWishlistId;
    }

    public String getUserSelfInfo() {
        return userSelfInfo;
    }

    public void setUserSelfInfo(String userSelfInfo) {
        this.userSelfInfo = userSelfInfo;
    }

    public String getUserPhotoApprovalStatus() {
        return userPhotoApprovalStatus;
    }

    public void setUserPhotoApprovalStatus(String userPhotoApprovalStatus) {
        this.userPhotoApprovalStatus = userPhotoApprovalStatus;
    }

    public String getUserProfession() {
        return userProfession;
    }

    public void setUserProfession(String userProfession) {
        this.userProfession = userProfession;
    }

    public String getUserWishlistUpdatedDate() {
        return userWishlistUpdatedDate;
    }

    public void setUserWishlistUpdatedDate(String userWishlistUpdatedDate) {
        this.userWishlistUpdatedDate = userWishlistUpdatedDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAgegroupId() {
        return agegroupId;
    }

    public void setAgegroupId(String agegroupId) {
        this.agegroupId = agegroupId;
    }

    public String getLbNoOfCoins() {
        return lbNoOfCoins;
    }

    public void setLbNoOfCoins(String lbNoOfCoins) {
        this.lbNoOfCoins = lbNoOfCoins;
    }

    public String getPrivacyStatus() {
        return privacyStatus;
    }

    public void setPrivacyStatus(String privacyStatus) {
        this.privacyStatus = privacyStatus;
    }

    public String getProfPhoneNumber() {
        return profPhoneNumber;
    }

    public void setProfPhoneNumber(String profPhoneNumber) {
        this.profPhoneNumber = profPhoneNumber;
    }

    public String getProfCoin() {
        return profCoin;
    }

    public void setProfCoin(String profCoin) {
        this.profCoin = profCoin;
    }

    public String getProfActiveLuck() {
        return profActiveLuck;
    }

    public void setProfActiveLuck(String profActiveLuck) {
        this.profActiveLuck = profActiveLuck;
    }

    public String getProfTotalLuck() {
        return profTotalLuck;
    }

    public void setProfTotalLuck(String profTotalLuck) {
        this.profTotalLuck = profTotalLuck;
    }

    public String getProfVoucher() {
        return profVoucher;
    }

    public void setProfVoucher(String profVoucher) {
        this.profVoucher = profVoucher;
    }

    public String getProfTotalShareLucks() {
        return profTotalShareLucks;
    }

    public void setProfTotalShareLucks(String profTotalShareLucks) {
        this.profTotalShareLucks = profTotalShareLucks;
    }

    public String getProfWallet() {
        return profWallet;
    }

    public void setProfWallet(String profWallet) {
        this.profWallet = profWallet;
    }

    public String getProfTotalSharesCoin() {
        return profTotalSharesCoin;
    }

    public void setProfTotalSharesCoin(String profTotalSharesCoin) {
        this.profTotalSharesCoin = profTotalSharesCoin;
    }

    public String getProfTotalChanks() {
        return profTotalChanks;
    }

    public void setProfTotalChanks(String profTotalChanks) {
        this.profTotalChanks = profTotalChanks;
    }

    public String getProfTotalExchanges() {
        return profTotalExchanges;
    }

    public void setProfTotalExchanges(String profTotalExchanges) {
        this.profTotalExchanges = profTotalExchanges;
    }

    public String getProfChat() {
        return profChat;
    }

    public void setProfChat(String profChat) {
        this.profChat = profChat;
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
