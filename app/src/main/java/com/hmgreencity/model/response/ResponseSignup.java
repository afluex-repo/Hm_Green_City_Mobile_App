package com.hmgreencity.model.response;

import com.google.gson.annotations.SerializedName;

public class ResponseSignup{

	@SerializedName("Status")
	private String status;

	@SerializedName("MobileNo")
	private String mobileNo;

	@SerializedName("RegistrationBy")
	private String registrationBy;

	@SerializedName("Email")
	private String email;

	@SerializedName("Message")
	private String message;

	@SerializedName("LoginId")
	private String loginId;

	@SerializedName("Address")
	private String address;

	@SerializedName("FirstName")
	private String firstName;

	@SerializedName("PanCard")
	private String panCard;

	@SerializedName("Gender")
	private String gender;

	@SerializedName("Leg")
	private String leg;

	@SerializedName("PinCode")
	private String pinCode;

	@SerializedName("SponsorId")
	private String sponsorId;

	@SerializedName("TransPassword")
	private String transPassword;

	@SerializedName("FullName")
	private String fullName;

	@SerializedName("LastName")
	private String lastName;

	@SerializedName("Password")
	private String password;

	public String getStatus(){
		return status;
	}

	public String getMobileNo(){
		return mobileNo;
	}

	public String getRegistrationBy(){
		return registrationBy;
	}

	public String getEmail(){
		return email;
	}

	public String getMessage(){
		return message;
	}

	public String getLoginId(){
		return loginId;
	}

	public String getAddress(){
		return address;
	}

	public String getFirstName(){
		return firstName;
	}

	public String getPanCard(){
		return panCard;
	}

	public String getGender(){
		return gender;
	}

	public String getLeg(){
		return leg;
	}

	public String getPinCode(){
		return pinCode;
	}

	public String getSponsorId(){
		return sponsorId;
	}

	public String getTransPassword(){
		return transPassword;
	}

	public String getFullName(){
		return fullName;
	}

	public String getLastName(){
		return lastName;
	}

	public String getPassword(){
		return password;
	}
}