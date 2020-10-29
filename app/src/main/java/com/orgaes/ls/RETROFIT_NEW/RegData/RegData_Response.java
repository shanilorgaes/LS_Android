
package com.orgaes.ls.RETROFIT_NEW.RegData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class RegData_Response {

    @SerializedName("authKey")
    @Expose
    private String authKey;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_profession")
    @Expose
    private String userProfession;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("user_gender")
    @Expose
    private String userGender;
    @SerializedName("user_age_group")
    @Expose
    private String userAgeGroup;
    @SerializedName("user_wishlist")
    @Expose
    private String userWishlist;
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
    public RegData_Response() {
    }

    /**
     *
     * @param authKey
     * @param userProfession
     * @param userPhone
     * @param userGender
     * @param userName
     * @param userAgeGroup
     * @param userWishlist
     */
    public RegData_Response(String authKey, String userName, String userProfession, String userPhone, String userGender, String userAgeGroup, String userWishlist) {
        super();
        this.authKey = authKey;
        this.userName = userName;
        this.userProfession = userProfession;
        this.userPhone = userPhone;
        this.userGender = userGender;
        this.userAgeGroup = userAgeGroup;
        this.userWishlist = userWishlist;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserProfession() {
        return userProfession;
    }

    public void setUserProfession(String userProfession) {
        this.userProfession = userProfession;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserAgeGroup() {
        return userAgeGroup;
    }

    public void setUserAgeGroup(String userAgeGroup) {
        this.userAgeGroup = userAgeGroup;
    }

    public String getUserWishlist() {
        return userWishlist;
    }

    public void setUserWishlist(String userWishlist) {
        this.userWishlist = userWishlist;
    }

}