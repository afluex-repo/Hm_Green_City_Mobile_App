package com.hmgreencity.model.response;

import com.google.gson.annotations.SerializedName;

public class ResponseSponsorName{

    @SerializedName("Status")
    private String status;

    @SerializedName("SponsorName")
    private String sponsorName;

    @SerializedName("Message")
    private String message;

    public String getStatus(){
        return status;
    }

    public String getSponsorName(){
        return sponsorName;
    }

    public String getMessage(){
        return message;
    }
}