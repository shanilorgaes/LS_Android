
package com.orgaes.ls.RETROFIT_NEW.WishlistMASTERDATA;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WishlistMasterResponse {

    @SerializedName("wishlist_user")
    @Expose
    private List<WishlistMasterWishlistUser> wishlistUser = null;
    @SerializedName("wishlist_master")
    @Expose
    private List<WishlistMasterDetails> wishlistMaster = null;
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
    public WishlistMasterResponse() {
    }

    /**
     * 
     * @param wishlistUser
     * @param wishlistMaster
     * @param wishlistExpiry
     */
    public WishlistMasterResponse(List<WishlistMasterWishlistUser> wishlistUser, List<WishlistMasterDetails> wishlistMaster, String wishlistExpiry) {
        super();
        this.wishlistUser = wishlistUser;
        this.wishlistMaster = wishlistMaster;
        this.wishlistExpiry = wishlistExpiry;
    }

    public List<WishlistMasterWishlistUser> getWishlistUser() {
        return wishlistUser;
    }

    public void setWishlistUser(List<WishlistMasterWishlistUser> wishlistUser) {
        this.wishlistUser = wishlistUser;
    }

    public List<WishlistMasterDetails> getWishlistMaster() {
        return wishlistMaster;
    }

    public void setWishlistMaster(List<WishlistMasterDetails> wishlistMaster) {
        this.wishlistMaster = wishlistMaster;
    }

    public String getWishlistExpiry() {
        return wishlistExpiry;
    }

    public void setWishlistExpiry(String wishlistExpiry) {
        this.wishlistExpiry = wishlistExpiry;
    }

}
