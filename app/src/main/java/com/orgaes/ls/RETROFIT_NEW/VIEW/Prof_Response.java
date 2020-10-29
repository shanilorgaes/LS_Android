
package com.orgaes.ls.RETROFIT_NEW.VIEW;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prof_Response {

    @SerializedName("user_profile")
    @Expose
    private List<Prof_UserProfile> userProfile = null;
    @SerializedName("user_coins")
    @Expose
    private Integer userCoins;
    @SerializedName("wishlist_expiry_date")
    @Expose
    private String wishlistExpiryDate;
    @SerializedName("user_wishlist")
    @Expose
    private List<Prof_UserWishlist> userWishlist = null;
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
    public Prof_Response() {
    }

    /**
     * 
     * @param wishlistExpiryDate
     * @param userCoins
     * @param userProfile
     * @param userWishlist
     */
    public Prof_Response(List<Prof_UserProfile> userProfile, Integer userCoins, String wishlistExpiryDate, List<Prof_UserWishlist> userWishlist) {
        super();
        this.userProfile = userProfile;
        this.userCoins = userCoins;
        this.wishlistExpiryDate = wishlistExpiryDate;
        this.userWishlist = userWishlist;
    }

    public List<Prof_UserProfile> getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(List<Prof_UserProfile> userProfile) {
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

    public List<Prof_UserWishlist> getUserWishlist() {
        return userWishlist;
    }

    public void setUserWishlist(List<Prof_UserWishlist> userWishlist) {
        this.userWishlist = userWishlist;
    }

}
