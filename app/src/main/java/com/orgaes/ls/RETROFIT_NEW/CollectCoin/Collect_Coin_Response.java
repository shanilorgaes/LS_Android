
package com.orgaes.ls.RETROFIT_NEW.CollectCoin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Collect_Coin_Response {

    @SerializedName("message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Collect_Coin_Response() {
    }

    /**
     * 
     * @param message
     */
    public Collect_Coin_Response(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
