
package com.orgaes.ls.RETROFIT_NEW.WishlistMASTERDATA;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WishlistMasterDetails {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("wish_name")
    @Expose
    private String wishName;

    /**
     * No args constructor for use in serialization
     * 
     */
    public WishlistMasterDetails() {
    }

    /**
     * 
     * @param wishName
     * @param id
     */
    public WishlistMasterDetails(String id, String wishName) {
        super();
        this.id = id;
        this.wishName = wishName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

}
