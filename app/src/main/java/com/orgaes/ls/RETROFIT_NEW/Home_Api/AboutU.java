
package com.orgaes.ls.RETROFIT_NEW.Home_Api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AboutU {

    @SerializedName("about_id")
    @Expose
    private String aboutId;
    @SerializedName("about_key")
    @Expose
    private String aboutKey;
    @SerializedName("about_value")
    @Expose
    private String aboutValue;
    @SerializedName("about_content")
    @Expose
    private String aboutContent;
    @SerializedName("about_language_code")
    @Expose
    private String aboutLanguageCode;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AboutU() {
    }

    /**
     * 
     * @param aboutId
     * @param aboutLanguageCode
     * @param aboutKey
     * @param aboutValue
     * @param aboutContent
     */
    public AboutU(String aboutId, String aboutKey, String aboutValue, String aboutContent, String aboutLanguageCode) {
        super();
        this.aboutId = aboutId;
        this.aboutKey = aboutKey;
        this.aboutValue = aboutValue;
        this.aboutContent = aboutContent;
        this.aboutLanguageCode = aboutLanguageCode;
    }

    public String getAboutId() {
        return aboutId;
    }

    public void setAboutId(String aboutId) {
        this.aboutId = aboutId;
    }

    public String getAboutKey() {
        return aboutKey;
    }

    public void setAboutKey(String aboutKey) {
        this.aboutKey = aboutKey;
    }

    public String getAboutValue() {
        return aboutValue;
    }

    public void setAboutValue(String aboutValue) {
        this.aboutValue = aboutValue;
    }

    public String getAboutContent() {
        return aboutContent;
    }

    public void setAboutContent(String aboutContent) {
        this.aboutContent = aboutContent;
    }

    public String getAboutLanguageCode() {
        return aboutLanguageCode;
    }

    public void setAboutLanguageCode(String aboutLanguageCode) {
        this.aboutLanguageCode = aboutLanguageCode;
    }

}
