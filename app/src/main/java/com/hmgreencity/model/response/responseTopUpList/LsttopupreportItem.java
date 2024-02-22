package com.hmgreencity.model.response.responseTopUpList;

import com.google.gson.annotations.SerializedName;

public class LsttopupreportItem{

    @SerializedName("ProductName")
    private String productName;

    @SerializedName("SiteName")
    private String siteName;

    @SerializedName("FK_InvestmentID")
    private String fKInvestmentID;

    @SerializedName("Amount")
    private String amount;

    @SerializedName("SectorName")
    private String sectorName;

    @SerializedName("UpgradtionDate")
    private String upgradtionDate;

    @SerializedName("Name")
    private String name;

    @SerializedName("PlotNumber")
    private String plotNumber;

    public String getProductName(){
        return productName;
    }

    public String getSiteName(){
        return siteName;
    }

    public String getFKInvestmentID(){
        return fKInvestmentID;
    }

    public String getAmount(){
        return amount;
    }

    public String getSectorName(){
        return sectorName;
    }

    public String getUpgradtionDate(){
        return upgradtionDate;
    }

    public String getName(){
        return name;
    }

    public String getfKInvestmentID() {
        return fKInvestmentID;
    }

    public String getPlotNumber() {
        return plotNumber;
    }
}