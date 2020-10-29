
package com.orgaes.ls.RETROFIT_NEW.SignIn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Signin_Response {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("authKey")
    @Expose
    private String authKey;
    @SerializedName("message")
    @Expose
    private String message;
    public String getMessage() {
        return message;
    }
    /**
     * No args constructor for use in serialization
     * 
     */
    public Signin_Response() {
    }

    /**
     * 
     * @param authKey
     * @param userId
     */
    public Signin_Response(String userId, String authKey) {
        super();
        this.userId = userId;
        this.authKey = authKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

}
