
package com.orgaes.ls.RETROFIT_NEW.CollectCoin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CollectCoin {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private Collect_Coin_Response response;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CollectCoin() {
    }

    /**
     * 
     * @param code
     * @param response
     * @param message
     * @param status
     */
    public CollectCoin(Integer code, String message, String status, Collect_Coin_Response response) {
        super();
        this.code = code;
        this.message = message;
        this.status = status;
        this.response = response;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Collect_Coin_Response getResponse() {
        return response;
    }

    public void setResponse(Collect_Coin_Response response) {
        this.response = response;
    }

}
