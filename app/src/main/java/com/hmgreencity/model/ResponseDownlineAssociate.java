package com.hmgreencity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseDownlineAssociate {

    @SerializedName("lstdownAchieverAssoreporttt")
    @Expose
    private ArrayList<ModelDownlineAssociate> downlineAssociateArrayList;

    public ArrayList<ModelDownlineAssociate> getDownlineAssociateArrayList() {
        return downlineAssociateArrayList;
    }

    public void setDownlineAssociateArrayList(ArrayList<ModelDownlineAssociate> downlineAssociateArrayList) {
        this.downlineAssociateArrayList = downlineAssociateArrayList;
    }
}
