package com.hmgreencity.model.response.responseDirectSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDirectSearch {


    @SerializedName("Status1")
    @Expose
    private String status1;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("FromDate")
    @Expose
    private String fromDate;
    @SerializedName("LoginId")
    @Expose
    private String loginId;
    @SerializedName("ToDate")
    @Expose
    private String toDate;
    @SerializedName("Leg")
    @Expose
    private String leg;
    @SerializedName("lstdirect")
    @Expose
    private List<responseLstdirect> lstdirect = null;

    public String getStatus1() {
        return status1;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getToDate() {
        return toDate;
    }

    public String getLeg() {
        return leg;
    }

    public List<responseLstdirect> getLstdirect() {
        return lstdirect;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public void setLeg(String leg) {
        this.leg = leg;
    }

    public void setLstdirect(List<responseLstdirect> lstdirect) {
        this.lstdirect = lstdirect;
    }
}
