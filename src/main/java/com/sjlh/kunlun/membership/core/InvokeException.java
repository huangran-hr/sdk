/**
 * 
 */
package com.sjlh.kunlun.membership.core;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Administrator
 *
 */
@Setter
@Getter
public class InvokeException extends RuntimeException {
	private static final long serialVersionUID = -5229601673436963085L;
	
	private Integer code;
	private String msg;
	
	/**
	 * 
	 */
	public InvokeException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvokeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public InvokeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * @param message
	 */
	public InvokeException(String message) {
		super(message);
	}
	
	/**
	 * @param cause
	 */
	public InvokeException(Throwable cause) {
		super(cause);
	}
}