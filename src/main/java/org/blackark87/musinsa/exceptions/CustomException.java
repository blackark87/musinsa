package org.blackark87.musinsa.exceptions;

import lombok.Getter;
import org.blackark87.musinsa.enums.ResponseExceptionCodeEnums;

@Getter
public class CustomException extends RuntimeException{

	private ResponseExceptionCodeEnums responseExceptionCodeEnums = null;

	public CustomException(String message) {
		super(message);
	}

	public CustomException(ResponseExceptionCodeEnums code) {
		super(code.getMessage());
		this.responseExceptionCodeEnums = code;
	}
}
