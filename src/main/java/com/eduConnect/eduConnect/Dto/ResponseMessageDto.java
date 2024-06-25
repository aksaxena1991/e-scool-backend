package com.eduConnect.eduConnect.Dto;

import java.util.Date;

public class ResponseMessageDto {

	private String status;
	private String message;
	private String error;
	private Date timestamp;
	private boolean verified;

	public ResponseMessageDto() {
		super();
	}

	public ResponseMessageDto(String status, String message, String error, Date timestamp) {
		super();
		this.status = status;
		this.message = message;
		this.error = error;
		this.timestamp = timestamp;
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

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
}
