
package com.orgaes.ls.RETROFIT_NEW.Notifications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WishlistNotification {

    @SerializedName("notification_id")
    @Expose
    private String notificationId;
    @SerializedName("notification_name")
    @Expose
    private String notificationName;
    @SerializedName("edition")
    @Expose
    private String edition;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("gender_Id")
    @Expose
    private String genderId;
    @SerializedName("age_group")
    @Expose
    private String ageGroup;
    @SerializedName("wishlist_trans_id")
    @Expose
    private String wishlistTransId;
    @SerializedName("wishlist_trans_master_id")
    @Expose
    private String wishlistTransMasterId;
    @SerializedName("wishlist_trans_language_code")
    @Expose
    private String wishlistTransLanguageCode;
    @SerializedName("wishlist_trans_name")
    @Expose
    private String wishlistTransName;
    @SerializedName("edition_id")
    @Expose
    private String editionId;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("state_id")
    @Expose
    private String stateId;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("edition_name")
    @Expose
    private String editionName;
    @SerializedName("x1_latitude")
    @Expose
    private String x1Latitude;
    @SerializedName("y1_longitude")
    @Expose
    private String y1Longitude;
    @SerializedName("x2_latitude")
    @Expose
    private String x2Latitude;
    @SerializedName("y2_longitude")
    @Expose
    private String y2Longitude;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("unit")
    @Expose
    private String unit;

    /**
     * No args constructor for use in serialization
     * 
     */
    public WishlistNotification() {
    }

    /**
     * 
     * @param pincode
     * @param wishlistTransId
     * @param y2Longitude
     * @param distance
     * @param editionName
     * @param stateId
     * @param genderId
     * @param editionId
     * @param edition
     * @param x1Latitude
     * @param language
     * @param ageGroup
     * @param wishlistTransMasterId
     * @param countryId
     * @param wishlistTransName
     * @param unit
     * @param x2Latitude
     * @param district
     * @param notificationId
     * @param wishlistTransLanguageCode
     * @param location
     * @param category
     * @param notificationName
     * @param y1Longitude
     */
    public WishlistNotification(String notificationId, String notificationName, String edition, String category, String genderId, String ageGroup, String wishlistTransId, String wishlistTransMasterId, String wishlistTransLanguageCode, String wishlistTransName, String editionId, String countryId, String stateId, String district, String location, String pincode, String language, String editionName, String x1Latitude, String y1Longitude, String x2Latitude, String y2Longitude, String distance, String unit) {
        super();
        this.notificationId = notificationId;
        this.notificationName = notificationName;
        this.edition = edition;
        this.category = category;
        this.genderId = genderId;
        this.ageGroup = ageGroup;
        this.wishlistTransId = wishlistTransId;
        this.wishlistTransMasterId = wishlistTransMasterId;
        this.wishlistTransLanguageCode = wishlistTransLanguageCode;
        this.wishlistTransName = wishlistTransName;
        this.editionId = editionId;
        this.countryId = countryId;
        this.stateId = stateId;
        this.district = district;
        this.location = location;
        this.pincode = pincode;
        this.language = language;
        this.editionName = editionName;
        this.x1Latitude = x1Latitude;
        this.y1Longitude = y1Longitude;
        this.x2Latitude = x2Latitude;
        this.y2Longitude = y2Longitude;
        this.distance = distance;
        this.unit = unit;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationName() {
        return notificationName;
    }

    public void setNotificationName(String notificationName) {
        this.notificationName = notificationName;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGenderId() {
        return genderId;
    }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getWishlistTransId() {
        return wishlistTransId;
    }

    public void setWishlistTransId(String wishlistTransId) {
        this.wishlistTransId = wishlistTransId;
    }

    public String getWishlistTransMasterId() {
        return wishlistTransMasterId;
    }

    public void setWishlistTransMasterId(String wishlistTransMasterId) {
        this.wishlistTransMasterId = wishlistTransMasterId;
    }

    public String getWishlistTransLanguageCode() {
        return wishlistTransLanguageCode;
    }

    public void setWishlistTransLanguageCode(String wishlistTransLanguageCode) {
        this.wishlistTransLanguageCode = wishlistTransLanguageCode;
    }

    public String getWishlistTransName() {
        return wishlistTransName;
    }

    public void setWishlistTransName(String wishlistTransName) {
        this.wishlistTransName = wishlistTransName;
    }

    public String getEditionId() {
        return editionId;
    }

    public void setEditionId(String editionId) {
        this.editionId = editionId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEditionName() {
        return editionName;
    }

    public void setEditionName(String editionName) {
        this.editionName = editionName;
    }

    public String getX1Latitude() {
        return x1Latitude;
    }

    public void setX1Latitude(String x1Latitude) {
        this.x1Latitude = x1Latitude;
    }

    public String getY1Longitude() {
        return y1Longitude;
    }

    public void setY1Longitude(String y1Longitude) {
        this.y1Longitude = y1Longitude;
    }

    public String getX2Latitude() {
        return x2Latitude;
    }

    public void setX2Latitude(String x2Latitude) {
        this.x2Latitude = x2Latitude;
    }

    public String getY2Longitude() {
        return y2Longitude;
    }

    public void setY2Longitude(String y2Longitude) {
        this.y2Longitude = y2Longitude;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
