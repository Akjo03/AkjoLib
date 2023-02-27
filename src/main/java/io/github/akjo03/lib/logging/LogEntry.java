package io.github.akjo03.lib.logging;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.io.PrintStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
@SuppressWarnings("unused")
public class LogEntry {
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
			.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
			.withLocale(Locale.getDefault())
			.withZone(ZoneId.systemDefault());

	private final Class<?> source;
	private final String loggerName;

	private final LogMessage message;

	LogEntry(Class<?> source,  String loggerName, LogMessage message) {
		this.source = source;
		this.loggerName = loggerName;
		this.message = message;
	}

	public void print(@NotNull String format) {
		PrintStream printStream = switch (message.getLevel()) {
			case ERROR, FATAL -> System.err;
			default -> System.out;
		};
		printStream.println(
				message.getLevel().getColor() != null
						? message.getLevel().getColor().colorize(generate(format), message.getLevel().isBold())
						: generate(format)
		);
	}

	public String generate(@NotNull String format) {
		String result = format
				.replace("%t", DATE_TIME_FORMATTER.format(Instant.now()))
				.replace("%h", Thread.currentThread().getName())
				.replace("%c", source != null ? source.getName() : loggerName)
				.replace("%i", source != null ? source.getSimpleName() : loggerName)
				.replace("%a", loggerName)
				.replace("%n", System.lineSeparator())
				.replace("%l", message.getLevel().getName())
				.replace("%m", message.getMessage());

		if (message.getThrowable() != null) {
			result += " | " + message.getThrowable().getClass().getName() + ": " + message.getThrowable().getLocalizedMessage();
		}

		return result;
	}
}