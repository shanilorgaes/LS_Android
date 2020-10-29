
package com.orgaes.ls.RETROFIT_NEW.InstantLuck_Data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InstantLuck_Response {

    @SerializedName("luck_details")
    @Expose
    private List<Instant_LuckDetail> luckDetails = null;
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
    public InstantLuck_Response() {
    }

    /**
     * 
     * @param luckDetails
     */
    public InstantLuck_Response(List<Instant_LuckDetail> luckDetails) {
        super();
        this.luckDetails = luckDetails;
    }

    public List<Instant_LuckDetail> getLuckDetails() {
        return luckDetails;
    }

    public void setLuckDetails(List<Instant_LuckDetail> luckDetails) {
        this.luckDetails = luckDetails;
    }

}
