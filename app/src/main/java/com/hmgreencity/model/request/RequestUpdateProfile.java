package com.hmgreencity.model.request;

import com.google.gson.annotations.SerializedName;

public class RequestUpdateProfile{

    @SerializedName("PK_UserId")
    private String pKUserId;

    @SerializedName("BankName")
    private String bankName;

    @SerializedName("EmailId")
    private String emailId;

    @SerializedName("BankBranch")
    private String bankBranch;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("LastName")
    private String lastName;

    @SerializedName("Mobile")
    private String mobile;

    @SerializedName("AccountNumber")
    private String accountNumber;

    @SerializedName("IFSC")
    private String iFSC;

    public void setPKUserId(String pKUserId){
        this.pKUserId = pKUserId;
    }

    public void setBankName(String bankName){
        this.bankName = bankName;
    }

    public void setEmailId(String emailId){
        this.emailId = emailId;
    }

    public void setBankBranch(String bankBranch){
        this.bankBranch = bankBranch;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

    public void setIFSC(String iFSC){
        this.iFSC = iFSC;
    }
}