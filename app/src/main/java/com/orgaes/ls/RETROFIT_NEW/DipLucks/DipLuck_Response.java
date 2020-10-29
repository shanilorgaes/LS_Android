
package com.orgaes.ls.RETROFIT_NEW.DipLucks;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DipLuck_Response {

    @SerializedName("dip_lucks")
    @Expose
    private List<DipLuck> dipLucks = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DipLuck_Response() {
    }

    /**
     * 
     * @param dipLucks
     */
    public DipLuck_Response(List<DipLuck> dipLucks) {
        super();
        this.dipLucks = dipLucks;
    }

    public List<DipLuck> getDipLucks() {
        return dipLucks;
    }

    public void setDipLucks(List<DipLuck> dipLucks) {
        this.dipLucks = dipLucks;
    }

}
