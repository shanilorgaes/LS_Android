
package com.orgaes.ls.RETROFIT_NEW.Editions_Data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Edition_Response {

    @SerializedName("edition")
    @Expose
    private List<EditionData> edition = null;
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
    public Edition_Response() {
    }

    /**
     * 
     * @param edition
     */
    public Edition_Response(List<EditionData> edition) {
        super();
        this.edition = edition;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public List<EditionData> getEdition() {
        return edition;
    }

    public void setEdition(List<EditionData> edition) {
        this.edition = edition;
    }

}
