
package com.orgaes.ls.RETROFIT_NEW.Fetch_Languages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Langugaedata {

    @SerializedName("languages_id")
    @Expose
    private String languagesId;
    @SerializedName("languages_code")
    @Expose
    private String languagesCode;
    @SerializedName("languages_name")
    @Expose
    private String languagesName;
    @SerializedName("languages_native")
    @Expose
    private String languagesNative;
    @SerializedName("languages_flag")
    @Expose
    private String languagesFlag;
    @SerializedName("languages_status")
    @Expose
    private String languagesStatus;
    @SerializedName("languages_default")
    @Expose
    private String languagesDefault;
    @SerializedName("languages_rtl_ltr")
    @Expose
    private String languagesRtlLtr;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Langugaedata() {
    }

    /**
     * 
     * @param languagesName
     * @param languagesRtlLtr
     * @param languagesStatus
     * @param languagesFlag
     * @param languagesCode
     * @param languagesId
     * @param languagesNative
     * @param languagesDefault
     */
    public Langugaedata(String languagesId, String languagesCode, String languagesName, String languagesNative, String languagesFlag, String languagesStatus, String languagesDefault, String languagesRtlLtr) {
        super();
        this.languagesId = languagesId;
        this.languagesCode = languagesCode;
        this.languagesName = languagesName;
        this.languagesNative = languagesNative;
        this.languagesFlag = languagesFlag;
        this.languagesStatus = languagesStatus;
        this.languagesDefault = languagesDefault;
        this.languagesRtlLtr = languagesRtlLtr;
    }

    public String getLanguagesId() {
        return languagesId;
    }

    public void setLanguagesId(String languagesId) {
        this.languagesId = languagesId;
    }

    public String getLanguagesCode() {
        return languagesCode;
    }

    public void setLanguagesCode(String languagesCode) {
        this.languagesCode = languagesCode;
    }

    public String getLanguagesName() {
        return languagesName;
    }

    public void setLanguagesName(String languagesName) {
        this.languagesName = languagesName;
    }

    public String getLanguagesNative() {
        return languagesNative;
    }

    public void setLanguagesNative(String languagesNative) {
        this.languagesNative = languagesNative;
    }

    public String getLanguagesFlag() {
        return languagesFlag;
    }

    public void setLanguagesFlag(String languagesFlag) {
        this.languagesFlag = languagesFlag;
    }

    public String getLanguagesStatus() {
        return languagesStatus;
    }

    public void setLanguagesStatus(String languagesStatus) {
        this.languagesStatus = languagesStatus;
    }

    public String getLanguagesDefault() {
        return languagesDefault;
    }

    public void setLanguagesDefault(String languagesDefault) {
        this.languagesDefault = languagesDefault;
    }

    public String getLanguagesRtlLtr() {
        return languagesRtlLtr;
    }

    public void setLanguagesRtlLtr(String languagesRtlLtr) {
        this.languagesRtlLtr = languagesRtlLtr;
    }

}
