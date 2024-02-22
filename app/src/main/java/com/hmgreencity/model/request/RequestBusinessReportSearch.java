package com.hmgreencity.model.request;

import com.google.gson.annotations.SerializedName;

public class RequestBusinessReportSearch {

    @SerializedName("loginid")
    private String loginId;
    @SerializedName("Leg")
    private String Leg;
    @SerializedName("isdownline")
    private String isdownline;
    @SerializedName("ToDate")
    private String ToDate;

    @SerializedName("FromDate")
    private String FromDate;

    public void setLeg(String leg) {
        Leg = leg;
    }

    public void setToDate(String toDate) {
        ToDate = toDate;
    }

    public void setFromDate(String fromDate) {
        FromDate = fromDate;
    }

    public void setIsdownline(String isdownline) {
        this.isdownline = isdownline;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
