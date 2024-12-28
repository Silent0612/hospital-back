package com.dzqc.cloud.common;

public class ResultObject {
	private Integer code;
	private String message;
	private Object data;

	public ResultObject() {
	}

	public ResultObject(Integer code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public static ResultObject success(Object data) {
		return new ResultObject(200, "操作成功", data);
	}

	public static ResultObject success(String message) {
		return new ResultObject(200, message, null);
	}

	public static ResultObject success(String message, Object data) {
		return new ResultObject(200, message, data);
	}

	public static ResultObject error(String message) {
		return new ResultObject(500, message, null);
	}

	public static ResultObject error(Integer code, String message) {
		return new ResultObject(code, message, null);
	}

	// Getters and Setters
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
