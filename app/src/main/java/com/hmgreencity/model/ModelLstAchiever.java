package com.hmgreencity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelLstAchiever {

    @SerializedName("lstachiver")
    @Expose
    private Object lstachiver;
    @SerializedName("ImageURLpopup")
    @Expose
    private String imageURLpopup;
    @SerializedName("AchiverRankpopup")
    @Expose
    private String achiverRankpopup;
    @SerializedName("FK_RankId")
    @Expose
    private String fKRankId;
    @SerializedName("Achiver")
    @Expose
    private String achiver;

    public Object getLstachiver() {
        return lstachiver;
    }

    public void setLstachiver(Object lstachiver) {
        this.lstachiver = lstachiver;
    }

    public String getImageURLpopup() {
        return imageURLpopup;
    }

    public void setImageURLpopup(String imageURLpopup) {
        this.imageURLpopup = imageURLpopup;
    }

    public String getAchiverRankpopup() {
        return achiverRankpopup;
    }

    public void setAchiverRankpopup(String achiverRankpopup) {
        this.achiverRankpopup = achiverRankpopup;
    }

    public String getFKRankId() {
        return fKRankId;
    }

    public void setFKRankId(String fKRankId) {
        this.fKRankId = fKRankId;
    }

    public String getAchiver() {
        return achiver;
    }

    public void setAchiver(String achiver) {
        this.achiver = achiver;
    }

}
