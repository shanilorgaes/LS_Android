
package com.orgaes.ls.RETROFIT_NEW.POSTEXTEND;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POstExtend_Response {

    @SerializedName("end_date")
    @Expose
    private String endDate;

    /**
     * No args constructor for use in serialization
     * 
     */
    public POstExtend_Response() {
    }

    /**
     * 
     * @param endDate
     */
    public POstExtend_Response(String endDate) {
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
