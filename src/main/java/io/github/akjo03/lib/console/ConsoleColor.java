package io.github.akjo03.lib.console;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public enum ConsoleColor {
	BLACK("\033[%c;30m"),
	RED("\033[%c;31m"),
	GREEN("\033[%c;32m"),
	YELLOW("\033[%c;33m"),
	BLUE("\033[%c;34m"),
	PURPLE("\033[%c;35m"),
	CYAN("\033[%c;36m"),
	WHITE("\033[%c;37m"),
	BLACK_BRIGHT("\033[%c;90m"),
	RED_BRIGHT("\033[%c;91m"),
	GREEN_BRIGHT("\033[%c;92m"),
	YELLOW_BRIGHT("\033[%c;93m"),
	BLUE_BRIGHT("\033[%c;94m"),
	PURPLE_BRIGHT("\033[%c;95m"),
	CYAN_BRIGHT("\033[%c;96m"),
	WHITE_BRIGHT("\033[%c;97m");

	private static final String RESET = "\033[0m";

	private final String code;

	ConsoleColor(String code) {
		this.code = code;
	}

	public @NotNull String getCode(boolean bold) {
		return code.replace("%c", bold ? "1" : "0");
	}

	public @NotNull String colorize(String text, boolean bold) {
		return getCode(bold) + text + RESET;
	}
}