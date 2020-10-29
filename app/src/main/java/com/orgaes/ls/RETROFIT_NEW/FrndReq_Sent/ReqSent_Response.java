
package com.orgaes.ls.RETROFIT_NEW.FrndReq_Sent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqSent_Response {

    @SerializedName("message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReqSent_Response() {
    }

    /**
     * 
     * @param message
     */
    public ReqSent_Response(String message) {
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
