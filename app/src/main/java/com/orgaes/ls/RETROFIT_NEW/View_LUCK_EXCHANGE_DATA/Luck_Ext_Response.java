
package com.orgaes.ls.RETROFIT_NEW.View_LUCK_EXCHANGE_DATA;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Luck_Ext_Response {

    @SerializedName("luck_details")
    @Expose
    private List<View_LuckDetail> luckDetails = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Luck_Ext_Response() {
    }

    /**
     * 
     * @param luckDetails
     */
    public Luck_Ext_Response(List<View_LuckDetail> luckDetails) {
        super();
        this.luckDetails = luckDetails;
    }

    public List<View_LuckDetail> getLuckDetails() {
        return luckDetails;
    }

    public void setLuckDetails(List<View_LuckDetail> luckDetails) {
        this.luckDetails = luckDetails;
    }

}
