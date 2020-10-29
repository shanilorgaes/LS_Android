
package com.orgaes.ls.RETROFIT_NEW.SendRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Send_Response {

    @SerializedName("message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Send_Response() {
    }

    /**
     * 
     * @param message
     */
    public Send_Response(String message) {
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
