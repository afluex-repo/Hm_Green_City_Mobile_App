package com.hmgreencity.model.response.responseTopUpList;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseTopUpList{

    @SerializedName("Status")
    private String status;

    @SerializedName("lsttopupreport")
    private List<LsttopupreportItem> lsttopupreport;
  @SerializedName("lstTopupReportNew")
    private List<LsttopupreportItem> lstTopupReportNew;

    @SerializedName("Message")
    private String message;

    public String getStatus(){
        return status;
    }


    public String getMessage(){
        return message;

    }

    public List<LsttopupreportItem> getLsttopupreport() {
        return lsttopupreport;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLsttopupreport(List<LsttopupreportItem> lsttopupreport) {
        this.lsttopupreport = lsttopupreport;
    }

    public List<LsttopupreportItem> getLstTopupReportNew() {
        return lstTopupReportNew;
    }

    public void setLstTopupReportNew(List<LsttopupreportItem> lstTopupReportNew) {
        this.lstTopupReportNew = lstTopupReportNew;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}