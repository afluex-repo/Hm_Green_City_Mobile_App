package com.hmgreencity.model.response.responsSpinerLeg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("LegId")
    @Expose
    private String legId;
    @SerializedName("LegName")
    @Expose
    private String legName;

    public String getLegId() {
        return legId;
    }


    public String getLegName() {
        return legName;
    }


}
