
package com.orgaes.ls.RETROFIT_NEW.AcceptReques;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccptReq_Response {

    @SerializedName("message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AccptReq_Response() {
    }

    /**
     * 
     * @param message
     */
    public AccptReq_Response(String message) {
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
