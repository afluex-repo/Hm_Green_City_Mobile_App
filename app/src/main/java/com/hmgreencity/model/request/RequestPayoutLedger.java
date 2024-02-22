package com.hmgreencity.model.request;

import com.google.gson.annotations.SerializedName;

public class RequestPayoutLedger {

    @SerializedName("Fk_UserId")
    private String Fk_UserId;
    @SerializedName("FromDate")
    private String FromDate;
    @SerializedName("ToDate")
    private String ToDate;

    public String getFromDate() {
        return FromDate;
    }

    public String getToDate() {
        return ToDate;
    }

    public String getFk_UserId() {
        return Fk_UserId;
    }

    public void setFromDate(String fromDate) {
        FromDate = fromDate;
    }

    public void setToDate(String toDate) {
        ToDate = toDate;
    }

    public void setFk_UserId(String fk_UserId) {
        Fk_UserId = fk_UserId;
    }
}
