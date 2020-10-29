
package com.orgaes.ls.RETROFIT_NEW.LuckBox_Data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LuckBoxResponse {

    @SerializedName("last_luck_win_date")
    @Expose
    private String lastLuckWinDate;
    @SerializedName("box_items")
    @Expose
    private List<BoxItem_> boxItems = null;
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
    public LuckBoxResponse() {
    }

    /**
     * 
     * @param boxItems
     * @param lastLuckWinDate
     */
    public LuckBoxResponse(String lastLuckWinDate, List<BoxItem_> boxItems) {
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

    public List<BoxItem_> getBoxItems() {
        return boxItems;
    }

    public void setBoxItems(List<BoxItem_> boxItems) {
        this.boxItems = boxItems;
    }

}
