package com.hmgreencity.model.response.responseTotalInvastment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lstinvestment {

    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
