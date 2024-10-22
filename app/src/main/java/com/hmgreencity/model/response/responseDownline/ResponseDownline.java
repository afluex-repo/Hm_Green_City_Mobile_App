package com.hmgreencity.model.response.responseDownline;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDownline {
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
    private List<LstdirectDownline> lstdirect = null;

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

    public List<LstdirectDownline> getLstdirect() {
        return lstdirect;
    }

    public void setLstdirect(List<LstdirectDownline> lstdirect) {
        this.lstdirect = lstdirect;
    }

}
