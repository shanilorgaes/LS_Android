
package com.orgaes.ls.RETROFIT_NEW.LuckData;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LuckData_Response {

    @SerializedName("luck_details")
    @Expose
    private List<LuckDetails_Model> luckDetails = null;
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
    public LuckData_Response() {
    }

    /**
     * 
     * @param luckDetails
     */
    public LuckData_Response(List<LuckDetails_Model> luckDetails) {
        super();
        this.luckDetails = luckDetails;
    }

    public List<LuckDetails_Model> getLuckDetails() {
        return luckDetails;
    }

    public void setLuckDetails(List<LuckDetails_Model> luckDetails) {
        this.luckDetails = luckDetails;
    }

}
