package com.orgaes.ls.Functions;

import android.os.Build;

import java.util.Objects;

public class Item {


    String Gcl_id;
    String Coupon_code;
    String Coupon_type;
    String Coupon_category;
    String Coupon_item;
    String Sponsored_by;
    String Start_date;
    String End_date;
    String Design;
    String Percentage;
    String Amount;
    String No_luck;
    String Cash;
    String Collection_center;
    String Brand;
    String Edition;
    String Coupon_type_id;
    String Name;
    String Status;
    String Coupon_category_id;
    String Coupon_category_name;
    String Gci_id;
    String Gift_item;

    public Item(String gcl_id, String coupon_code, String coupon_type, String coupon_category, String coupon_item, String sponsored_by, String start_date, String end_date, String design, String percentage, String amount, String no_luck, String cash, String collection_center, String brand, String edition, String coupon_type_id, String name, String status, String coupon_category_id, String coupon_category_name, String gci_id, String gift_item) {
        Gcl_id = gcl_id;
        Coupon_code = coupon_code;
        Coupon_type = coupon_type;
        Coupon_category = coupon_category;
        Coupon_item = coupon_item;
        Sponsored_by = sponsored_by;
        Start_date = start_date;
        End_date = end_date;
        Design = design;
        Percentage = percentage;
        Amount = amount;
        No_luck = no_luck;
        Cash = cash;
        Collection_center = collection_center;
        Brand = brand;
        Edition = edition;
        Coupon_type_id = coupon_type_id;
        Name = name;
        Status = status;
        Coupon_category_id = coupon_category_id;
        Coupon_category_name = coupon_category_name;
        Gci_id = gci_id;
        Gift_item = gift_item;
    }


    public String getGcl_id() {
        return Gcl_id;
    }

    public void setGcl_id(String gcl_id) {
        Gcl_id = gcl_id;
    }

    public String getCoupon_code() {
        return Coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        Coupon_code = coupon_code;
    }

    public String getCoupon_type() {
        return Coupon_type;
    }

    public void setCoupon_type(String coupon_type) {
        Coupon_type = coupon_type;
    }

    public String getCoupon_category() {
        return Coupon_category;
    }

    public void setCoupon_category(String coupon_category) {
        Coupon_category = coupon_category;
    }

    public String getCoupon_item() {
        return Coupon_item;
    }

    public void setCoupon_item(String coupon_item) {
        Coupon_item = coupon_item;
    }

    public String getSponsored_by() {
        return Sponsored_by;
    }

    public void setSponsored_by(String sponsored_by) {
        Sponsored_by = sponsored_by;
    }

    public String getStart_date() {
        return Start_date;
    }

    public void setStart_date(String start_date) {
        Start_date = start_date;
    }

    public String getEnd_date() {
        return End_date;
    }

    public void setEnd_date(String end_date) {
        End_date = end_date;
    }

    public String getDesign() {
        return Design;
    }

    public void setDesign(String design) {
        Design = design;
    }

    public String getPercentage() {
        return Percentage;
    }

    public void setPercentage(String percentage) {
        Percentage = percentage;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getNo_luck() {
        return No_luck;
    }

    public void setNo_luck(String no_luck) {
        No_luck = no_luck;
    }

    public String getCash() {
        return Cash;
    }

    public void setCash(String cash) {
        Cash = cash;
    }

    public String getCollection_center() {
        return Collection_center;
    }

    public void setCollection_center(String collection_center) {
        Collection_center = collection_center;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getEdition() {
        return Edition;
    }

    public void setEdition(String edition) {
        Edition = edition;
    }

    public String getCoupon_type_id() {
        return Coupon_type_id;
    }

    public void setCoupon_type_id(String coupon_type_id) {
        Coupon_type_id = coupon_type_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCoupon_category_id() {
        return Coupon_category_id;
    }

    public void setCoupon_category_id(String coupon_category_id) {
        Coupon_category_id = coupon_category_id;
    }

    public String getCoupon_category_name() {
        return Coupon_category_name;
    }

    public void setCoupon_category_name(String coupon_category_name) {
        Coupon_category_name = coupon_category_name;
    }

    public String getGci_id() {
        return Gci_id;
    }

    public void setGci_id(String gci_id) {
        Gci_id = gci_id;
    }

    public String getGift_item() {
        return Gift_item;
    }

    public void setGift_item(String gift_item) {
        Gift_item = gift_item;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;

        if (getGci_id() != item.getGci_id()) return false;
        if (getCash() != null ? !getCash().equals(item.getCash()) : item.getCash() != null) return false;
        if (getAmount() != null ? !getAmount().equals(item.getAmount()) : item.getAmount() != null)
            return false;
        if (getBrand() != null ? !getBrand().equals(item.getBrand()) : item.getBrand() != null)
            return false;
        if (getCollection_center() != null ? !getCollection_center().equals(item.getCollection_center()) : item.getCollection_center() != null)
            return false;
        if (getStart_date() != null ? !getStart_date().equals(item.getStart_date()) : item.getStart_date() != null) return false;
        return !(getEnd_date() != null ? !getEnd_date().equals(item.getEnd_date()) : item.getEnd_date() != null);

    }


}
