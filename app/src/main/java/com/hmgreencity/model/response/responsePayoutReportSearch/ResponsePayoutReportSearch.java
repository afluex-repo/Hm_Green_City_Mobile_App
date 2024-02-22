package com.hmgreencity.model.response.responsePayoutReportSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePayoutReportSearch {

    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("LoginId")
    @Expose
    private String loginId;
    @SerializedName("FromDate")
    @Expose
    private Object fromDate;
    @SerializedName("ToDate")
    @Expose
    private Object toDate;
    @SerializedName("PayoutNo")
    @Expose
    private Object payoutNo;
    @SerializedName("lstPayoutDetail")
    @Expose
    private List<LstPayoutDetail> lstPayoutDetails = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Object getFromDate() {
        return fromDate;
    }

    public void setFromDate(Object fromDate) {
        this.fromDate = fromDate;
    }

    public Object getToDate() {
        return toDate;
    }

    public void setToDate(Object toDate) {
        this.toDate = toDate;
    }

    public Object getPayoutNo() {
        return payoutNo;
    }

    public void setPayoutNo(Object payoutNo) {
        this.payoutNo = payoutNo;
    }

    public List<LstPayoutDetail> getLstPayoutDetails() {
        return lstPayoutDetails;
    }

    public void setLstPayoutDetail(List<LstPayoutDetail> lstPayoutDetail) {
        this.lstPayoutDetails = lstPayoutDetail;
    }


}
