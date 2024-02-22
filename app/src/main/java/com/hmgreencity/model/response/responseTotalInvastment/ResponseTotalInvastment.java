package com.hmgreencity.model.response.responseTotalInvastment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseTotalInvastment {

    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Fk_UserId")
    @Expose
    private String fkUserId;
    @SerializedName("lstinvestment")
    @Expose
    private List<Lstinvestment> lstinvestment = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(String fkUserId) {
        this.fkUserId = fkUserId;
    }

    public List<Lstinvestment> getLstinvestment() {
        return lstinvestment;
    }

    public void setLstinvestment(List<Lstinvestment> lstinvestment) {
        this.lstinvestment = lstinvestment;
    }


}
