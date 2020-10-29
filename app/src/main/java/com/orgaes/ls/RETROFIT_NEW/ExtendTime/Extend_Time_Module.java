
package com.orgaes.ls.RETROFIT_NEW.ExtendTime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Extend_Time_Module {

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
    private Extend_TimeResponse response;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Extend_Time_Module() {
    }

    /**
     * 
     * @param code
     * @param response
     * @param message
     * @param status
     */
    public Extend_Time_Module(Integer code, String message, String status, Extend_TimeResponse response) {
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

    public Extend_TimeResponse getResponse() {
        return response;
    }

    public void setResponse(Extend_TimeResponse response) {
        this.response = response;
    }

}
