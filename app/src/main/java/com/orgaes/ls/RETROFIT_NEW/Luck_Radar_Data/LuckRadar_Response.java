
package com.orgaes.ls.RETROFIT_NEW.Luck_Radar_Data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LuckRadar_Response {

    @SerializedName("luck_radar")
    @Expose
    private List<LuckRadar_Details> luckRadar = null;
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
    public LuckRadar_Response() {
    }

    /**
     * 
     * @param luckRadar
     */
    public LuckRadar_Response(List<LuckRadar_Details> luckRadar) {
        super();
        this.luckRadar = luckRadar;
    }

    public List<LuckRadar_Details> getLuckRadar() {
        return luckRadar;
    }

    public void setLuckRadar(List<LuckRadar_Details> luckRadar) {
        this.luckRadar = luckRadar;
    }

}
