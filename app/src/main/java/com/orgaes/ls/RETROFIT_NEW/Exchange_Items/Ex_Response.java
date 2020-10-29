
package com.orgaes.ls.RETROFIT_NEW.Exchange_Items;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ex_Response {

    @SerializedName("items")
    @Expose
    private List<EX_Item> items = null;
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
    public Ex_Response() {
    }

    /**
     * 
     * @param items
     */
    public Ex_Response(List<EX_Item> items) {
        super();
        this.items = items;
    }

    public List<EX_Item> getItems() {
        return items;
    }

    public void setItems(List<EX_Item> items) {
        this.items = items;
    }

}
