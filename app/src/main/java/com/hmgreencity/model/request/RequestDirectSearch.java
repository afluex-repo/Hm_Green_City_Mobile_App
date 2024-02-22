package com.hmgreencity.model.request;

import com.google.gson.annotations.SerializedName;

public class RequestDirectSearch {
    @SerializedName("LoginId")
    private String loginId;
    @SerializedName("Name")
    private String Name;
    @SerializedName("FromDate")
    private String FromDate;
    @SerializedName("ToDate")
    private String ToDate;
    @SerializedName("Status")
    private String status;

    @SerializedName("Leg")
    private String Leg;


    public void setName(String name) {
        Name = name;
    }

    public void setFromDate(String fromDate) {
        FromDate = fromDate;
    }

    public void setLeg(String leg) {
        Leg = leg;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setToDate(String toDate) {
        ToDate = toDate;
    }
    public void setLoginId(String loginId){
        this.loginId = loginId;
    }


}
