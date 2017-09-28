package com.szmz.entity.response;

/**
 * 错误消息封装
 *
 */
public class Error {

	private Integer ErrorCode;
	private String ErrorMessage;
	
	public Integer getErrorCode() {
		return ErrorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.ErrorCode = errorCode;
	}
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.ErrorMessage = errorMessage;
	}
}