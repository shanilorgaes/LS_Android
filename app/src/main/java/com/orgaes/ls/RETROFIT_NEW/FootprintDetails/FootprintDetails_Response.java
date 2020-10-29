
package com.orgaes.ls.RETROFIT_NEW.FootprintDetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FootprintDetails_Response {

    @SerializedName("footprint_details")
    @Expose
    private List<FootprintDetail_Data> footprintDetails = null;
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
    public FootprintDetails_Response() {
    }

    /**
     * 
     * @param footprintDetails
     */
    public FootprintDetails_Response(List<FootprintDetail_Data> footprintDetails) {
        super();
        this.footprintDetails = footprintDetails;
    }

    public List<FootprintDetail_Data> getFootprintDetails() {
        return footprintDetails;
    }

    public void setFootprintDetails(List<FootprintDetail_Data> footprintDetails) {
        this.footprintDetails = footprintDetails;
    }

}
