
package com.orgaes.ls.RETROFIT_NEW.CoinData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoinData_Response {

    @SerializedName("coin_value")
    @Expose
    private String coinValue;
    @SerializedName("coin_last_earned")
    @Expose
    private String coinLastEarned;
    @SerializedName("coin_requested")
    @Expose
    private String coinRequested;
    @SerializedName("coin_exchanged")
    @Expose
    private String coinExchanged;
    @SerializedName("coin_luck_radar_claimed")
    @Expose
    private String coinLuckRadarClaimed;
    @SerializedName("coin_total")
    @Expose
    private String coinTotal;
    @SerializedName("coin_wallet_money")
    @Expose
    private String coinWalletMoney;
    @SerializedName("coin_shared")
    @Expose
    private String coinShared;
    @SerializedName("coin_used")
    @Expose
    private String coinUsed;
    @SerializedName("coin_expiry_date")
    @Expose
    private String coinExpiryDate;
    @SerializedName("coin_total_expired")
    @Expose
    private String coinTotalExpired;
    @SerializedName("message")
    @Expose
    private String message;
    public String getMessage() {
        return message;
    }
    /**
     * No args constructor for use in serialization
     * 
     */
    public CoinData_Response() {
    }

    /**
     * 
     * @param coinRequested
     * @param coinTotal
     * @param coinShared
     * @param coinUsed
     * @param coinTotalExpired
     * @param coinValue
     * @param coinLastEarned
     * @param coinExchanged
     * @param coinLuckRadarClaimed
     * @param coinExpiryDate
     * @param coinWalletMoney
     */
    public CoinData_Response(String coinValue, String coinLastEarned, String coinRequested, String coinExchanged, String coinLuckRadarClaimed, String coinTotal, String coinWalletMoney, String coinShared, String coinUsed, String coinExpiryDate, String coinTotalExpired) {
        super();
        this.coinValue = coinValue;
        this.coinLastEarned = coinLastEarned;
        this.coinRequested = coinRequested;
        this.coinExchanged = coinExchanged;
        this.coinLuckRadarClaimed = coinLuckRadarClaimed;
        this.coinTotal = coinTotal;
        this.coinWalletMoney = coinWalletMoney;
        this.coinShared = coinShared;
        this.coinUsed = coinUsed;
        this.coinExpiryDate = coinExpiryDate;
        this.coinTotalExpired = coinTotalExpired;
    }

    public String getCoinValue() {
        return coinValue;
    }

    public void setCoinValue(String coinValue) {
        this.coinValue = coinValue;
    }

    public String getCoinLastEarned() {
        return coinLastEarned;
    }

    public void setCoinLastEarned(String coinLastEarned) {
        this.coinLastEarned = coinLastEarned;
    }

    public String getCoinRequested() {
        return coinRequested;
    }

    public void setCoinRequested(String coinRequested) {
        this.coinRequested = coinRequested;
    }

    public String getCoinExchanged() {
        return coinExchanged;
    }

    public void setCoinExchanged(String coinExchanged) {
        this.coinExchanged = coinExchanged;
    }

    public String getCoinLuckRadarClaimed() {
        return coinLuckRadarClaimed;
    }

    public void setCoinLuckRadarClaimed(String coinLuckRadarClaimed) {
        this.coinLuckRadarClaimed = coinLuckRadarClaimed;
    }

    public String getCoinTotal() {
        return coinTotal;
    }

    public void setCoinTotal(String coinTotal) {
        this.coinTotal = coinTotal;
    }

    public String getCoinWalletMoney() {
        return coinWalletMoney;
    }

    public void setCoinWalletMoney(String coinWalletMoney) {
        this.coinWalletMoney = coinWalletMoney;
    }

    public String getCoinShared() {
        return coinShared;
    }

    public void setCoinShared(String coinShared) {
        this.coinShared = coinShared;
    }

    public String getCoinUsed() {
        return coinUsed;
    }

    public void setCoinUsed(String coinUsed) {
        this.coinUsed = coinUsed;
    }

    public String getCoinExpiryDate() {
        return coinExpiryDate;
    }

    public void setCoinExpiryDate(String coinExpiryDate) {
        this.coinExpiryDate = coinExpiryDate;
    }

    public String getCoinTotalExpired() {
        return coinTotalExpired;
    }

    public void setCoinTotalExpired(String coinTotalExpired) {
        this.coinTotalExpired = coinTotalExpired;
    }

}
