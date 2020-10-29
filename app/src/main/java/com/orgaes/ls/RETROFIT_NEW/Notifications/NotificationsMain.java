
package com.orgaes.ls.RETROFIT_NEW.Notifications;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationsMain {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("wishlist_notifications")
    @Expose
    private List<WishlistNotification> wishlistNotifications = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public NotificationsMain() {
    }

    /**
     * 
     * @param code
     * @param message
     * @param status
     * @param wishlistNotifications
     */
    public NotificationsMain(Integer code, String status, String message, List<WishlistNotification> wishlistNotifications) {
        super();
        this.code = code;
        this.status = status;
        this.message = message;
        this.wishlistNotifications = wishlistNotifications;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<WishlistNotification> getWishlistNotifications() {
        return wishlistNotifications;
    }

    public void setWishlistNotifications(List<WishlistNotification> wishlistNotifications) {
        this.wishlistNotifications = wishlistNotifications;
    }

}
