
package com.orgaes.ls.RETROFIT_NEW.VIEW;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prof_UserWishlist {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("wishlist_id")
    @Expose
    private String wishlistId;
    @SerializedName("wish_name")
    @Expose
    private String wishName;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Prof_UserWishlist() {
    }

    /**
     * 
     * @param wishName
     * @param id
     * @param wishlistId
     */
    public Prof_UserWishlist(String id, String wishlistId, String wishName) {
        super();
        this.id = id;
        this.wishlistId = wishlistId;
        this.wishName = wishName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(String wishlistId) {
        this.wishlistId = wishlistId;
    }

    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

}
