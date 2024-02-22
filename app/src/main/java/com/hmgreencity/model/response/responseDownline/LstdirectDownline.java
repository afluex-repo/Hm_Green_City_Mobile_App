package com.hmgreencity.model.response.responseDownline;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstdirectDownline {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("LoginId")
    @Expose
    private String loginId;
    @SerializedName("JoiningDate")
    @Expose
    private String joiningDate;
    @SerializedName("Leg")
    @Expose
    private String leg;
    @SerializedName("PermanentDate")
    @Expose
    private String permanentDate;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Package")
    @Expose
    private String _package;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getLeg() {
        return leg;
    }

    public void setLeg(String leg) {
        this.leg = leg;
    }

    public String getPermanentDate() {
        return permanentDate;
    }

    public void setPermanentDate(String permanentDate) {
        this.permanentDate = permanentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPackage() {
        return _package;
    }

    public void setPackage(String _package) {
        this._package = _package;
    }

}
