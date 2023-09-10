package io.github.akjo03.lib.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SuppressWarnings({"unused"})
public class ErrorTemplate {
	@Getter
	private final int errorCode;
	@Getter
	private final String errorTitle;

	private final String errorMessage;

	public String getErrorMessage(Object... args) {
		return String.format(errorMessage, args);
	}
}