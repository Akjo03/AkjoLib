package io.github.akjo03.lib.exceptions;

@SuppressWarnings("unused")
public class NotYetImplementedException extends RuntimeException {
	public NotYetImplementedException() {
		super("This feature is not yet implemented.");
	}

	public NotYetImplementedException(String message) {
		super(message);
	}
}