package io.github.akjo03.lib.validation;

import java.io.Serial;

@SuppressWarnings("unused")
public class ValidationException extends RuntimeException {
	@Serial private static final long serialVersionUID = 1L;

	public ValidationException(String message) { super(message); }
}