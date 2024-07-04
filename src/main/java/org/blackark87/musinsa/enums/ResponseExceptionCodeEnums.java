package org.blackark87.musinsa.enums;

import lombok.Getter;
import org.blackark87.musinsa.interfaces.ResponseCode;

@Getter
public enum ResponseExceptionCodeEnums implements ResponseCode {

	NOT_FOUND(404, 404001, "Not Found"),
	;

	private final int httpStatus;
	private final int code;
	private final String message;

	ResponseExceptionCodeEnums(int httpStatus, int code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}
}
