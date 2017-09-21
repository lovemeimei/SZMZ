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
/**
 {"result":[{"systemMsg":{"realName":null,"account":"sysadmin","systemID":"8a8a800b5e029817015e02c9106d0010"}},{"personal":{"orgCode":null,"realName":"管理员","mobilePhone":null,"emaile":null,"departName":null,"officePhone":null}}],"error":{"ErrorMessage":"登录成功","ErrorCode":"0"},"totalNum":"1"}
 */