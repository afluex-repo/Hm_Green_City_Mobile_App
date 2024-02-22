package com.hmgreencity.model.response.responsePayoutReportSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstPayoutDetail {

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
    @SerializedName("LeadershipBonus")
    @Expose
    private String leadershipBonus;
    @SerializedName("TDSAmount")
    @Expose
    private String tDSAmount;
    @SerializedName("NetAmount")
    @Expose
    private String netAmount;
    @SerializedName("ProductWallet")
    @Expose
    private String productWallet;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPayoutNo() {
        return payoutNo;
    }

    public void setPayoutNo(String payoutNo) {
        this.payoutNo = payoutNo;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getBinaryIncome() {
        return binaryIncome;
    }

    public void setBinaryIncome(String binaryIncome) {
        this.binaryIncome = binaryIncome;
    }

    public String getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(String grossAmount) {
        this.grossAmount = grossAmount;
    }

    public String getDirectIncome() {
        return directIncome;
    }

    public void setDirectIncome(String directIncome) {
        this.directIncome = directIncome;
    }

    public String getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(String processingFee) {
        this.processingFee = processingFee;
    }

    public String getLeadershipBonus() {
        return leadershipBonus;
    }

    public void setLeadershipBonus(String leadershipBonus) {
        this.leadershipBonus = leadershipBonus;
    }

    public String getTDSAmount() {
        return tDSAmount;
    }

    public void setTDSAmount(String tDSAmount) {
        this.tDSAmount = tDSAmount;
    }

    public String getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

    public String getProductWallet() {
        return productWallet;
    }

    public void setProductWallet(String productWallet) {
        this.productWallet = productWallet;
    }

}
