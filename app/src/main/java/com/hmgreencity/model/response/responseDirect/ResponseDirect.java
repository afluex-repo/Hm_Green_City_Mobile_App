package com.hmgreencity.model.response.responseDirect;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDirect {

    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("LoginId")
    @Expose
    private String loginId;
    @SerializedName("lstdirect")
    @Expose
    private List<Lstdirect> lstdirect = null;

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

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public List<Lstdirect> getLstdirect() {
        return lstdirect;
    }

    public void setLstdirect(List<Lstdirect> lstdirect) {
        this.lstdirect = lstdirect;
    }


}
