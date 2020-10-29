
package com.orgaes.ls.RETROFIT_NEW.EnrollLuck;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Enroll_Luck_Model {

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
    private Enroll_LuckResponse response;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Enroll_Luck_Model() {
    }

    /**
     * 
     * @param code
     * @param response
     * @param message
     * @param status
     */
    public Enroll_Luck_Model(Integer code, String message, String status, Enroll_LuckResponse response) {
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

    public Enroll_LuckResponse getResponse() {
        return response;
    }

    public void setResponse(Enroll_LuckResponse response) {
        this.response = response;
    }

}
