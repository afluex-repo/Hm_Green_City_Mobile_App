package com.hmgreencity.model.response.treeView;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseTreeView{

    @SerializedName("Status")
    private String status;

    @SerializedName("Message")
    private String message;

    @SerializedName("LoginId")
    private String loginId;

    @SerializedName("GetGenelogy")
    private List<GetGenelogyItem> getGenelogy;

    @SerializedName("Fk_headId")
    private String fkHeadId;

    public String getStatus(){
        return status;
    }

    public String getMessage(){
        return message;
    }

    public String getLoginId(){
        return loginId;
    }

    public List<GetGenelogyItem> getGetGenelogy(){
        return getGenelogy;
    }

    public String getFkHeadId(){
        return fkHeadId;
    }
}