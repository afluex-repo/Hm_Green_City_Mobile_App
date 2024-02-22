package com.hmgreencity.model.request;

import com.google.gson.annotations.SerializedName;

public class RequestLogin{

    @SerializedName("LoginId")
    private String loginId;

    @SerializedName("Password")
    private String password;

    public void setLoginId(String loginId){
        this.loginId = loginId;
    }

    public void setPassword(String password){
        this.password = password;
    }
}