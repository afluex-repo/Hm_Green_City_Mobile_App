package com.hmgreencity.model.response.responseDownlineSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDownlineSearch {


    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Status1")
    @Expose
    private String status1;
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
    private List<Lstdirectdownline> lstdirect = null;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public Object getLeg() {
        return leg;
    }


    public void setLeg(String leg) {
        this.leg = leg;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public List<Lstdirectdownline> getLstdirect() {
        return lstdirect;
    }

    public void setLstdirect(List<Lstdirectdownline> lstdirect) {
        this.lstdirect = lstdirect;
    }

}
