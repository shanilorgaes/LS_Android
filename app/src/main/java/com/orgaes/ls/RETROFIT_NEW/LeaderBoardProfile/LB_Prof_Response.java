
package com.orgaes.ls.RETROFIT_NEW.LeaderBoardProfile;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LB_Prof_Response {

    @SerializedName("user_profile")
    @Expose
    private List<LB_Prof_UserProfile> userProfile = null;
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
    public LB_Prof_Response() {
    }

    /**
     * 
     * @param userProfile
     */
    public LB_Prof_Response(List<LB_Prof_UserProfile> userProfile) {
        super();
        this.userProfile = userProfile;
    }

    public List<LB_Prof_UserProfile> getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(List<LB_Prof_UserProfile> userProfile) {
        this.userProfile = userProfile;
    }

}
