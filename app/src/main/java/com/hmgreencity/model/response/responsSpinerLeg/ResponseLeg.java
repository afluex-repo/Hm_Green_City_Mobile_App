package com.hmgreencity.model.response.responsSpinerLeg;

import android.widget.Spinner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseLeg {

    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("Leg")
    @Expose
    private String leg;

    public List<Item> getItems() {
        return items;
    }


    public String getLeg() {
        return leg;
    }



}

