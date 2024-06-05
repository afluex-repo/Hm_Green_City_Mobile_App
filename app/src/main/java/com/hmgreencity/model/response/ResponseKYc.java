package com.hmgreencity.model.response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ResponseKYc {

    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("ErrorMessage")
    @Expose
    private Object errorMessage;
    @SerializedName("SuccessMessage")
    @Expose
    private String successMessage;
    @SerializedName("lstKycdocuments")
    @Expose
    private ArrayList<lstKycDocuments> lstKycdocuments;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public ArrayList<lstKycDocuments> getLstKycdocuments() {
        return lstKycdocuments;
    }

    public void setLstKycdocuments(ArrayList<lstKycDocuments> lstKycdocuments) {
        this.lstKycdocuments = lstKycdocuments;
    }
}
