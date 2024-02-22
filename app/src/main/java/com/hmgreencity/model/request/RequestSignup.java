package com.hmgreencity.model.request;

import com.google.gson.annotations.SerializedName;

public class RequestSignup{

	@SerializedName("SponsorId")
	private String sponsorId;

	@SerializedName("MobileNo")
	private String mobileNo;

	@SerializedName("Email")
	private String email;

	@SerializedName("RegistrationBy")
	private String registrationBy;

	@SerializedName("Address")
	private String address;

	@SerializedName("FirstName")
	private String firstName;

	@SerializedName("LastName")
	private String lastName;

	@SerializedName("PanCard")
	private String panCard;

	@SerializedName("Gender")
	private String gender;

	@SerializedName("Leg")
	private String leg;

	@SerializedName("PinCode")
	private String pinCode;

	public void setSponsorId(String sponsorId){
		this.sponsorId = sponsorId;
	}

	public void setMobileNo(String mobileNo){
		this.mobileNo = mobileNo;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setRegistrationBy(String registrationBy){
		this.registrationBy = registrationBy;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public void setPanCard(String panCard){
		this.panCard = panCard;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public void setLeg(String leg){
		this.leg = leg;
	}

	public void setPinCode(String pinCode){
		this.pinCode = pinCode;
	}
}