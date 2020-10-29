
package com.orgaes.ls.RETROFIT_NEW.SignIn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post_SignIn {

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
    private Signin_Response response;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Post_SignIn() {
    }

    /**
     * 
     * @param code
     * @param response
     * @param message
     * @param status
     */
    public Post_SignIn(Integer code, String message, String status, Signin_Response response) {
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

    public Signin_Response getResponse() {
        return response;
    }

    public void setResponse(Signin_Response response) {
        this.response = response;
    }

}
