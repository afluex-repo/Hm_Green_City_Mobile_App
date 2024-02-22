package com.hmgreencity.model.response.responseDirect;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lstdirect {

    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Email")
    @Expose
    private String email;
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
    @SerializedName("SponsorId")
    @Expose
    private String sponsorId;
    @SerializedName("SponsorName")
    @Expose
    private String sponsorName;
    @SerializedName("Package")
    @Expose
    private String _package;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getPackage() {
        return _package;
    }

    public void setPackage(String _package) {
        this._package = _package;
    }

}
