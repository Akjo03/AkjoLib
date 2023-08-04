package io.github.akjo03.lib.logging;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings({"unused", "OptionalGetWithoutIsPresent"})
public class LoggerManager {
	private static final LoggingLevel DEFAULT_MINIMUM_LOGGING_LEVEL = LoggingLevel.INFO;
	private static final String DEFAULT_LOGGING_FORMAT = "[%t] [%c / %l]: %m";

	private static final List<Logger> LOGGERS = new ArrayList<>();
	private static final List<Consumer<LogMessage>> LOGGERS_LISTENERS = new ArrayList<>();

	public static void addLoggerListener(Consumer<LogMessage> listener) {
		LOGGERS_LISTENERS.add(listener);
	}

	public static @NotNull Logger getLogger(Class<?> element) {
		return getLogger(element, DEFAULT_MINIMUM_LOGGING_LEVEL, DEFAULT_LOGGING_FORMAT, new ArrayList<>());
	}

	public static @NotNull Logger getLogger(String name) {
		return getLogger(name, DEFAULT_MINIMUM_LOGGING_LEVEL, DEFAULT_LOGGING_FORMAT, new ArrayList<>());
	}

	public static @NotNull Logger getLogger(Class<?> source, LoggingLevel minLevel) {
		return getLogger(source, minLevel, DEFAULT_LOGGING_FORMAT, new ArrayList<>());
	}

	public static @NotNull Logger getLogger(String name, LoggingLevel minLevel) {
		return getLogger(name, minLevel, DEFAULT_LOGGING_FORMAT, new ArrayList<>());
	}

	public static @NotNull Logger getLogger(Class<?> source, String loggingFormat) {
		return getLogger(source, DEFAULT_MINIMUM_LOGGING_LEVEL, loggingFormat, new ArrayList<>());
	}

	public static @NotNull Logger getLogger(String name, String loggingFormat) {
		return getLogger(name, DEFAULT_MINIMUM_LOGGING_LEVEL, loggingFormat, new ArrayList<>());
	}

	public static @NotNull Logger getLogger(Class<?> source, LoggingLevel minLevel, String loggingFormat) {
		return getLogger(source, minLevel, loggingFormat, new ArrayList<>());
	}

	public static @NotNull Logger getLogger(Class<?> source, LoggingLevel minLevel, String loggingFormat, List<Consumer<LogMessage>> listeners) {
		if (LOGGERS.stream().anyMatch(logger -> logger.getSource().equals(source))) {
			return LOGGERS.stream().filter(loggerP -> loggerP.getSource().equals(source)).findFirst().get();
		}

		Logger logger = new Logger(source, LOGGERS_LISTENERS, minLevel, loggingFormat, listeners);
		LOGGERS.add(logger);
		return logger;
	}

	public static @NotNull Logger getLogger(String name, LoggingLevel minLevel, String loggingFormat, List<Consumer<LogMessage>> listeners) {
		if (LOGGERS.stream().anyMatch(logger -> logger.getName().equals(name))) {
			return LOGGERS.stream().filter(loggerP -> loggerP.getName().equals(name)).findFirst().get();
		}

		Logger logger = new Logger(name, LOGGERS_LISTENERS, minLevel, loggingFormat, listeners);
		LOGGERS.add(logger);
		return logger;
	}

	public static void clearLoggers() {
		LOGGERS.clear();
	}

	public static void removeLogger(Class<?> source) {
		LOGGERS.removeIf(logger -> logger.getSource().equals(source));
	}

	public static void removeLogger(String name) {
		LOGGERS.removeIf(logger -> logger.getName().equals(name));
	}

	public static void removeLogger(Logger logger) {
		LOGGERS.remove(logger);
	}
}