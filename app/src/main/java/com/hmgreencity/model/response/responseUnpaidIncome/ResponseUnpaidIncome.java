package com.hmgreencity.model.response.responseUnpaidIncome;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hmgreencity.model.response.responsePayoutReport.lstPayoutDetail;

import java.util.List;

public class ResponseUnpaidIncome {


    @SerializedName("lstunpaidincomes")
    @Expose
    private List<lstunpaidincomes> lstunpaidincomes = null;
    @SerializedName("LoginId")
    @Expose
    private String loginId;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Message")
    @Expose
    private String message;

    public List<lstunpaidincomes> getLstunpaidincomes() {
        return lstunpaidincomes;
    }



    public String getLoginId() {
        return loginId;
    }


    public String getStatus() {
        return status;
    }



    public String getMessage() {
        return message;
    }





}
