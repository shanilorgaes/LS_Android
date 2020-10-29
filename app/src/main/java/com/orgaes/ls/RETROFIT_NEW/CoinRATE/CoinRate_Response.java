
package com.orgaes.ls.RETROFIT_NEW.CoinRATE;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoinRate_Response {

    @SerializedName("coin_rate")
    @Expose
    private List<CoinRate> coinRate = null;
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
    public CoinRate_Response() {
    }

    /**
     * 
     * @param coinRate
     */
    public CoinRate_Response(List<CoinRate> coinRate) {
        super();
        this.coinRate = coinRate;
    }

    public List<CoinRate> getCoinRate() {
        return coinRate;
    }

    public void setCoinRate(List<CoinRate> coinRate) {
        this.coinRate = coinRate;
    }

}
