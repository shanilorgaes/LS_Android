
package com.orgaes.ls.RETROFIT_NEW.WinnerData;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WinnerData_Response {

    @SerializedName("winner_details")
    @Expose
    private List<WinnerDetails_> winnerDetails = null;
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
    public WinnerData_Response() {
    }

    /**
     * 
     * @param winnerDetails
     */
    public WinnerData_Response(List<WinnerDetails_> winnerDetails) {
        super();
        this.winnerDetails = winnerDetails;
    }

    public List<WinnerDetails_> getWinnerDetails() {
        return winnerDetails;
    }

    public void setWinnerDetails(List<WinnerDetails_> winnerDetails) {
        this.winnerDetails = winnerDetails;
    }

}
