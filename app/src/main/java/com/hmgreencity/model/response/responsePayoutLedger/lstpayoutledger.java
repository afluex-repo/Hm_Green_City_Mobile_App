package com.hmgreencity.model.response.responsePayoutLedger;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class lstpayoutledger {

    @SerializedName("Narration")
    @Expose
    private String narration;
    @SerializedName("DrAmount")
    @Expose
    private String drAmount;
    @SerializedName("CrAmount")
    @Expose
    private String crAmount;
    @SerializedName("AddedOn")
    @Expose
    private String addedOn;
    @SerializedName("PayoutBalance")
    @Expose
    private String payoutBalance;
 @SerializedName("TDSCharge")
    @Expose
    private String TDSCharge ;
 @SerializedName("TransactionNo")
    @Expose
    private String TransactionNo ;

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getDrAmount() {
        return drAmount;
    }

    public void setDrAmount(String drAmount) {
        this.drAmount = drAmount;
    }

    public String getCrAmount() {
        return crAmount;
    }

    public void setCrAmount(String crAmount) {
        this.crAmount = crAmount;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public String getPayoutBalance() {
        return payoutBalance;
    }

    public void setPayoutBalance(String payoutBalance) {
        this.payoutBalance = payoutBalance;
    }


    public String getTDSCharge() {
        return TDSCharge;
    }

    public void setTDSCharge(String TDSCharge) {
        this.TDSCharge = TDSCharge;
    }

    public String getTransactionNo() {
        return TransactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        TransactionNo = transactionNo;
    }
}
