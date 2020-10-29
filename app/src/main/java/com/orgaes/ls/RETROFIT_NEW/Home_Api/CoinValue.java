
package com.orgaes.ls.RETROFIT_NEW.Home_Api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoinValue {

    @SerializedName("coin_id")
    @Expose
    private String coinId;
    @SerializedName("coin_date")
    @Expose
    private String coinDate;
    @SerializedName("coin_value")
    @Expose
    private String coinValue;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CoinValue() {
    }

    /**
     * 
     * @param coinId
     * @param coinValue
     * @param coinDate
     */
    public CoinValue(String coinId, String coinDate, String coinValue) {
        super();
        this.coinId = coinId;
        this.coinDate = coinDate;
        this.coinValue = coinValue;
    }

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public String getCoinDate() {
        return coinDate;
    }

    public void setCoinDate(String coinDate) {
        this.coinDate = coinDate;
    }

    public String getCoinValue() {
        return coinValue;
    }

    public void setCoinValue(String coinValue) {
        this.coinValue = coinValue;
    }

}
