
package com.orgaes.ls.RETROFIT_NEW.Fetch_Profile;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile_Response {

    @SerializedName("user_profile")
    @Expose
    private List<UserProfile_data> userProfile = null;
    @SerializedName("user_coins")
    @Expose
    private Integer userCoins;
    @SerializedName("wishlist_expiry_date")
    @Expose
    private String wishlistExpiryDate;
    @SerializedName("user_wishlist")
    @Expose
    private List<UserWishlist_data> userWishlist = null;
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
    public Profile_Response() {
    }

    /**
     * 
     * @param wishlistExpiryDate
     * @param userCoins
     * @param userProfile
     * @param userWishlist
     */
    public Profile_Response(List<UserProfile_data> userProfile, Integer userCoins, String wishlistExpiryDate, List<UserWishlist_data> userWishlist) {
        super();
        this.userProfile = userProfile;
        this.userCoins = userCoins;
        this.wishlistExpiryDate = wishlistExpiryDate;
        this.userWishlist = userWishlist;
    }

    public List<UserProfile_data> getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(List<UserProfile_data> userProfile) {
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

    public List<UserWishlist_data> getUserWishlist() {
        return userWishlist;
    }

    public void setUserWishlist(List<UserWishlist_data> userWishlist) {
        this.userWishlist = userWishlist;
    }

}
