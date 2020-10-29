
package com.orgaes.ls.RETROFIT_NEW.Home_Api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodaysWinner {

    @SerializedName("edition_name")
    @Expose
    private String editionName;
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
    @SerializedName("user_photo_approval_status")
    @Expose
    private String userPhotoApprovalStatus;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("lb_no_of_coins")
    @Expose
    private String lbNoOfCoins;
    @SerializedName("lb_coin_points")
    @Expose
    private String lbCoinPoints;
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
    @SerializedName("prof_voucher")
    @Expose
    private String profVoucher;
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
    @SerializedName("prof_total_luck")
    @Expose
    private String profTotalLuck;
    @SerializedName("prof_total_share_lucks")
    @Expose
    private String profTotalShareLucks;
    @SerializedName("luck_win_id")
    @Expose
    private String luckWinId;
    @SerializedName("gcl_id")
    @Expose
    private String gclId;
    @SerializedName("item_name")
    @Expose
    private String itemName;
    @SerializedName("design")
    @Expose
    private String design;
    @SerializedName("client_user_image")
    @Expose
    private String clientUserImage;
    @SerializedName("win_date")
    @Expose
    private String winDate;
    @SerializedName("win_time")
    @Expose
    private String winTime;
    @SerializedName("type")
    @Expose
    private String type;
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
    public TodaysWinner() {
    }

    /**
     * 
     * @param profTotalShareLucks
     * @param userPhoto
     * @param gender
     * @param luckWinId
     * @param clientUserImage
     * @param userPhone
     * @param lbNoOfCoins
     * @param privacyStatus
     * @param profWallet
     * @param winDate
     * @param type
     * @param gclId
     * @param profVoucher
     * @param itemName
     * @param userPhotoApprovalStatus
     * @param profPhoneNumber
     * @param lbCoinPoints
     * @param design
     * @param coinSUM
     * @param totalCoinExchages
     * @param totoalvoucher
     * @param userEmail
     * @param profTotalLuck
     * @param winTime
     * @param walletsum
     * @param luckWinCount
     * @param profTotalExchanges
     * @param luckCOUNT
     * @param editionName
     * @param profTotalChanks
     * @param userName
     * @param userId
     * @param profTotalSharesCoin
     * @param activeLuckInHand
     * @param profActiveLuck
     * @param profCoin
     * @param totalchanks
     */
    public TodaysWinner(String editionName, String userId, String userName, String userPhoto, String userPhone, String userEmail, String userPhotoApprovalStatus, String gender, String lbNoOfCoins, String lbCoinPoints, String privacyStatus, String profPhoneNumber, String profCoin, String profActiveLuck, String profVoucher, String profWallet, String profTotalSharesCoin, String profTotalChanks, String profTotalExchanges, String profTotalLuck, String profTotalShareLucks, String luckWinId, String gclId, String itemName, String design, String clientUserImage, String winDate, String winTime, String type, String luckWinCount, String totalchanks, String activeLuckInHand, String totoalvoucher, String coinSUM, String luckCOUNT, String walletsum, String totalCoinExchages) {
        super();
        this.editionName = editionName;
        this.userId = userId;
        this.userName = userName;
        this.userPhoto = userPhoto;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userPhotoApprovalStatus = userPhotoApprovalStatus;
        this.gender = gender;
        this.lbNoOfCoins = lbNoOfCoins;
        this.lbCoinPoints = lbCoinPoints;
        this.privacyStatus = privacyStatus;
        this.profPhoneNumber = profPhoneNumber;
        this.profCoin = profCoin;
        this.profActiveLuck = profActiveLuck;
        this.profVoucher = profVoucher;
        this.profWallet = profWallet;
        this.profTotalSharesCoin = profTotalSharesCoin;
        this.profTotalChanks = profTotalChanks;
        this.profTotalExchanges = profTotalExchanges;
        this.profTotalLuck = profTotalLuck;
        this.profTotalShareLucks = profTotalShareLucks;
        this.luckWinId = luckWinId;
        this.gclId = gclId;
        this.itemName = itemName;
        this.design = design;
        this.clientUserImage = clientUserImage;
        this.winDate = winDate;
        this.winTime = winTime;
        this.type = type;
        this.luckWinCount = luckWinCount;
        this.totalchanks = totalchanks;
        this.activeLuckInHand = activeLuckInHand;
        this.totoalvoucher = totoalvoucher;
        this.coinSUM = coinSUM;
        this.luckCOUNT = luckCOUNT;
        this.walletsum = walletsum;
        this.totalCoinExchages = totalCoinExchages;
    }

    public String getEditionName() {
        return editionName;
    }

    public void setEditionName(String editionName) {
        this.editionName = editionName;
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

    public String getUserPhotoApprovalStatus() {
        return userPhotoApprovalStatus;
    }

    public void setUserPhotoApprovalStatus(String userPhotoApprovalStatus) {
        this.userPhotoApprovalStatus = userPhotoApprovalStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLbNoOfCoins() {
        return lbNoOfCoins;
    }

    public void setLbNoOfCoins(String lbNoOfCoins) {
        this.lbNoOfCoins = lbNoOfCoins;
    }

    public String getLbCoinPoints() {
        return lbCoinPoints;
    }

    public void setLbCoinPoints(String lbCoinPoints) {
        this.lbCoinPoints = lbCoinPoints;
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

    public String getProfVoucher() {
        return profVoucher;
    }

    public void setProfVoucher(String profVoucher) {
        this.profVoucher = profVoucher;
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

    public String getProfTotalLuck() {
        return profTotalLuck;
    }

    public void setProfTotalLuck(String profTotalLuck) {
        this.profTotalLuck = profTotalLuck;
    }

    public String getProfTotalShareLucks() {
        return profTotalShareLucks;
    }

    public void setProfTotalShareLucks(String profTotalShareLucks) {
        this.profTotalShareLucks = profTotalShareLucks;
    }

    public String getLuckWinId() {
        return luckWinId;
    }

    public void setLuckWinId(String luckWinId) {
        this.luckWinId = luckWinId;
    }

    public String getGclId() {
        return gclId;
    }

    public void setGclId(String gclId) {
        this.gclId = gclId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getClientUserImage() {
        return clientUserImage;
    }

    public void setClientUserImage(String clientUserImage) {
        this.clientUserImage = clientUserImage;
    }

    public String getWinDate() {
        return winDate;
    }

    public void setWinDate(String winDate) {
        this.winDate = winDate;
    }

    public String getWinTime() {
        return winTime;
    }

    public void setWinTime(String winTime) {
        this.winTime = winTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
