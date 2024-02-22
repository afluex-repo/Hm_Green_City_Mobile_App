package com.hmgreencity.model.request;

import com.google.gson.annotations.SerializedName;

public class RequestPayoutReportSearch {
    @SerializedName("LoginId")
    private String loginId;
    @SerializedName("FromDate")
    private String FromDate;
    @SerializedName("ToDate")
    private String ToDate;
    @SerializedName("PayoutNo")
    private String payoutNo;


    public String getLoginId() {
        return loginId;
    }

    public String getFromDate() {
        return FromDate;
    }

    public String getToDate() {
        return ToDate;
    }

    public String getPayoutNo() {
        return payoutNo;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setFromDate(String fromDate) {
        FromDate = fromDate;
    }

    public void setToDate(String toDate) {
        ToDate = toDate;
    }

    public void setPayoutNo(String payoutNo) {
        this.payoutNo = payoutNo;
    }
}
