
package com.orgaes.ls.RETROFIT_NEW.CoinCount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoinCountData {

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
    private CoinCount_Response response;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CoinCountData() {
    }

    /**
     * 
     * @param code
     * @param response
     * @param message
     * @param status
     */
    public CoinCountData(Integer code, String message, String status, CoinCount_Response response) {
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

    public CoinCount_Response getResponse() {
        return response;
    }

    public void setResponse(CoinCount_Response response) {
        this.response = response;
    }

}
