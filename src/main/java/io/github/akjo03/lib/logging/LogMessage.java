package io.github.akjo03.lib.logging;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@SuppressWarnings("unused")
public class LogMessage {
	private final String message;
	private final LoggingLevel level;
	private final Throwable throwable;

	public LogMessage(@NotNull String message, @NotNull LoggingLevel level) {
		this.message = message;
		this.level = level;
		this.throwable = null;
	}

	public LogMessage(@NotNull String message, @NotNull LoggingLevel level, @NotNull Throwable throwable) {
		this.message = message;
		this.level = level;
		this.throwable = throwable;
	}

	public LogMessage(@NotNull Throwable throwable) {
		this.message = throwable.getMessage();
		this.level = LoggingLevel.ERROR;
		this.throwable = throwable;
	}

	public LogMessage(@NotNull Throwable throwable, @NotNull LoggingLevel level) {
		this.message = throwable.getMessage();
		this.level = level;
		this.throwable = throwable;
	}

	public LogMessage(Object message, @NotNull LoggingLevel level) {
		if (message == null) {
			this.message = "null";
		} else {
			this.message = message.toString();
		}
		this.level = level;
		this.throwable = null;
	}

	public LogMessage(Object message, @NotNull LoggingLevel level, @NotNull Throwable throwable) {
		if (message == null) {
			this.message = "null";
		} else {
			this.message = message.toString();
		}
		this.level = level;
		this.throwable = throwable;
	}
}