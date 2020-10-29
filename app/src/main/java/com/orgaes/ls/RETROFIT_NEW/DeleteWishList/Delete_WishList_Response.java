
package com.orgaes.ls.RETROFIT_NEW.DeleteWishList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Delete_WishList_Response {

    @SerializedName("message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Delete_WishList_Response() {
    }

    /**
     * 
     * @param message
     */
    public Delete_WishList_Response(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
