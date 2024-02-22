package com.hmgreencity.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hmgreencity.model.ModelSponserList;

import java.util.ArrayList;

public class ResponseSponsorList {


    @SerializedName("lstsponserlist")
    @Expose
    private ArrayList<ModelSponserList> sponserListArrayList;


    public ArrayList<ModelSponserList> getSponserListArrayList() {
        return sponserListArrayList;
    }

    public void setSponserListArrayList(ArrayList<ModelSponserList> sponserListArrayList) {
        this.sponserListArrayList = sponserListArrayList;
    }
}
