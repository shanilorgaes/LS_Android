
package com.orgaes.ls.RETROFIT_NEW.Fetch_All_Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchAllReqData {

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
    private FetchAllReqData_Response response;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FetchAllReqData() {
    }

    /**
     * 
     * @param code
     * @param response
     * @param message
     * @param status
     */
    public FetchAllReqData(Integer code, String message, String status, FetchAllReqData_Response response) {
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

    public FetchAllReqData_Response getResponse() {
        return response;
    }

    public void setResponse(FetchAllReqData_Response response) {
        this.response = response;
    }

}
