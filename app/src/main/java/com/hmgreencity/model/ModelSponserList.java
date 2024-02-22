package com.hmgreencity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelSponserList {
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("LoginIDD")
    @Expose
    private String loginIDD;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginIDD() {
        return loginIDD;
    }

    public void setLoginIDD(String loginIDD) {
        this.loginIDD = loginIDD;
    }
}
