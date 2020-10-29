
package com.orgaes.ls.RETROFIT_NEW.LeaderBoard_Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeaderBoard_Response {

    @SerializedName("pages")
    @Expose
    private Integer pages;
    @SerializedName("leaderboard")
    @Expose
    private List<Leaderboard_Details> leaderboard = null;
    @SerializedName("leaderboard_user")
    @Expose
    private List<Leaderboard_Details> leaderboardUser = null;
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
    public LeaderBoard_Response() {
    }

    /**
     * 
     * @param leaderboard
     * @param pages
     * @param leaderboardUser
     */
    public LeaderBoard_Response(Integer pages, List<Leaderboard_Details> leaderboard, List<Leaderboard_Details> leaderboardUser) {
        super();
        this.pages = pages;
        this.leaderboard = leaderboard;
        this.leaderboardUser = leaderboardUser;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<Leaderboard_Details> getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(List<Leaderboard_Details> leaderboard) {
        this.leaderboard = leaderboard;
    }

    public List<Leaderboard_Details> getLeaderboardUser() {
        return leaderboardUser;
    }

    public void setLeaderboardUser(List<Leaderboard_Details> leaderboardUser) {
        this.leaderboardUser = leaderboardUser;
    }

}
