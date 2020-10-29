
package com.orgaes.ls.RETROFIT_NEW.Wishlist_master;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Master_Response {

    @SerializedName("wishlist_user")
    @Expose
    private List<Data_WishlistUser> wishlistUser = null;
    @SerializedName("wishlist_master")
    @Expose
    private List<Data_WishlistMaster> wishlistMaster = null;
    @SerializedName("wishlist_expiry")
    @Expose
    private String wishlistExpiry;
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
    public Master_Response() {
    }

    /**
     * 
     * @param wishlistUser
     * @param wishlistMaster
     * @param wishlistExpiry
     */
    public Master_Response(List<Data_WishlistUser> wishlistUser, List<Data_WishlistMaster> wishlistMaster, String wishlistExpiry) {
        super();
        this.wishlistUser = wishlistUser;
        this.wishlistMaster = wishlistMaster;
        this.wishlistExpiry = wishlistExpiry;
    }

    public List<Data_WishlistUser> getWishlistUser() {
        return wishlistUser;
    }

    public void setWishlistUser(List<Data_WishlistUser> wishlistUser) {
        this.wishlistUser = wishlistUser;
    }

    public List<Data_WishlistMaster> getWishlistMaster() {
        return wishlistMaster;
    }

    public void setWishlistMaster(List<Data_WishlistMaster> wishlistMaster) {
        this.wishlistMaster = wishlistMaster;
    }

    public String getWishlistExpiry() {
        return wishlistExpiry;
    }

    public void setWishlistExpiry(String wishlistExpiry) {
        this.wishlistExpiry = wishlistExpiry;
    }

}
