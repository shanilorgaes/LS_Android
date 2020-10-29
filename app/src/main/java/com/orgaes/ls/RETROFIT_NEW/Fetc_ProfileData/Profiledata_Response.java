
package com.orgaes.ls.RETROFIT_NEW.Fetc_ProfileData;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profiledata_Response {

    @SerializedName("user_profile")
    @Expose
    private List<UserProfile_data_Module> userProfile = null;
    @SerializedName("user_coins")
    @Expose
    private Integer userCoins;
    @SerializedName("wishlist_expiry_date")
    @Expose
    private String wishlistExpiryDate;
    @SerializedName("user_wishlist")
    @Expose
    private List<UserWishlist_Data> userWishlist = null;
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
    public Profiledata_Response() {
    }

    /**
     * 
     * @param wishlistExpiryDate
     * @param userCoins
     * @param userProfile
     * @param userWishlist
     */
    public Profiledata_Response(List<UserProfile_data_Module> userProfile, Integer userCoins, String wishlistExpiryDate, List<UserWishlist_Data> userWishlist) {
        super();
        this.userProfile = userProfile;
        this.userCoins = userCoins;
        this.wishlistExpiryDate = wishlistExpiryDate;
        this.userWishlist = userWishlist;
    }

    public List<UserProfile_data_Module> getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(List<UserProfile_data_Module> userProfile) {
        this.userProfile = userProfile;
    }

    public Integer getUserCoins() {
        return userCoins;
    }

    public void setUserCoins(Integer userCoins) {
        this.userCoins = userCoins;
    }

    public String getWishlistExpiryDate() {
        return wishlistExpiryDate;
    }

    public void setWishlistExpiryDate(String wishlistExpiryDate) {
        this.wishlistExpiryDate = wishlistExpiryDate;
    }

    public List<UserWishlist_Data> getUserWishlist() {
        return userWishlist;
    }

    public void setUserWishlist(List<UserWishlist_Data> userWishlist) {
        this.userWishlist = userWishlist;
    }

}
