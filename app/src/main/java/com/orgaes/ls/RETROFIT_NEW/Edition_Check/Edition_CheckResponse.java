
package com.orgaes.ls.RETROFIT_NEW.Edition_Check;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Edition_CheckResponse {

    @SerializedName("edition")
    @Expose
    private String edition;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Edition_CheckResponse() {
    }

    /**
     * 
     * @param edition
     */
    public Edition_CheckResponse(String edition) {
        super();
        this.edition = edition;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

}
