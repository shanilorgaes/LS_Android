
package com.orgaes.ls.RETROFIT_NEW.Wishlist_master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data_WishlistUser {

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
    @SerializedName("wishlist_master_id")
    @Expose
    private String wishlistMasterId;
    @SerializedName("wishlist_master_group_id")
    @Expose
    private String wishlistMasterGroupId;
    @SerializedName("wishlist_master_cat_id")
    @Expose
    private String wishlistMasterCatId;
    @SerializedName("wishlist_master_subcat_id")
    @Expose
    private String wishlistMasterSubcatId;
    @SerializedName("wishlist_master_price")
    @Expose
    private String wishlistMasterPrice;
    @SerializedName("wishlist_master_model")
    @Expose
    private String wishlistMasterModel;
    @SerializedName("wishlist_master_brand_id")
    @Expose
    private String wishlistMasterBrandId;
    @SerializedName("wishlist_master_created_by")
    @Expose
    private String wishlistMasterCreatedBy;
    @SerializedName("wishlist_master_approved_by")
    @Expose
    private String wishlistMasterApprovedBy;
    @SerializedName("wishlist_master_discount")
    @Expose
    private String wishlistMasterDiscount;
    @SerializedName("wishlist_master_created_date")
    @Expose
    private String wishlistMasterCreatedDate;
    @SerializedName("wishlist_master_updated_by")
    @Expose
    private String wishlistMasterUpdatedBy;
    @SerializedName("wishlist_master_status")
    @Expose
    private String wishlistMasterStatus;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data_WishlistUser() {
    }

    /**
     * 
     * @param wishlistTransId
     * @param wishlistMasterCreatedDate
     * @param wishlistTransMasterId
     * @param wishlistMasterId
     * @param wishlistMasterApprovedBy
     * @param wishlistTransName
     * @param wishlistMasterStatus
     * @param wishlistMasterModel
     * @param wishlistMasterCreatedBy
     * @param wishlistMasterDiscount
     * @param wishlistMasterSubcatId
     * @param wishlistMasterCatId
     * @param wishlistMasterPrice
     * @param wishlistTransLanguageCode
     * @param wishlistMasterUpdatedBy
     * @param wishlistMasterGroupId
     * @param wishlistMasterBrandId
     */
    public Data_WishlistUser(String wishlistTransId, String wishlistTransMasterId, String wishlistTransLanguageCode, String wishlistTransName, String wishlistMasterId, String wishlistMasterGroupId, String wishlistMasterCatId, String wishlistMasterSubcatId, String wishlistMasterPrice, String wishlistMasterModel, String wishlistMasterBrandId, String wishlistMasterCreatedBy, String wishlistMasterApprovedBy, String wishlistMasterDiscount, String wishlistMasterCreatedDate, String wishlistMasterUpdatedBy, String wishlistMasterStatus) {
        super();
        this.wishlistTransId = wishlistTransId;
        this.wishlistTransMasterId = wishlistTransMasterId;
        this.wishlistTransLanguageCode = wishlistTransLanguageCode;
        this.wishlistTransName = wishlistTransName;
        this.wishlistMasterId = wishlistMasterId;
        this.wishlistMasterGroupId = wishlistMasterGroupId;
        this.wishlistMasterCatId = wishlistMasterCatId;
        this.wishlistMasterSubcatId = wishlistMasterSubcatId;
        this.wishlistMasterPrice = wishlistMasterPrice;
        this.wishlistMasterModel = wishlistMasterModel;
        this.wishlistMasterBrandId = wishlistMasterBrandId;
        this.wishlistMasterCreatedBy = wishlistMasterCreatedBy;
        this.wishlistMasterApprovedBy = wishlistMasterApprovedBy;
        this.wishlistMasterDiscount = wishlistMasterDiscount;
        this.wishlistMasterCreatedDate = wishlistMasterCreatedDate;
        this.wishlistMasterUpdatedBy = wishlistMasterUpdatedBy;
        this.wishlistMasterStatus = wishlistMasterStatus;
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

    public String getWishlistMasterId() {
        return wishlistMasterId;
    }

    public void setWishlistMasterId(String wishlistMasterId) {
        this.wishlistMasterId = wishlistMasterId;
    }

    public String getWishlistMasterGroupId() {
        return wishlistMasterGroupId;
    }

    public void setWishlistMasterGroupId(String wishlistMasterGroupId) {
        this.wishlistMasterGroupId = wishlistMasterGroupId;
    }

    public String getWishlistMasterCatId() {
        return wishlistMasterCatId;
    }

    public void setWishlistMasterCatId(String wishlistMasterCatId) {
        this.wishlistMasterCatId = wishlistMasterCatId;
    }

    public String getWishlistMasterSubcatId() {
        return wishlistMasterSubcatId;
    }

    public void setWishlistMasterSubcatId(String wishlistMasterSubcatId) {
        this.wishlistMasterSubcatId = wishlistMasterSubcatId;
    }

    public String getWishlistMasterPrice() {
        return wishlistMasterPrice;
    }

    public void setWishlistMasterPrice(String wishlistMasterPrice) {
        this.wishlistMasterPrice = wishlistMasterPrice;
    }

    public String getWishlistMasterModel() {
        return wishlistMasterModel;
    }

    public void setWishlistMasterModel(String wishlistMasterModel) {
        this.wishlistMasterModel = wishlistMasterModel;
    }

    public String getWishlistMasterBrandId() {
        return wishlistMasterBrandId;
    }

    public void setWishlistMasterBrandId(String wishlistMasterBrandId) {
        this.wishlistMasterBrandId = wishlistMasterBrandId;
    }

    public String getWishlistMasterCreatedBy() {
        return wishlistMasterCreatedBy;
    }

    public void setWishlistMasterCreatedBy(String wishlistMasterCreatedBy) {
        this.wishlistMasterCreatedBy = wishlistMasterCreatedBy;
    }

    public String getWishlistMasterApprovedBy() {
        return wishlistMasterApprovedBy;
    }

    public void setWishlistMasterApprovedBy(String wishlistMasterApprovedBy) {
        this.wishlistMasterApprovedBy = wishlistMasterApprovedBy;
    }

    public String getWishlistMasterDiscount() {
        return wishlistMasterDiscount;
    }

    public void setWishlistMasterDiscount(String wishlistMasterDiscount) {
        this.wishlistMasterDiscount = wishlistMasterDiscount;
    }

    public String getWishlistMasterCreatedDate() {
        return wishlistMasterCreatedDate;
    }

    public void setWishlistMasterCreatedDate(String wishlistMasterCreatedDate) {
        this.wishlistMasterCreatedDate = wishlistMasterCreatedDate;
    }

    public String getWishlistMasterUpdatedBy() {
        return wishlistMasterUpdatedBy;
    }

    public void setWishlistMasterUpdatedBy(String wishlistMasterUpdatedBy) {
        this.wishlistMasterUpdatedBy = wishlistMasterUpdatedBy;
    }

    public String getWishlistMasterStatus() {
        return wishlistMasterStatus;
    }

    public void setWishlistMasterStatus(String wishlistMasterStatus) {
        this.wishlistMasterStatus = wishlistMasterStatus;
    }

}
