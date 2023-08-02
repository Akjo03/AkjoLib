package io.github.akjo03.lib.logging;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

@Getter
@Accessors(chain = true)
@SuppressWarnings("unused")
public class Logger {
	private final Class<?> source;
	private final String name;

	private final BufferedWriter outWriter;
	private final BufferedWriter errWriter;

	@Setter private LoggingLevel minimumLoggingLevel = LoggingLevel.INFO;
	@Setter private String loggingFormat = "[%t] [%c / %l]: %m";

	public Logger(Class<?> source) {
		this.source = source;
		this.name = source.getSimpleName();

		this.outWriter = new BufferedWriter(new OutputStreamWriter(System.out));
		this.errWriter = new BufferedWriter(new OutputStreamWriter(System.err));
	}

	public Logger(String name) {
		this.source = null;
		this.name = name;

		this.outWriter = new BufferedWriter(new OutputStreamWriter(System.out));
		this.errWriter = new BufferedWriter(new OutputStreamWriter(System.err));
	}

	private void log(@NotNull LogMessage logMessage) {
		if (logMessage.getLevel().getLevel() < minimumLoggingLevel.getLevel()) {
			return;
		}

		LogEntry logEntry = new LogEntry(source, name, logMessage, outWriter, errWriter);
		logEntry.print(loggingFormat);
	}

	public void empty() {
		System.out.println();
	}

	public void trace(Object obj) {
		log(new LogMessage(obj, LoggingLevel.TRACE));
	}

	public void trace(Object obj, Throwable throwable) {
		log(new LogMessage(obj, LoggingLevel.TRACE, throwable));
	}

	public void trace(String message) {
		log(new LogMessage(message, LoggingLevel.TRACE));
	}

	public void trace(Throwable throwable) {
		log(new LogMessage(throwable, LoggingLevel.TRACE));
	}

	public void trace(String message, Throwable throwable) {
		log(new LogMessage(message, LoggingLevel.TRACE, throwable));
	}

	public void debug(Object obj) {
		log(new LogMessage(obj, LoggingLevel.DEBUG));
	}

	public void debug(Object obj, Throwable throwable) {
		log(new LogMessage(obj, LoggingLevel.DEBUG, throwable));
	}

	public void debug(String message) {
		log(new LogMessage(message, LoggingLevel.DEBUG));
	}

	public void debug(Throwable throwable) {
		log(new LogMessage(throwable, LoggingLevel.DEBUG));
	}

	public void debug(String message, Throwable throwable) {
		log(new LogMessage(message, LoggingLevel.DEBUG, throwable));
	}

	public void info(Object obj) {
		log(new LogMessage(obj, LoggingLevel.INFO));
	}

	public void info(Object obj, Throwable throwable) {
		log(new LogMessage(obj, LoggingLevel.INFO, throwable));
	}

	public void info(String message) {
		log(new LogMessage(message, LoggingLevel.INFO));
	}

	public void info(Throwable throwable) {
		log(new LogMessage(throwable, LoggingLevel.INFO));
	}

	public void info(String message, Throwable throwable) {
		log(new LogMessage(message, LoggingLevel.INFO, throwable));
	}

	public void success(Object obj) {
		log(new LogMessage(obj, LoggingLevel.SUCCESS));
	}

	public void success(Object obj, Throwable throwable) {
		log(new LogMessage(obj, LoggingLevel.SUCCESS, throwable));
	}

	public void success(String message) {
		log(new LogMessage(message, LoggingLevel.SUCCESS));
	}

	public void success(Throwable throwable) {
		log(new LogMessage(throwable, LoggingLevel.SUCCESS));
	}

	public void success(String message, Throwable throwable) {
		log(new LogMessage(message, LoggingLevel.SUCCESS, throwable));
	}

	public void warn(Object obj) {
		log(new LogMessage(obj, LoggingLevel.WARN));
	}

	public void warn(Object obj, Throwable throwable) {
		log(new LogMessage(obj, LoggingLevel.WARN, throwable));
	}

	public void warn(String message) {
		log(new LogMessage(message, LoggingLevel.WARN));
	}

	public void warn(Throwable throwable) {
		log(new LogMessage(throwable, LoggingLevel.WARN));
	}

	public void warn(String message, Throwable throwable) {
		log(new LogMessage(message, LoggingLevel.WARN, throwable));
	}

	public void error(Object obj) {
		log(new LogMessage(obj, LoggingLevel.ERROR));
	}

	public void error(Object obj, Throwable throwable) {
		log(new LogMessage(obj, LoggingLevel.ERROR, throwable));
	}

	public void error(String message) {
		log(new LogMessage(message, LoggingLevel.ERROR));
	}

	public void error(Throwable throwable) {
		log(new LogMessage(throwable, LoggingLevel.ERROR));
	}

	public void error(String message, Throwable throwable) {
		log(new LogMessage(message, LoggingLevel.ERROR, throwable));
	}

	public void fatal(Object obj) {
		log(new LogMessage(obj, LoggingLevel.FATAL));
	}

	public void fatal(Object obj, Throwable throwable) {
		log(new LogMessage(obj, LoggingLevel.FATAL, throwable));
	}

	public void fatal(String message) {
		log(new LogMessage(message, LoggingLevel.FATAL));
	}

	public void fatal(Throwable throwable) {
		log(new LogMessage(throwable, LoggingLevel.FATAL));
	}

	public void fatal(String message, Throwable throwable) {
		log(new LogMessage(message, LoggingLevel.FATAL, throwable));
	}
}