
package com.orgaes.ls.RETROFIT_NEW.DrawWinners;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Draw_Winners_Response {

    @SerializedName("winners")
    @Expose
    private List<Draw_Winners_Winner> winners = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Draw_Winners_Response() {
    }

    /**
     * 
     * @param winners
     */
    public Draw_Winners_Response(List<Draw_Winners_Winner> winners) {
        super();
        this.winners = winners;
    }

    public List<Draw_Winners_Winner> getWinners() {
        return winners;
    }

    public void setWinners(List<Draw_Winners_Winner> winners) {
        this.winners = winners;
    }

}
