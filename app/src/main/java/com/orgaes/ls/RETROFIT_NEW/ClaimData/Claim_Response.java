
package com.orgaes.ls.RETROFIT_NEW.ClaimData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Claim_Response {

    @SerializedName("end_date")
    @Expose
    private String endDate;
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
    public Claim_Response() {
    }

    /**
     * 
     * @param endDate
     */
    public Claim_Response(String endDate) {
        super();
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
