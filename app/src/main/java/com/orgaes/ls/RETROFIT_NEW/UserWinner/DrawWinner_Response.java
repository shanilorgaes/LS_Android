
package com.orgaes.ls.RETROFIT_NEW.UserWinner;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DrawWinner_Response {

    @SerializedName("draw_win")
    @Expose
    private List<DrawWin> drawWin = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DrawWinner_Response() {
    }

    /**
     * 
     * @param drawWin
     */
    public DrawWinner_Response(List<DrawWin> drawWin) {
        super();
        this.drawWin = drawWin;
    }

    public List<DrawWin> getDrawWin() {
        return drawWin;
    }

    public void setDrawWin(List<DrawWin> drawWin) {
        this.drawWin = drawWin;
    }

}
