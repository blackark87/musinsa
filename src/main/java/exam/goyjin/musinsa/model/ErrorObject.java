package exam.goyjin.musinsa.model;

import java.time.LocalDateTime;

public record ErrorObject(
		int code,
		String message,
		LocalDateTime timestamp
) {
}
