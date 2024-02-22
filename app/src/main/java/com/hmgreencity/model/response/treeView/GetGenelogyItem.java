package com.hmgreencity.model.response.treeView;

import com.google.gson.annotations.SerializedName;

public class GetGenelogyItem {

    @SerializedName("LoginId")
    private String loginId;

    @SerializedName("BusinessLeft")
    private String businessLeft;

    @SerializedName("ImageURL")
    private String imageURL;

    @SerializedName("Fk_SponsorId")
    private String fkSponsorId;

    @SerializedName("BusinessRight")
    private String businessRight;

    @SerializedName("Leg")
    private String leg;

    @SerializedName("ActiveLeft")
    private String activeLeft;

    @SerializedName("InactiveLeft")
    private String inactiveLeft;

    @SerializedName("SponsorId")
    private String sponsorId;

    @SerializedName("Fk_UserId")
    private String fkUserId;

    @SerializedName("ActiveRight")
    private String activeRight;

    @SerializedName("Fk_ParentId")
    private String fkParentId;

    @SerializedName("TeamPermanent")
    private String teamPermanent;

    @SerializedName("InactiveRight")
    private String inactiveRight;

    @SerializedName("MemberName")
    private String memberName;

    @SerializedName("Id")
    private String id;

    @SerializedName("ActivationDate")
    private String activationDate;

    @SerializedName("MemberLevel")
    private String memberLevel;
    @SerializedName("HoldLeft")
    private String HoldLeft;
    @SerializedName("HoldRight")
    private String HoldRight;


    public String getLoginId(){
        return loginId;
    }

    public String getBusinessLeft(){
        return businessLeft;
    }

    public String getImageURL(){
        return imageURL;
    }

    public String getFkSponsorId(){
        return fkSponsorId;
    }

    public String getBusinessRight(){
        return businessRight;
    }

    public String getLeg(){
        return leg;
    }

    public String getActiveLeft(){
        return activeLeft;
    }

    public String getInactiveLeft(){
        return inactiveLeft;
    }

    public String getSponsorId(){
        return sponsorId;
    }

    public String getFkUserId(){
        return fkUserId;
    }

    public String getActiveRight(){
        return activeRight;
    }

    public String getFkParentId(){
        return fkParentId;
    }

    public String getTeamPermanent(){
        return teamPermanent;
    }

    public String getInactiveRight(){
        return inactiveRight;
    }

    public String getMemberName(){
        return memberName;
    }

    public String getId(){
        return id;
    }

    public String getActivationDate(){
        return activationDate;
    }

    public String getMemberLevel(){
        return memberLevel;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setBusinessLeft(String businessLeft) {
        this.businessLeft = businessLeft;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setFkSponsorId(String fkSponsorId) {
        this.fkSponsorId = fkSponsorId;
    }

    public void setBusinessRight(String businessRight) {
        this.businessRight = businessRight;
    }

    public void setLeg(String leg) {
        this.leg = leg;
    }

    public void setActiveLeft(String activeLeft) {
        this.activeLeft = activeLeft;
    }

    public void setInactiveLeft(String inactiveLeft) {
        this.inactiveLeft = inactiveLeft;
    }

    public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId;
    }

    public void setFkUserId(String fkUserId) {
        this.fkUserId = fkUserId;
    }

    public void setActiveRight(String activeRight) {
        this.activeRight = activeRight;
    }

    public void setFkParentId(String fkParentId) {
        this.fkParentId = fkParentId;
    }

    public void setTeamPermanent(String teamPermanent) {
        this.teamPermanent = teamPermanent;
    }

    public void setInactiveRight(String inactiveRight) {
        this.inactiveRight = inactiveRight;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getHoldLeft() {
        return HoldLeft;
    }

    public void setHoldLeft(String holdLeft) {
        HoldLeft = holdLeft;
    }

    public String getHoldRight() {
        return HoldRight;
    }

    public void setHoldRight(String holdRight) {
        HoldRight = holdRight;
    }
}