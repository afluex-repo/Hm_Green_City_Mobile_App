package com.hmgreencity.model.request;

import com.google.gson.annotations.SerializedName;

public class RequestKYC {

    @SerializedName("fk_UserId")
    private String fk_UserId;


 @SerializedName("Pk_UserID")
    private String Pk_UserID;
    @SerializedName("AdharImage")
    private String AdharImage;
    @SerializedName("AdharNumber")
    private String AdharNumber;

    @SerializedName("PanNumber")
    private String PanNumber;
    @SerializedName("PanImage")
    private String PanImage;

    @SerializedName("DocNumber")
    private String DocNumber;
    @SerializedName("DocImage")
    private String DocImage;


    public void setFk_UserId(String fk_UserId) {
        this.fk_UserId = fk_UserId;
    }

    public void setAdharImage(String adharImage) {
        AdharImage = adharImage;
    }

    public void setAdharNumber(String adharNumber) {
        AdharNumber = adharNumber;
    }

    public void setPanNumber(String panNumber) {
        PanNumber = panNumber;
    }

    public void setPanImage(String panImage) {
        PanImage = panImage;
    }

    public void setDocNumber(String docNumber) {
        DocNumber = docNumber;
    }

    public void setDocImage(String docImage) {
        DocImage = docImage;
    }


    public void setPk_UserID(String pk_UserID) {
        Pk_UserID = pk_UserID;
    }
}
