
package com.orgaes.ls.RETROFIT_NEW.FootPrintData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FootprintData {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("type")
    @Expose
    private String type;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FootprintData() {
    }

    /**
     * 
     * @param date
     * @param id
     * @param time
     * @param type
     */
    public FootprintData(String id, String date, String time, String type) {
        super();
        this.id = id;
        this.date = date;
        this.time = time;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
