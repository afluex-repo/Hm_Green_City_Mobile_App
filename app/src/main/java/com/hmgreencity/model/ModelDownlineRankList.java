package com.hmgreencity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelDownlineRankList {

    @SerializedName("FK_RankId")
    @Expose
    private String fKRankId;
    @SerializedName("RankName")
    @Expose
    private String rankName;
    @SerializedName("RewardImage")
    @Expose
    private String rewardImage;
    @SerializedName("TotalAchieverLeft")
    @Expose
    private String totalAchieverLeft;
    @SerializedName("TotalAchieverRight")
    @Expose
    private String totalAchieverRight;

    public String getFKRankId() {
        return fKRankId;
    }

    public void setFKRankId(String fKRankId) {
        this.fKRankId = fKRankId;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public String getRewardImage() {
        return rewardImage;
    }

    public void setRewardImage(String rewardImage) {
        this.rewardImage = rewardImage;
    }

    public String getTotalAchieverLeft() {
        return totalAchieverLeft;
    }

    public void setTotalAchieverLeft(String totalAchieverLeft) {
        this.totalAchieverLeft = totalAchieverLeft;
    }

    public String getTotalAchieverRight() {
        return totalAchieverRight;
    }

    public void setTotalAchieverRight(String totalAchieverRight) {
        this.totalAchieverRight = totalAchieverRight;
    }
}
