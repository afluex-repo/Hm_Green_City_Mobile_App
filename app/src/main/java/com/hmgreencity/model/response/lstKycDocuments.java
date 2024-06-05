package com.hmgreencity.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class lstKycDocuments {
    @SerializedName("AdharNumber")
    @Expose
    private String adharNumber;@SerializedName("AdharBacksideImage")
    @Expose
    private String AdharBacksideImage;
    @SerializedName("AdharImage")
    @Expose
    private String adharImage;
    @SerializedName("AdharStatus")
    @Expose
    private String adharStatus;
    @SerializedName("PanNumber")
    @Expose
    private String panNumber;
    @SerializedName("PanImage")
    @Expose
    private String panImage;
    @SerializedName("PanStatus")
    @Expose
    private String panStatus;
    @SerializedName("DocumentNumber")
    @Expose
    private String documentNumber;
    @SerializedName("DocumentImage")
    @Expose
    private String documentImage;
    @SerializedName("DocumentStatus")
    @Expose
    private String documentStatus;



    @SerializedName("AccountHolderName")
    @Expose
    private String BankHolderName;

    @SerializedName("BankName")
    @Expose
    private String BankName;

    @SerializedName("IFSCCode")
    @Expose
    private String IFSCCode;


    @SerializedName("BankBranch")
    @Expose
    private String BankBranch;

    public String getAdharNumber() {
        return adharNumber;
    }

    public void setAdharNumber(String adharNumber) {
        this.adharNumber = adharNumber;
    }

    public String getAdharImage() {
        return adharImage;
    }

    public void setAdharImage(String adharImage) {
        this.adharImage = adharImage;
    }

    public String getAdharStatus() {
        return adharStatus;
    }

    public void setAdharStatus(String adharStatus) {
        this.adharStatus = adharStatus;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getPanImage() {
        return panImage;
    }

    public void setPanImage(String panImage) {
        this.panImage = panImage;
    }

    public String getPanStatus() {
        return panStatus;
    }

    public void setPanStatus(String panStatus) {
        this.panStatus = panStatus;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentImage() {
        return documentImage;
    }

    public void setDocumentImage(String documentImage) {
        this.documentImage = documentImage;
    }

    public String getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(String documentStatus) {
        this.documentStatus = documentStatus;
    }

    public String getAdharBacksideImage() {
        return AdharBacksideImage;
    }

    public void setAdharBacksideImage(String adharBacksideImage) {
        AdharBacksideImage = adharBacksideImage;
    }

    public String getBankHolderName() {
        return BankHolderName;
    }

    public void setBankHolderName(String bankHolderName) {
        BankHolderName = bankHolderName;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getIFSCCode() {
        return IFSCCode;
    }

    public void setIFSCCode(String IFSCCode) {
        this.IFSCCode = IFSCCode;
    }

    public String getBankBranch() {
        return BankBranch;
    }

    public void setBankBranch(String bankBranch) {
        BankBranch = bankBranch;
    }
}
