
package com.orgaes.ls.RETROFIT_NEW.PrivacyUpdate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Privacy_Response {

    @SerializedName("message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Privacy_Response() {
    }

    /**
     * 
     * @param message
     */
    public Privacy_Response(String message) {
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
