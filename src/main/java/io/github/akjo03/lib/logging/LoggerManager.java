package io.github.akjo03.lib.logging;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class LoggerManager {
	private static final ArrayList<Logger> LOGGERS = new ArrayList<>();

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	public static @NotNull Logger getLogger(Class<?> element) {
		if (LOGGERS.stream().anyMatch(logger -> logger.getSource().equals(element))) {
			return LOGGERS.stream().filter(logger -> logger.getSource().equals(element)).findFirst().get();
		}

		Logger logger = new Logger(element);
		LOGGERS.add(logger);
		return logger;
	}

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	public static @NotNull Logger getLogger(String name) {
		if (LOGGERS.stream().anyMatch(logger -> logger.getName().equals(name))) {
			return LOGGERS.stream().filter(logger -> logger.getName().equals(name)).findFirst().get();
		}

		Logger logger = new Logger(name);
		LOGGERS.add(logger);
		return logger;
	}

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	public static @NotNull Logger getLogger(Class<?> source, LoggingLevel minLevel) {
		if (LOGGERS.stream().anyMatch(logger -> logger.getSource().equals(source))) {
			return LOGGERS.stream().filter(loggerP -> loggerP.getSource().equals(source)).findFirst().get()
					.setMinimumLoggingLevel(minLevel);
		}

		Logger logger = new Logger(source)
				.setMinimumLoggingLevel(minLevel);
		LOGGERS.add(logger);
		return logger;
	}

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	public static @NotNull Logger getLogger(String name, LoggingLevel minLevel) {
		if (LOGGERS.stream().anyMatch(logger -> logger.getName().equals(name))) {
			return LOGGERS.stream().filter(loggerP -> loggerP.getName().equals(name)).findFirst().get()
					.setMinimumLoggingLevel(minLevel);
		}

		Logger logger = new Logger(name)
				.setMinimumLoggingLevel(minLevel);
		LOGGERS.add(logger);
		return logger;
	}

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	public static @NotNull Logger getLogger(Class<?> source, String loggingFormat) {
		if (LOGGERS.stream().anyMatch(logger -> logger.getSource().equals(source))) {
			return LOGGERS.stream().filter(loggerP -> loggerP.getSource().equals(source)).findFirst().get()
					.setLoggingFormat(loggingFormat);
		}

		Logger logger = new Logger(source)
				.setLoggingFormat(loggingFormat);
		LOGGERS.add(logger);
		return logger;
	}

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	public static @NotNull Logger getLogger(String name, String loggingFormat) {
		if (LOGGERS.stream().anyMatch(logger -> logger.getName().equals(name))) {
			return LOGGERS.stream().filter(loggerP -> loggerP.getName().equals(name)).findFirst().get()
					.setLoggingFormat(loggingFormat);
		}

		Logger logger = new Logger(name)
				.setLoggingFormat(loggingFormat);
		LOGGERS.add(logger);
		return logger;
	}

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	public static @NotNull Logger getLogger(Class<?> source, LoggingLevel minLevel, String loggingFormat) {
		if (LOGGERS.stream().anyMatch(logger -> logger.getSource().equals(source))) {
			return LOGGERS.stream().filter(loggerP -> loggerP.getSource().equals(source)).findFirst().get()
					.setMinimumLoggingLevel(minLevel)
					.setLoggingFormat(loggingFormat);
		}

		Logger logger = new Logger(source)
				.setMinimumLoggingLevel(minLevel)
				.setLoggingFormat(loggingFormat);
		LOGGERS.add(logger);
		return logger;
	}

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	public static @NotNull Logger getLogger(String name, LoggingLevel minLevel, String loggingFormat) {
		if (LOGGERS.stream().anyMatch(logger -> logger.getName().equals(name))) {
			return LOGGERS.stream().filter(loggerP -> loggerP.getName().equals(name)).findFirst().get()
					.setMinimumLoggingLevel(minLevel)
					.setLoggingFormat(loggingFormat);
		}

		Logger logger = new Logger(name)
				.setMinimumLoggingLevel(minLevel)
				.setLoggingFormat(loggingFormat);
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