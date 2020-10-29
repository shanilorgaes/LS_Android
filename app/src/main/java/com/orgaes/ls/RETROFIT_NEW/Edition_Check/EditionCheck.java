
package com.orgaes.ls.RETROFIT_NEW.Edition_Check;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditionCheck {

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
    private Edition_CheckResponse response;

    /**
     * No args constructor for use in serialization
     * 
     */
    public EditionCheck() {
    }

    /**
     * 
     * @param code
     * @param response
     * @param message
     * @param status
     */
    public EditionCheck(Integer code, String message, String status, Edition_CheckResponse response) {
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

    public Edition_CheckResponse getResponse() {
        return response;
    }

    public void setResponse(Edition_CheckResponse response) {
        this.response = response;
    }

}
