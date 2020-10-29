
package com.orgaes.ls.RETROFIT_NEW.DipLucks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DipLucks_Model {

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
    private DipLuck_Response response;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DipLucks_Model() {
    }

    /**
     * 
     * @param code
     * @param response
     * @param message
     * @param status
     */
    public DipLucks_Model(Integer code, String message, String status, DipLuck_Response response) {
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

    public DipLuck_Response getResponse() {
        return response;
    }

    public void setResponse(DipLuck_Response response) {
        this.response = response;
    }

}
