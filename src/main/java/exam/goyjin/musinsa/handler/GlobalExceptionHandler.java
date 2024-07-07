package exam.goyjin.musinsa.handler;

import exam.goyjin.musinsa.exceptions.CustomException;
import exam.goyjin.musinsa.model.ErrorObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({CustomException.class})
	public ResponseEntity<ErrorObject> handleCustomException(CustomException e) {

		ErrorObject errorObject = new ErrorObject(e.getResponseExceptionCodeEnums().getCode(), e.getMessage(), LocalDateTime.now());

		return ResponseEntity.status(e.getResponseExceptionCodeEnums().getHttpStatus()).contentType(MediaType.APPLICATION_JSON).body(errorObject);
	}
}
