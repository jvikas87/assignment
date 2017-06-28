package com.congnitive.scale.assignment.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class APIResponse {

	private String response;
	
	private Object data;

	private String message;
	
	public APIResponse(String response, Object data) {
		this.response = response;
		this.data = data;
	}

	public APIResponse(String response, String message) {
		this.response = response;
		this.message = message;
	}

	public Object getData() {
		return data;
	}
	
	public String getMessage() {
		return message;
	}

	public String getResponse() {
		return response;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
