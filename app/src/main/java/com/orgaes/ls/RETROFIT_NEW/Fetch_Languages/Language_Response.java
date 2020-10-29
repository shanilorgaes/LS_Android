
package com.orgaes.ls.RETROFIT_NEW.Fetch_Languages;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Language_Response {

    @SerializedName("langugaes")
    @Expose
    private List<Langugaedata> langugaes = null;
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
    public Language_Response() {
    }

    /**
     * 
     * @param langugaes
     */
    public Language_Response(List<Langugaedata> langugaes) {
        super();
        this.langugaes = langugaes;
    }

    public List<Langugaedata> getLangugaes() {
        return langugaes;
    }

    public void setLangugaes(List<Langugaedata> langugaes) {
        this.langugaes = langugaes;
    }

}
