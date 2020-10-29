
package com.orgaes.ls.RETROFIT_NEW.Profile_Wishlist;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prof_Wishlist_Response {

    @SerializedName("wishlist")
    @Expose
    private List<WishlistData> wishlist = null;
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
    public Prof_Wishlist_Response() {
    }

    /**
     * 
     * @param wishlist
     */
    public Prof_Wishlist_Response(List<WishlistData> wishlist) {
        super();
        this.wishlist = wishlist;
    }

    public List<WishlistData> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<WishlistData> wishlist) {
        this.wishlist = wishlist;
    }

}
