package com.prestamosprima.app.ws.ui.model.response;

public class ResultRest {
	private Object data;
	private Integer status;
	private String message;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String messages) {
		this.message = messages;
	}

}
