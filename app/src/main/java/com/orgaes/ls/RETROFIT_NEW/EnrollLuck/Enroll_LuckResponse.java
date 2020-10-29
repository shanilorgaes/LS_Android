
package com.orgaes.ls.RETROFIT_NEW.EnrollLuck;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Enroll_LuckResponse {

    @SerializedName("message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Enroll_LuckResponse() {
    }

    /**
     * 
     * @param message
     */
    public Enroll_LuckResponse(String message) {
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
