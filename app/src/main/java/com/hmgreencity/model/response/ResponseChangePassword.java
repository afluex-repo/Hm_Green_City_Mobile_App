package com.hmgreencity.model.response;

import com.google.gson.annotations.SerializedName;

public class ResponseChangePassword {

	@SerializedName("Status")
	private String status;

	@SerializedName("Message")
	private String errorMessage;

	public String getStatus(){
		return status;
	}

	public String getErrorMessage(){
		return errorMessage;
	}
}