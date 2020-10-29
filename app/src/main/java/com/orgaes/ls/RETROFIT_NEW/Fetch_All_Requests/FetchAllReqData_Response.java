
package com.orgaes.ls.RETROFIT_NEW.Fetch_All_Requests;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchAllReqData_Response {

    @SerializedName("requests")
    @Expose
    private List<FetchAllReqData_Request> requests = null;
    @SerializedName("request_count")
    @Expose
    private Integer requestCount;
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
    public FetchAllReqData_Response() {
    }

    /**
     * 
     * @param requestCount
     * @param requests
     */
    public FetchAllReqData_Response(List<FetchAllReqData_Request> requests, Integer requestCount) {
        super();
        this.requests = requests;
        this.requestCount = requestCount;
    }

    public List<FetchAllReqData_Request> getRequests() {
        return requests;
    }

    public void setRequests(List<FetchAllReqData_Request> requests) {
        this.requests = requests;
    }

    public Integer getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Integer requestCount) {
        this.requestCount = requestCount;
    }

}
