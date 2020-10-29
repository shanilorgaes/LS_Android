
package com.orgaes.ls.RETROFIT_NEW.FriendsList;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Friendslist_Response {

    @SerializedName("friends")
    @Expose
    private List<FriendsList_Data> friends = null;
    @SerializedName("request_coming")
    @Expose
    private String requestComing;
    @SerializedName("request_send")
    @Expose
    private String requestSend;
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
    public Friendslist_Response() {
    }

    /**
     * 
     * @param requestComing
     * @param requestSend
     * @param friends
     */
    public Friendslist_Response(List<FriendsList_Data> friends, String requestComing, String requestSend) {
        super();
        this.friends = friends;
        this.requestComing = requestComing;
        this.requestSend = requestSend;
    }

    public List<FriendsList_Data> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendsList_Data> friends) {
        this.friends = friends;
    }

    public String getRequestComing() {
        return requestComing;
    }

    public void setRequestComing(String requestComing) {
        this.requestComing = requestComing;
    }

    public String getRequestSend() {
        return requestSend;
    }

    public void setRequestSend(String requestSend) {
        this.requestSend = requestSend;
    }

}
