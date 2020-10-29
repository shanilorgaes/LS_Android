
package com.orgaes.ls.RETROFIT_NEW.ExtendTime;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Extend_TimeResponse {

    @SerializedName("extends")
    @Expose
    private List<Extend_Times_Extend> _extends = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Extend_TimeResponse() {
    }

    /**
     * 
     * @param _extends
     */
    public Extend_TimeResponse(List<Extend_Times_Extend> _extends) {
        super();
        this._extends = _extends;
    }

    public List<Extend_Times_Extend> getExtends() {
        return _extends;
    }

    public void setExtends(List<Extend_Times_Extend> _extends) {
        this._extends = _extends;
    }

}
