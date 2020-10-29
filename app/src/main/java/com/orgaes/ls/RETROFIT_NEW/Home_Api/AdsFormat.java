
package com.orgaes.ls.RETROFIT_NEW.Home_Api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdsFormat {

    @SerializedName("format")
    @Expose
    private String format;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AdsFormat() {
    }

    /**
     * 
     * @param format
     */
    public AdsFormat(String format) {
        super();
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
