package com.hmgreencity.model.response;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("Status")
	private String status;

	@SerializedName("LoginId")
	private String loginId;

	@SerializedName("Message")
	private String message;

	@SerializedName("FranchiseAdminID")
	private String franchiseAdminID;

	@SerializedName("UserId")
	private String userId;

	@SerializedName("FullName")
	private String fullName;

	@SerializedName("Pk_adminId")
	private String pkAdminId;

	@SerializedName("UserType")
	private String userType;

	@SerializedName("Profile")
	private String profile;

	@SerializedName("Password")
	private String password;

	public String getStatus(){
		return status;
	}

	public String getLoginId(){
		return loginId;
	}

	public String getMessage(){
		return message;
	}

	public String getFranchiseAdminID(){
		return franchiseAdminID;
	}

	public String getUserId(){
		return userId;
	}

	public String getFullName(){
		return fullName;
	}

	public String getPkAdminId(){
		return pkAdminId;
	}

	public String getUserType(){
		return userType;
	}

	public String getProfile(){
		return profile;
	}

	public String getPassword(){
		return password;
	}
}