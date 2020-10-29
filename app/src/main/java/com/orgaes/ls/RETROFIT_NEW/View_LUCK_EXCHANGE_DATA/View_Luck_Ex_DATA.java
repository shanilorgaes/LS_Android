
package com.orgaes.ls.RETROFIT_NEW.View_LUCK_EXCHANGE_DATA;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class View_Luck_Ex_DATA {

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
    private Luck_Ext_Response response;

    /**
     * No args constructor for use in serialization
     * 
     */
    public View_Luck_Ex_DATA() {
    }

    /**
     * 
     * @param code
     * @param response
     * @param message
     * @param status
     */
    public View_Luck_Ex_DATA(Integer code, String message, String status, Luck_Ext_Response response) {
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

    public Luck_Ext_Response getResponse() {
        return response;
    }

    public void setResponse(Luck_Ext_Response response) {
        this.response = response;
    }

}
