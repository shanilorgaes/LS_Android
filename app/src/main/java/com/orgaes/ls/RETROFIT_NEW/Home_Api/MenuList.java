
package com.orgaes.ls.RETROFIT_NEW.Home_Api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuList {

    @SerializedName("user_menu_name")
    @Expose
    private String userMenuName;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MenuList() {
    }

    /**
     * 
     * @param userMenuName
     */
    public MenuList(String userMenuName) {
        super();
        this.userMenuName = userMenuName;
    }

    public String getUserMenuName() {
        return userMenuName;
    }

    public void setUserMenuName(String userMenuName) {
        this.userMenuName = userMenuName;
    }

}
