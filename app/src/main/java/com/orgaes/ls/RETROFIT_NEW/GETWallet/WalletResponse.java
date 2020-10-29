
package com.orgaes.ls.RETROFIT_NEW.GETWallet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WalletResponse {

    @SerializedName("min_coins_requires")
    @Expose
    private String minCoinsRequires;
    @SerializedName("earned_money")
    @Expose
    private String earnedMoney;
    @SerializedName("coin_requested")
    @Expose
    private String coinRequested;
    @SerializedName("coin_exchanged_money")
    @Expose
    private String coinExchangedMoney;
    @SerializedName("coin_shared")
    @Expose
    private String coinShared;
    @SerializedName("last_redeem_money")
    @Expose
    private String lastRedeemMoney;
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
    public WalletResponse() {
    }

    /**
     * 
     * @param coinRequested
     * @param minCoinsRequires
     * @param coinShared
     * @param lastRedeemMoney
     * @param coinExchangedMoney
     * @param earnedMoney
     */
    public WalletResponse(String minCoinsRequires, String earnedMoney, String coinRequested, String coinExchangedMoney, String coinShared, String lastRedeemMoney) {
        super();
        this.minCoinsRequires = minCoinsRequires;
        this.earnedMoney = earnedMoney;
        this.coinRequested = coinRequested;
        this.coinExchangedMoney = coinExchangedMoney;
        this.coinShared = coinShared;
        this.lastRedeemMoney = lastRedeemMoney;
    }

    public String getMinCoinsRequires() {
        return minCoinsRequires;
    }

    public void setMinCoinsRequires(String minCoinsRequires) {
        this.minCoinsRequires = minCoinsRequires;
    }

    public String getEarnedMoney() {
        return earnedMoney;
    }

    public void setEarnedMoney(String earnedMoney) {
        this.earnedMoney = earnedMoney;
    }

    public String getCoinRequested() {
        return coinRequested;
    }

    public void setCoinRequested(String coinRequested) {
        this.coinRequested = coinRequested;
    }

    public String getCoinExchangedMoney() {
        return coinExchangedMoney;
    }

    public void setCoinExchangedMoney(String coinExchangedMoney) {
        this.coinExchangedMoney = coinExchangedMoney;
    }

    public String getCoinShared() {
        return coinShared;
    }

    public void setCoinShared(String coinShared) {
        this.coinShared = coinShared;
    }

    public String getLastRedeemMoney() {
        return lastRedeemMoney;
    }

    public void setLastRedeemMoney(String lastRedeemMoney) {
        this.lastRedeemMoney = lastRedeemMoney;
    }

}
