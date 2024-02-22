package com.hmgreencity.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hmgreencity.model.ModelLstAchiever;
import com.hmgreencity.model.response.responseTotalInvastment.Lstinvestment;

import java.util.ArrayList;
import java.util.List;

public class ResponseDashboard{

	@SerializedName("lstinvestment")
	@Expose
	private List<Lstinvestment> lstinvestment = null;
	@SerializedName("SelfBusiness")
	@Expose
	private String selfBusiness;



	@SerializedName("lstachiver")
	@Expose
	private ArrayList<ModelLstAchiever> achieverArrayList;
	@SerializedName("Status")
	@Expose
	private String status;
	@SerializedName("Message")
	@Expose
	private String message;
	@SerializedName("TotalDownline")
	@Expose
	private String totalDownline;
	@SerializedName("TotalDirects")
	@Expose
	private String totalDirects;
	@SerializedName("PayoutWalletBalance")
	@Expose
	private String payoutWalletBalance;
	@SerializedName("TotalPayout")
	@Expose
	private String totalPayout;
	@SerializedName("TotalDeduction")
	@Expose
	private String totalDeduction;
	@SerializedName("TotalAdvance")
	@Expose
	private String totalAdvance;
	@SerializedName("TotalActive")
	@Expose
	private String totalActive;
	@SerializedName("TotalInActive")
	@Expose
	private String totalInActive;
	@SerializedName("UnpaidIncome")
	@Expose
	private String unpaidIncome;
	@SerializedName("PaidBusinessLeft")
	@Expose
	private String paidBusinessLeft;
	@SerializedName("PaidBusinessRight")
	@Expose
	private String paidBusinessRight;
	@SerializedName("TotalBusinessLeft")
	@Expose
	private String totalBusinessLeft;
	@SerializedName("TotalBusinessRight")
	@Expose
	private String totalBusinessRight;
	@SerializedName("CarryLeft")
	@Expose
	private String carryLeft;
	@SerializedName("CarryRight")
	@Expose
	private String carryRight;
	@SerializedName("Fk_UserId")
	@Expose
	private String fkUserId;

	@SerializedName("ImageURL")
	@Expose
	private String ImageURL;

	@SerializedName("AchiverRank")
	@Expose
	private String AchiverRank;
	@SerializedName("Name")
	@Expose
	private String Name;
	@SerializedName("ProfilePic")
	@Expose
	private String ProfilePic;





@SerializedName("TotalHold")
	@Expose
	private String TotalHold;



	public List<Lstinvestment> getLstinvestment() {
		return lstinvestment;
	}

	public void setLstinvestment(List<Lstinvestment> lstinvestment) {
		this.lstinvestment = lstinvestment;
	}

	public String getSelfBusiness() {
		return selfBusiness;
	}

	public void setSelfBusiness(String selfBusiness) {
		this.selfBusiness = selfBusiness;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTotalDownline() {
		return totalDownline;
	}

	public void setTotalDownline(String totalDownline) {
		this.totalDownline = totalDownline;
	}

	public String getTotalDirects() {
		return totalDirects;
	}

	public void setTotalDirects(String totalDirects) {
		this.totalDirects = totalDirects;
	}

	public String getPayoutWalletBalance() {
		return payoutWalletBalance;
	}

	public void setPayoutWalletBalance(String payoutWalletBalance) {
		this.payoutWalletBalance = payoutWalletBalance;
	}

	public String getTotalPayout() {
		return totalPayout;
	}

	public void setTotalPayout(String totalPayout) {
		this.totalPayout = totalPayout;
	}

	public String getTotalDeduction() {
		return totalDeduction;
	}

	public void setTotalDeduction(String totalDeduction) {
		this.totalDeduction = totalDeduction;
	}

	public String getTotalAdvance() {
		return totalAdvance;
	}

	public void setTotalAdvance(String totalAdvance) {
		this.totalAdvance = totalAdvance;
	}

	public String getTotalActive() {
		return totalActive;
	}

	public void setTotalActive(String totalActive) {
		this.totalActive = totalActive;
	}

	public String getTotalInActive() {
		return totalInActive;
	}

	public void setTotalInActive(String totalInActive) {
		this.totalInActive = totalInActive;
	}

	public String getUnpaidIncome() {
		return unpaidIncome;
	}

	public void setUnpaidIncome(String unpaidIncome) {
		this.unpaidIncome = unpaidIncome;
	}

	public String getPaidBusinessLeft() {
		return paidBusinessLeft;
	}

	public void setPaidBusinessLeft(String paidBusinessLeft) {
		this.paidBusinessLeft = paidBusinessLeft;
	}

	public String getPaidBusinessRight() {
		return paidBusinessRight;
	}

	public void setPaidBusinessRight(String paidBusinessRight) {
		this.paidBusinessRight = paidBusinessRight;
	}

	public String getTotalBusinessLeft() {
		return totalBusinessLeft;
	}

	public void setTotalBusinessLeft(String totalBusinessLeft) {
		this.totalBusinessLeft = totalBusinessLeft;
	}

	public String getTotalBusinessRight() {
		return totalBusinessRight;
	}

	public void setTotalBusinessRight(String totalBusinessRight) {
		this.totalBusinessRight = totalBusinessRight;
	}

	public String getCarryLeft() {
		return carryLeft;
	}

	public void setCarryLeft(String carryLeft) {
		this.carryLeft = carryLeft;
	}

	public String getCarryRight() {
		return carryRight;
	}

	public void setCarryRight(String carryRight) {
		this.carryRight = carryRight;
	}

	public String getFkUserId() {
		return fkUserId;
	}

	public void setFkUserId(String fkUserId) {
		this.fkUserId = fkUserId;
	}


	public String getImageURL() {
		return ImageURL;
	}

	public void setImageURL(String imageURL) {
		ImageURL = imageURL;
	}

	public String getAchiverRank() {
		return AchiverRank;
	}

	public void setAchiverRank(String achiverRank) {
		AchiverRank = achiverRank;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getProfilePic() {
		return ProfilePic;
	}

	public void setProfilePic(String profilePic) {
		ProfilePic = profilePic;
	}


	public String getTotalHold() {
		return TotalHold;
	}

	public void setTotalHold(String totalHold) {
		TotalHold = totalHold;
	}


	public ArrayList<ModelLstAchiever> getAchieverArrayList() {
		return achieverArrayList;
	}

	public void setAchieverArrayList(ArrayList<ModelLstAchiever> achieverArrayList) {
		this.achieverArrayList = achieverArrayList;
	}
}