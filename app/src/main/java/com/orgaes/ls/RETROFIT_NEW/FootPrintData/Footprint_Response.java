
package com.orgaes.ls.RETROFIT_NEW.FootPrintData;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Footprint_Response {

    @SerializedName("footprint")
    @Expose
    private List<FootprintData> footprint = null;

    @SerializedName("pages")
    @Expose
    private Integer pages;
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
    public Footprint_Response() {
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    /**
     * 
     * @param footprint
     */
    public Footprint_Response(List<FootprintData> footprint) {
        super();
        this.footprint = footprint;
    }

    public List<FootprintData> getFootprint() {
        return footprint;
    }

    public void setFootprint(List<FootprintData> footprint) {
        this.footprint = footprint;
    }

}
