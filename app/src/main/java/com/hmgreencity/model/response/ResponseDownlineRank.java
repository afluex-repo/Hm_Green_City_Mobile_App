package com.hmgreencity.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hmgreencity.model.ModelDownlineRankList;
import com.hmgreencity.model.ModelSponserList;

import java.util.ArrayList;

public class ResponseDownlineRank {
    @SerializedName("lstdownlineAchieverreport")
    @Expose
    private ArrayList<ModelDownlineRankList> downlineRankListArrayList;


    public ArrayList<ModelDownlineRankList> getDownlineRankListArrayList() {
        return downlineRankListArrayList;
    }

    public void setDownlineRankListArrayList(ArrayList<ModelDownlineRankList> downlineRankListArrayList) {
        this.downlineRankListArrayList = downlineRankListArrayList;
    }
}
