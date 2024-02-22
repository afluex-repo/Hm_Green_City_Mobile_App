package com.hmgreencity.model.response.responseBusinessReportSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hmgreencity.model.response.responseBusinessReport.Lstassociate;

import java.util.List;

public class ResponseBusinessReportSearch {
    @SerializedName("lstassociate")
    @Expose
    private List<Lstassociate> lstassociate = null;
    @SerializedName("Leg")
    @Expose
    private Object leg;
    @SerializedName("ToDate")
    @Expose
    private Object toDate;
    @SerializedName("FromDate")
    @Expose
    private Object fromDate;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("LoginId")
    @Expose
    private String loginId;
    @SerializedName("TotalNetAmount")
    @Expose
    private String totalNetAmount;
    @SerializedName("TotalBV")
    @Expose
    private String totalBV;
    @SerializedName("IsDownline")
    @Expose
    private Object isDownline;

    public List<Lstassociate> getLstassociate() {
        return lstassociate;
    }

    public void setLstassociate(List<Lstassociate> lstassociate) {
        this.lstassociate = lstassociate;
    }

    public Object getLeg() {
        return leg;
    }

    public void setLeg(Object leg) {
        this.leg = leg;
    }

    public Object getToDate() {
        return toDate;
    }

    public void setToDate(Object toDate) {
        this.toDate = toDate;
    }

    public Object getFromDate() {
        return fromDate;
    }

    public void setFromDate(Object fromDate) {
        this.fromDate = fromDate;
    }

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

    public String getTotalNetAmount() {
        return totalNetAmount;
    }

    public void setTotalNetAmount(String totalNetAmount) {
        this.totalNetAmount = totalNetAmount;
    }

    public String getTotalBV() {
        return totalBV;
    }

    public void setTotalBV(String totalBV) {
        this.totalBV = totalBV;
    }

    public Object getIsDownline() {
        return isDownline;
    }

    public void setIsDownline(Object isDownline) {
        this.isDownline = isDownline;
    }


}
