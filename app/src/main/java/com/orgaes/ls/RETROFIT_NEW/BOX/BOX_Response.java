
package com.orgaes.ls.RETROFIT_NEW.BOX;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BOX_Response {

    @SerializedName("last_luck_win_date")
    @Expose
    private String lastLuckWinDate;
    @SerializedName("box_items")
    @Expose
    private List<BOX_BoxItem> boxItems = null;
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
    public BOX_Response() {
    }

    /**
     * 
     * @param boxItems
     * @param lastLuckWinDate
     */
    public BOX_Response(String lastLuckWinDate, List<BOX_BoxItem> boxItems) {
        super();
        this.lastLuckWinDate = lastLuckWinDate;
        this.boxItems = boxItems;
    }

    public String getLastLuckWinDate() {
        return lastLuckWinDate;
    }

    public void setLastLuckWinDate(String lastLuckWinDate) {
        this.lastLuckWinDate = lastLuckWinDate;
    }

    public List<BOX_BoxItem> getBoxItems() {
        return boxItems;
    }

    public void setBoxItems(List<BOX_BoxItem> boxItems) {
        this.boxItems = boxItems;
    }

}
