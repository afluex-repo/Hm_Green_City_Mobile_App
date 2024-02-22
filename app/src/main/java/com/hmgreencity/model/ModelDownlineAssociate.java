package com.hmgreencity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelDownlineAssociate {
    @SerializedName("Fk_UserId")
    @Expose
    private String fkUserId;
    @SerializedName("LoginId")
    @Expose
    private String loginId;
    @SerializedName("Name")
    @Expose
    private String name;

    public String getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(String fkUserId) {
        this.fkUserId = fkUserId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
