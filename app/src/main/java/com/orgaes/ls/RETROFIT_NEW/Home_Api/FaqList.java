
package com.orgaes.ls.RETROFIT_NEW.Home_Api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FaqList {

    @SerializedName("faq_trans_id")
    @Expose
    private String faqTransId;
    @SerializedName("faq_trans_master_id")
    @Expose
    private String faqTransMasterId;
    @SerializedName("faq_question")
    @Expose
    private String faqQuestion;
    @SerializedName("faq_answer")
    @Expose
    private String faqAnswer;
    @SerializedName("faq_language_code")
    @Expose
    private String faqLanguageCode;
    @SerializedName("faq_master_id")
    @Expose
    private String faqMasterId;
    @SerializedName("faq_master_status")
    @Expose
    private String faqMasterStatus;
    @SerializedName("faq_date_time")
    @Expose
    private String faqDateTime;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FaqList() {
    }

    /**
     * 
     * @param faqAnswer
     * @param faqTransMasterId
     * @param faqQuestion
     * @param faqMasterStatus
     * @param faqTransId
     * @param faqLanguageCode
     * @param faqDateTime
     * @param faqMasterId
     */
    public FaqList(String faqTransId, String faqTransMasterId, String faqQuestion, String faqAnswer, String faqLanguageCode, String faqMasterId, String faqMasterStatus, String faqDateTime) {
        super();
        this.faqTransId = faqTransId;
        this.faqTransMasterId = faqTransMasterId;
        this.faqQuestion = faqQuestion;
        this.faqAnswer = faqAnswer;
        this.faqLanguageCode = faqLanguageCode;
        this.faqMasterId = faqMasterId;
        this.faqMasterStatus = faqMasterStatus;
        this.faqDateTime = faqDateTime;
    }

    public String getFaqTransId() {
        return faqTransId;
    }

    public void setFaqTransId(String faqTransId) {
        this.faqTransId = faqTransId;
    }

    public String getFaqTransMasterId() {
        return faqTransMasterId;
    }

    public void setFaqTransMasterId(String faqTransMasterId) {
        this.faqTransMasterId = faqTransMasterId;
    }

    public String getFaqQuestion() {
        return faqQuestion;
    }

    public void setFaqQuestion(String faqQuestion) {
        this.faqQuestion = faqQuestion;
    }

    public String getFaqAnswer() {
        return faqAnswer;
    }

    public void setFaqAnswer(String faqAnswer) {
        this.faqAnswer = faqAnswer;
    }

    public String getFaqLanguageCode() {
        return faqLanguageCode;
    }

    public void setFaqLanguageCode(String faqLanguageCode) {
        this.faqLanguageCode = faqLanguageCode;
    }

    public String getFaqMasterId() {
        return faqMasterId;
    }

    public void setFaqMasterId(String faqMasterId) {
        this.faqMasterId = faqMasterId;
    }

    public String getFaqMasterStatus() {
        return faqMasterStatus;
    }

    public void setFaqMasterStatus(String faqMasterStatus) {
        this.faqMasterStatus = faqMasterStatus;
    }

    public String getFaqDateTime() {
        return faqDateTime;
    }

    public void setFaqDateTime(String faqDateTime) {
        this.faqDateTime = faqDateTime;
    }

}
