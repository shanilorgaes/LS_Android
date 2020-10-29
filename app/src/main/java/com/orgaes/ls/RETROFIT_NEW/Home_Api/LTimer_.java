
package com.orgaes.ls.RETROFIT_NEW.Home_Api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LTimer_ {

    @SerializedName("time")
    @Expose
    private String time;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LTimer_() {
    }

    /**
     * 
     * @param time
     */
    public LTimer_(String time) {
        super();
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
