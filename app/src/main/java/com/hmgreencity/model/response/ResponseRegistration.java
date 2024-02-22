package com.hmgreencity.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseRegistration {
    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("MobileNo")
    @Expose
    private String MobileNo;



    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("LoginId")
    @Expose
    private String loginId;
    @SerializedName("DisplayName")
    @Expose
    private String displayName;
    @SerializedName("PassWord")
    @Expose
    private String passWord;
    @SerializedName("Transpassword")
    @Expose
    private String transpassword;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getTranspassword() {
        return transpassword;
    }

    public void setTranspassword(String transpassword) {
        this.transpassword = transpassword;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }
}
