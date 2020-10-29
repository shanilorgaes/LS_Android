
package com.orgaes.ls.RETROFIT_NEW.ExtendTime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Extend_Times_Extend {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("coin")
    @Expose
    private String coin;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Extend_Times_Extend() {
    }

    /**
     * 
     * @param duration
     * @param id
     * @param coin
     * @param status
     */
    public Extend_Times_Extend(String id, String duration, String coin, String status) {
        super();
        this.id = id;
        this.duration = duration;
        this.coin = coin;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
