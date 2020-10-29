
package com.orgaes.ls.RETROFIT_NEW.Home_Api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Home_Response {

    @SerializedName("ads_random")
    @Expose
    private List<AdsRandom> adsRandom = null;
    @SerializedName("ads_sequence")
    @Expose
    private List<AdsSequence> adsSequence = null;
    @SerializedName("ads_format")
    @Expose
    private List<AdsFormat> adsFormat = null;
    @SerializedName("ads_default")
    @Expose
    private List<AdsDefault> adsDefault = null;
    @SerializedName("about_us")
    @Expose
    private List<AboutU> aboutUs = null;
    @SerializedName("coin_value")
    @Expose
    private List<CoinValue> coinValue = null;
    @SerializedName("menu_list")
    @Expose
    private List<MenuList> menuList = null;
    @SerializedName("faq_list")
    @Expose
    private List<FaqList> faqList = null;
    @SerializedName("user_profile")
    @Expose
    private List<UserProfile> userProfile = null;
    @SerializedName("user_coins")
    @Expose
    private String userCoins;
    @SerializedName("user_wishlist")
    @Expose
    private List<UserWishlist> userWishlist = null;
    @SerializedName("todays_winners")
    @Expose
    private List<TodaysWinner> todaysWinners = null;
    @SerializedName("todays_luck")
    @Expose
    private List<TodaysLuck> todaysLuck = null;
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
    public Home_Response() {
    }

    /**
     * 
     * @param aboutUs
     * @param menuList
     * @param coinValue
     * @param faqList
     * @param userProfile
     * @param userWishlist
     * @param adsSequence
     * @param adsFormat
     * @param adsDefault
     * @param userCoins
     * @param todaysWinners
     * @param adsRandom
     * @param todaysLuck
     */
    public Home_Response(List<AdsRandom> adsRandom, List<AdsSequence> adsSequence, List<AdsFormat> adsFormat, List<AdsDefault> adsDefault, List<AboutU> aboutUs, List<CoinValue> coinValue, List<MenuList> menuList, List<FaqList> faqList, List<UserProfile> userProfile, String userCoins, List<UserWishlist> userWishlist, List<TodaysWinner> todaysWinners, List<TodaysLuck> todaysLuck) {
        super();
        this.adsRandom = adsRandom;
        this.adsSequence = adsSequence;
        this.adsFormat = adsFormat;
        this.adsDefault = adsDefault;
        this.aboutUs = aboutUs;
        this.coinValue = coinValue;
        this.menuList = menuList;
        this.faqList = faqList;
        this.userProfile = userProfile;
        this.userCoins = userCoins;
        this.userWishlist = userWishlist;
        this.todaysWinners = todaysWinners;
        this.todaysLuck = todaysLuck;
    }

    public List<AdsRandom> getAdsRandom() {
        return adsRandom;
    }

    public void setAdsRandom(List<AdsRandom> adsRandom) {
        this.adsRandom = adsRandom;
    }

    public List<AdsSequence> getAdsSequence() {
        return adsSequence;
    }

    public void setAdsSequence(List<AdsSequence> adsSequence) {
        this.adsSequence = adsSequence;
    }

    public List<AdsFormat> getAdsFormat() {
        return adsFormat;
    }

    public void setAdsFormat(List<AdsFormat> adsFormat) {
        this.adsFormat = adsFormat;
    }

    public List<AdsDefault> getAdsDefault() {
        return adsDefault;
    }

    public void setAdsDefault(List<AdsDefault> adsDefault) {
        this.adsDefault = adsDefault;
    }

    public List<AboutU> getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(List<AboutU> aboutUs) {
        this.aboutUs = aboutUs;
    }

    public List<CoinValue> getCoinValue() {
        return coinValue;
    }

    public void setCoinValue(List<CoinValue> coinValue) {
        this.coinValue = coinValue;
    }

    public List<MenuList> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuList> menuList) {
        this.menuList = menuList;
    }

    public List<FaqList> getFaqList() {
        return faqList;
    }

    public void setFaqList(List<FaqList> faqList) {
        this.faqList = faqList;
    }

    public List<UserProfile> getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(List<UserProfile> userProfile) {
        this.userProfile = userProfile;
    }

    public String getUserCoins() {
        return userCoins;
    }

    public void setUserCoins(String userCoins) {
        this.userCoins = userCoins;
    }

    public List<UserWishlist> getUserWishlist() {
        return userWishlist;
    }

    public void setUserWishlist(List<UserWishlist> userWishlist) {
        this.userWishlist = userWishlist;
    }

    public List<TodaysWinner> getTodaysWinners() {
        return todaysWinners;
    }

    public void setTodaysWinners(List<TodaysWinner> todaysWinners) {
        this.todaysWinners = todaysWinners;
    }

    public List<TodaysLuck> getTodaysLuck() {
        return todaysLuck;
    }

    public void setTodaysLuck(List<TodaysLuck> todaysLuck) {
        this.todaysLuck = todaysLuck;
    }

}
