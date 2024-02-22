package com.hmgreencity.model.request;

import com.google.gson.annotations.SerializedName;

public class RequestTopUpList {


    @SerializedName("LoginId")
    private String loginId;
    @SerializedName("Name")
    private String Name;
    @SerializedName("FromDate")
    private String FromDate;
    @SerializedName("ToDate")
    private String ToDate;
    @SerializedName("Package")
    private String  Package;
    @SerializedName("Status")
    private String Status;

    public void setLoginId(String loginId){
        this.loginId = loginId;
    }

    public String getName() {
        return Name;
    }

    public String getFromDate() {
        return FromDate;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPackage() {
        return Package;
    }

    public String getStatus() {
        return Status;
    }

    public String getToDate() {
        return ToDate;
    }

}
