package com.szmz.entity.response;

/**
 * 错误消息封装
 *
 */
public class Error {

	private Integer errorCode;
	private String errorMessage;
	
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
/**
 {"result":[{"systemMsg":{"realName":null,"account":"sysadmin","systemID":"8a8a800b5e029817015e02c9106d0010"}},{"personal":{"orgCode":null,"realName":"管理员","mobilePhone":null,"emaile":null,"departName":null,"officePhone":null}}],"error":{"ErrorMessage":"登录成功","ErrorCode":"0"},"totalNum":"1"}
 */