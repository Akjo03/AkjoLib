package io.github.akjo03.lib.logging;

import io.github.akjo03.lib.console.ConsoleColor;
import lombok.Getter;

@Getter
public enum LoggingLevel {
	TRACE("TRACE", -200, ConsoleColor.WHITE, false),
	DEBUG("DEBUG", -100),
	INFO("INFO", 0),
	SUCCESS("SUCCESS", 10, ConsoleColor.GREEN, false),
	WARN("WARN", 100, ConsoleColor.YELLOW, false),
	ERROR("ERROR", 200, ConsoleColor.RED, false),
	FATAL("FATAL", 300, ConsoleColor.RED, true);

	private final String name;
	private final int level;
	private final ConsoleColor color;
	private final boolean bold;

	LoggingLevel(String name, int level) {
		this.name = name;
		this.level = level;
		this.color = null;
		this.bold = false;
	}

	LoggingLevel(String name, int level, ConsoleColor color, boolean bold) {
		this.name = name;
		this.level = level;
		this.color = color;
		this.bold = bold;
	}
}