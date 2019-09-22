package com.prestamosprima.app.ws.shared.exception;

import com.prestamosprima.app.ws.ui.model.response.ResultRest;

public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5706678689747236230L;
	
	private ResultRest result;

	public BusinessException(ResultRest result) {
		super();
		this.result = result;
	}
	
	public BusinessException(Integer status, String message) {
		super();
		this.result= new ResultRest();
		this.result.setStatus(status);
		this.result.setMessage(message);
	}

	public ResultRest getResult() {
		return result;
	}

	public void setResult(ResultRest result) {
		this.result = result;
	}
	
	
	
}
