package com.hmgreencity.model.request;

import com.google.gson.annotations.SerializedName;

public class RequestChangePassword {

	@SerializedName("NewPassword")
	private String newPassword;

	@SerializedName("OldPassword")
	private String oldPassword;

	@SerializedName("UpdatedBy")
	private String pkParentId;

	@SerializedName("PasswordType")
	private String PasswordType;

	public void setPasswordType(String passwordType) {
		PasswordType = passwordType;
	}

	public void setNewPassword(String newPassword){
		this.newPassword = newPassword;
	}

	public void setOldPassword(String oldPassword){
		this.oldPassword = oldPassword;
	}

	public void setPkParentId(String pkParentId){
		this.pkParentId = pkParentId;
	}
}