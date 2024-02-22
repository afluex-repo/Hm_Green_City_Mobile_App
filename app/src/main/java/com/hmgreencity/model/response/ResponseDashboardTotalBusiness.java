package com.hmgreencity.model.response;

import com.google.gson.annotations.SerializedName;

public class ResponseDashboardTotalBusiness{

    @SerializedName("Status")
    private String status;

    @SerializedName("CarryRight")
    private String carryRight;

    @SerializedName("Message")
    private String message;

    @SerializedName("CarryLeft")
    private String carryLeft;

    @SerializedName("TeamBusiness")
    private String teamBusiness;

    @SerializedName("TotalBusinessRight")
    private String totalBusinessRight;

    @SerializedName("PaidBusinessRight")
    private String paidBusinessRight;

    @SerializedName("TotalBusinessLeft")
    private String totalBusinessLeft;

    @SerializedName("PaidBusinessLeft")
    private String paidBusinessLeft;

    public String getStatus(){
        return status;
    }

    public String getCarryRight(){
        return carryRight;
    }

    public String getMessage(){
        return message;
    }

    public String getCarryLeft(){
        return carryLeft;
    }

    public String getTeamBusiness(){
        return teamBusiness;
    }

    public String getTotalBusinessRight(){
        return totalBusinessRight;
    }

    public String getPaidBusinessRight(){
        return paidBusinessRight;
    }

    public String getTotalBusinessLeft(){
        return totalBusinessLeft;
    }

    public String getPaidBusinessLeft(){
        return paidBusinessLeft;
    }
}