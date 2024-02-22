package com.hmgreencity.model.response.responseBusinessReport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lstassociate {

    @SerializedName("Leg")
    @Expose
    private String leg;
    @SerializedName("ClosingDate")
    @Expose
    private String closingDate;
    @SerializedName("DisplayName")
    @Expose
    private String displayName;
    @SerializedName("LoginId")
    @Expose
    private String loginId;
    @SerializedName("NetAmount")
    @Expose
    private String netAmount;
    @SerializedName("LeadershipBonus")
    @Expose
    private String leadershipBonus;

    public String getLeg() {
        return leg;
    }

    public void setLeg(String leg) {
        this.leg = leg;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

    public String getLeadershipBonus() {
        return leadershipBonus;
    }

    public void setLeadershipBonus(String leadershipBonus) {
        this.leadershipBonus = leadershipBonus;
    }


}
