
package com.orgaes.ls.RETROFIT_NEW.UserWinner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DrawWin {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("item_id")
    @Expose
    private String item_id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("rank")
    @Expose
    private String rank;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DrawWin() {
    }

    /**
     * 
     * @param image
     * @param name
     * @param description
     * @param rank
     * @param id
     */
    public DrawWin(String id, String name, String description, String image, String rank) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.rank = rank;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

}
