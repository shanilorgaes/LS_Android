
package com.orgaes.ls.RETROFIT_NEW.Editions_Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditionData {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("display_name")
    @Expose
    private String displayName;

    /**
     * No args constructor for use in serialization
     * 
     */
    public EditionData() {
    }

    /**
     * 
     * @param displayName
     * @param id
     */
    public EditionData(String id, String displayName) {
        super();
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
