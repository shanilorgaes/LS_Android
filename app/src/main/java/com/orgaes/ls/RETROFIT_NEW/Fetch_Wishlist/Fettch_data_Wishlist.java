
package com.orgaes.ls.RETROFIT_NEW.Fetch_Wishlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fettch_data_Wishlist {

    @SerializedName("id")
    @Expose
    private String wishlistTransId;
    @SerializedName("wish_name")
    @Expose
    private String wishlistTransName;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Fettch_data_Wishlist() {
    }

    /**
     * 
     * @param wishlistTransId
     * @param wishlistTransLanguageCode
     * @param wishlistTransMasterId
     * @param wishlistTransName
     */
    public Fettch_data_Wishlist(String wishlistTransId, String wishlistTransMasterId, String wishlistTransLanguageCode, String wishlistTransName) {
        super();
        this.wishlistTransId = wishlistTransId;

        this.wishlistTransName = wishlistTransName;
    }

    public String getWishlistTransId() {
        return wishlistTransId;
    }

    public void setWishlistTransId(String wishlistTransId) {
        this.wishlistTransId = wishlistTransId;
    }



    public String getWishlistTransName() {
        return wishlistTransName;
    }

    public void setWishlistTransName(String wishlistTransName) {
        this.wishlistTransName = wishlistTransName;
    }

}
