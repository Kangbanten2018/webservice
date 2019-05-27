package com.meridianid.farizdotid.mahasiswaapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDosen2 {

	@SerializedName("data")
	private List<SemuadosenItem2> semuadosen;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public void setSemuadosen(List<SemuadosenItem2> semuadosen){
		this.semuadosen = semuadosen;
	}

	public List<SemuadosenItem2> getSemuadosen(){
		return semuadosen;
	}

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"ResponseDosen{" + 
			"semuadosen = '" + semuadosen + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}