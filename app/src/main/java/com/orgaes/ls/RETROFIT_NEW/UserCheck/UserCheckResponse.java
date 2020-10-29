
package com.orgaes.ls.RETROFIT_NEW.UserCheck;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserCheckResponse {

    @SerializedName("authKey")
    @Expose
    private String authKey;
    @SerializedName("usercoin")
    @Expose
    private String usercoin;
    @SerializedName("userType")
    @Expose
    private String userType;
    @SerializedName("userprofile")
    @Expose
    private List<UserCheck_Userprofile> userprofile = null;
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
    public UserCheckResponse() {
    }

    public String getUsercoin() {
        return usercoin;
    }

    public void setUsercoin(String usercoin) {
        this.usercoin = usercoin;
    }

    /**
     * 
     * @param authKey
     * @param userprofile
     * @param userType
     */
    public UserCheckResponse(String authKey, String userType, List<UserCheck_Userprofile> userprofile) {
        super();
        this.authKey = authKey;
        this.userType = userType;
        this.userprofile = userprofile;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<UserCheck_Userprofile> getUserprofile() {
        return userprofile;
    }

    public void setUserprofile(List<UserCheck_Userprofile> userprofile) {
        this.userprofile = userprofile;
    }

}
