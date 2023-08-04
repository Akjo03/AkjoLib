package io.github.akjo03.lib.logging;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Accessors(chain = true)
@SuppressWarnings({"unused", "LombokGetterMayBeUsed"})
public class Logger {
	@Getter private final Class<?> source;
	@Getter private final String name;

	private final LoggingLevel minimumLoggingLevel;
	private final String loggingFormat;

	private final List<Consumer<LogMessage>> listeners;

	private final BufferedWriter outWriter;
	private final BufferedWriter errWriter;

	public Logger(Class<?> source, List<Consumer<LogMessage>> listeners, LoggingLevel minimumLoggingLevel, String loggingFormat, List<Consumer<LogMessage>> additionalListeners) {
		this.source = source;
		this.name = source.getSimpleName();

		this.minimumLoggingLevel = minimumLoggingLevel;
		this.loggingFormat = loggingFormat;

		this.listeners = additionalListeners.isEmpty() ? listeners : Stream.concat(listeners.stream(), additionalListeners.stream()).toList();

		this.outWriter = new BufferedWriter(new OutputStreamWriter(System.out));
		this.errWriter = new BufferedWriter(new OutputStreamWriter(System.err));
	}

	public Logger(String name, List<Consumer<LogMessage>> listeners, LoggingLevel minimumLoggingLevel, String loggingFormat, List<Consumer<LogMessage>> additionalListeners) {
		this.source = null;
		this.name = name;

		this.minimumLoggingLevel = minimumLoggingLevel;
		this.loggingFormat = loggingFormat;

		this.listeners = listeners;

		this.outWriter = new BufferedWriter(new OutputStreamWriter(System.out));
		this.errWriter = new BufferedWriter(new OutputStreamWriter(System.err));
	}

	private void log(@NotNull LogMessage logMessage) {
		if (logMessage.getLevel().getLevel() < minimumLoggingLevel.getLevel()) {
			return;
		}

		LogEntry logEntry = new LogEntry(source, name, logMessage, outWriter, errWriter);
		logEntry.print(loggingFormat);
		listeners.forEach(listener -> listener.accept(logMessage));
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

	public void trace(String message, Object... args) {
		log(new LogMessage(String.format(message, args), LoggingLevel.TRACE));
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

	public void debug(String message, Object... args) {
		log(new LogMessage(String.format(message, args), LoggingLevel.DEBUG));
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

	public void info(String message, Object... args) {
		log(new LogMessage(String.format(message, args), LoggingLevel.INFO));
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

	public void success(String message, Object... args) {
		log(new LogMessage(String.format(message, args), LoggingLevel.SUCCESS));
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

	public void warn(String message, Object... args) {
		log(new LogMessage(String.format(message, args), LoggingLevel.WARN));
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

	public void error(String message, Object... args) {
		log(new LogMessage(String.format(message, args), LoggingLevel.ERROR));
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

	public void fatal(String message, Object... args) {
		log(new LogMessage(String.format(message, args), LoggingLevel.FATAL));
	}
}