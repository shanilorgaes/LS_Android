
package com.orgaes.ls.RETROFIT_NEW.CoinRATE;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoinRate {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("coin_value")
    @Expose
    private String coinValue;
    @SerializedName("min_coin_count")
    @Expose
    private String minCoinCount;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CoinRate() {
    }

    /**
     * 
     * @param date
     * @param coinValue
     * @param id
     * @param time
     * @param minCoinCount
     */
    public CoinRate(String id, String coinValue, String minCoinCount, String date, String time) {
        super();
        this.id = id;
        this.coinValue = coinValue;
        this.minCoinCount = minCoinCount;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoinValue() {
        return coinValue;
    }

    public void setCoinValue(String coinValue) {
        this.coinValue = coinValue;
    }

    public String getMinCoinCount() {
        return minCoinCount;
    }

    public void setMinCoinCount(String minCoinCount) {
        this.minCoinCount = minCoinCount;
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

}
