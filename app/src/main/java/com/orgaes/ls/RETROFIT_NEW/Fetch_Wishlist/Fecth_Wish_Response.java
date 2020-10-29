
package com.orgaes.ls.RETROFIT_NEW.Fetch_Wishlist;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fecth_Wish_Response {

    @SerializedName("wishlist")
    @Expose
    private List<Fettch_data_Wishlist> wishlist = null;
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
    public Fecth_Wish_Response() {
    }

    /**
     * 
     * @param wishlist
     */
    public Fecth_Wish_Response(List<Fettch_data_Wishlist> wishlist) {
        super();
        this.wishlist = wishlist;
    }

    public List<Fettch_data_Wishlist> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<Fettch_data_Wishlist> wishlist) {
        this.wishlist = wishlist;
    }

}
