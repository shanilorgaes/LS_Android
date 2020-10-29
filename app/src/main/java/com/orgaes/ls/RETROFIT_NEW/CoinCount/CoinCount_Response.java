
package com.orgaes.ls.RETROFIT_NEW.CoinCount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoinCount_Response {

    @SerializedName("coin_count")
    @Expose
    private String coinCount;
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
    public CoinCount_Response() {
    }

    /**
     * 
     * @param coinCount
     */
    public CoinCount_Response(String coinCount) {
        super();
        this.coinCount = coinCount;
    }

    public String getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(String coinCount) {
        this.coinCount = coinCount;
    }

}
