package com.hmgreencity.model.response.responsePayoutReport;

import com.google.gson.annotations.SerializedName;
import com.hmgreencity.model.response.responseTopUpList.LsttopupreportItem;

import java.util.List;

public class ResponsePayoutReport {

    @SerializedName("Status")
    private String status;

    @SerializedName("lstPayoutDetail")
    private List<lstPayoutDetail> lstPayoutDetail;


    @SerializedName("Message")
    private String message;

    public String getStatus(){
        return status;
    }

    public List<lstPayoutDetail> getLstPayoutDetail() {
        return lstPayoutDetail;
    }

    public String getMessage(){
        return message;
    }

}
