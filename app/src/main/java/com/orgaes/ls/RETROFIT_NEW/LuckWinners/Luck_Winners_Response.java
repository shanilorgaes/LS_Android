
package com.orgaes.ls.RETROFIT_NEW.LuckWinners;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Luck_Winners_Response {

    @SerializedName("todays_winner")
    @Expose
    private List<TodaysWinner_Data> todaysWinner = null;
    @SerializedName("todays_luck")
    @Expose
    private List<TodaysLuck_Data> todaysLuck = null;
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
    public Luck_Winners_Response() {
    }

    /**
     * 
     * @param todaysWinner
     * @param todaysLuck
     */
    public Luck_Winners_Response(List<TodaysWinner_Data> todaysWinner, List<TodaysLuck_Data> todaysLuck) {
        super();
        this.todaysWinner = todaysWinner;
        this.todaysLuck = todaysLuck;
    }

    public List<TodaysWinner_Data> getTodaysWinner() {
        return todaysWinner;
    }

    public void setTodaysWinner(List<TodaysWinner_Data> todaysWinner) {
        this.todaysWinner = todaysWinner;
    }

    public List<TodaysLuck_Data> getTodaysLuck() {
        return todaysLuck;
    }

    public void setTodaysLuck(List<TodaysLuck_Data> todaysLuck) {
        this.todaysLuck = todaysLuck;
    }

}
