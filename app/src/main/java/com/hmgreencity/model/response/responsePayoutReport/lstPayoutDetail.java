package com.hmgreencity.model.response.responsePayoutReport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class lstPayoutDetail {

    @SerializedName("LoginId")
    @Expose
    private String loginId;
    @SerializedName("DisplayName")
    @Expose
    private String displayName;
    @SerializedName("PayoutNo")
    @Expose
    private String payoutNo;
    @SerializedName("ClosingDate")
    @Expose
    private String closingDate;
    @SerializedName("BinaryIncome")
    @Expose
    private String binaryIncome;
    @SerializedName("GrossAmount")
    @Expose
    private String grossAmount;
    @SerializedName("DirectIncome")
    @Expose
    private String directIncome;
    @SerializedName("ProcessingFee")
    @Expose
    private String processingFee;
    @SerializedName("TDSAmount")
    @Expose
    private String tDSAmount;
    @SerializedName("NetAmount")
    @Expose
    private String netAmount;


    public String getLoginId() {
        return loginId;
    }


    public String getDisplayName() {
        return displayName;
    }



    public String getPayoutNo() {
        return payoutNo;
    }


    public String getClosingDate() {
        return closingDate;
    }


    public String getBinaryIncome() {
        return binaryIncome;
    }


    public String getGrossAmount() {
        return grossAmount;
    }


    public String getDirectIncome() {
        return directIncome;
    }


    public String getProcessingFee() {
        return processingFee;
    }



    public String getTDSAmount() {
        return tDSAmount;
    }


    public String getNetAmount() {
        return netAmount;
    }





}
