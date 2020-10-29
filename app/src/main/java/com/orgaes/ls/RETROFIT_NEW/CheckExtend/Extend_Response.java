
package com.orgaes.ls.RETROFIT_NEW.CheckExtend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Extend_Response {

    @SerializedName("message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Extend_Response() {
    }

    /**
     * 
     * @param message
     */
    public Extend_Response(String message) {
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
