
package com.orgaes.ls.RETROFIT_NEW.Home_New;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Home_New_Response {

    @SerializedName("ads")
    @Expose
    private List<Home_New_Ad> ads = null;
    @SerializedName("lucks")
    @Expose
    private List<Home_New_Luck> lucks = null;
    @SerializedName("user_ad_id")
    @Expose
    private Integer userAdId;
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
    public Home_New_Response() {
    }

    /**
     * 
     * @param ads
     * @param lucks
     * @param userAdId
     */
    public Home_New_Response(List<Home_New_Ad> ads, List<Home_New_Luck> lucks, Integer userAdId) {
        super();
        this.ads = ads;
        this.lucks = lucks;
        this.userAdId = userAdId;
    }

    public List<Home_New_Ad> getAds() {
        return ads;
    }

    public void setAds(List<Home_New_Ad> ads) {
        this.ads = ads;
    }

    public List<Home_New_Luck> getLucks() {
        return lucks;
    }

    public void setLucks(List<Home_New_Luck> lucks) {
        this.lucks = lucks;
    }

    public Integer getUserAdId() {
        return userAdId;
    }

    public void setUserAdId(Integer userAdId) {
        this.userAdId = userAdId;
    }

}
