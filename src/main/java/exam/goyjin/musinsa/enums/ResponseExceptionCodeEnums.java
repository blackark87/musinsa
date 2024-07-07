package exam.goyjin.musinsa.enums;

import exam.goyjin.musinsa.interfaces.ResponseCode;
import lombok.Getter;

@Getter
public enum ResponseExceptionCodeEnums implements ResponseCode {

	NOT_FOUND(404, 404001, "찾을수 없습니다."),
	NOT_EXIST(404, 404002, "존재 하지 않습니다."),
	NO_BRAND(400, 400001, "브랜드 정보가 없습니다."),
	NO_CATEGORY(400, 400002, "카테고리 정보가 없습니다."),
	NO_PRICE(400, 400003, "가격 정보가 없습니다."),
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
