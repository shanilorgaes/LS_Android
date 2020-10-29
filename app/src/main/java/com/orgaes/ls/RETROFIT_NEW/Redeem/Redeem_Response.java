
package com.orgaes.ls.RETROFIT_NEW.Redeem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Redeem_Response {

    @SerializedName("qr_code")
    @Expose
    private String qrCode;
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
    public Redeem_Response() {
    }

    /**
     * 
     * @param qrCode
     */
    public Redeem_Response(String qrCode) {
        super();
        this.qrCode = qrCode;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

}
