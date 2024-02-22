package com.hmgreencity.model.response.responseUnpaidIncome;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class lstunpaidincomes {

    @SerializedName("FromLoginId")
    @Expose
    private String fromLoginId;
    @SerializedName("FromUserName")
    @Expose
    private String fromUserName;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("IncomeType")
    @Expose
    private String incomeType;
    @SerializedName("Date")
    @Expose
    private String date;

    public String getFromLoginId() {
        return fromLoginId;
    }


    public String getFromUserName() {
        return fromUserName;
    }


    public String getAmount() {
        return amount;
    }


    public String getIncomeType() {
        return incomeType;
    }


    public String getDate() {
        return date;
    }


}
