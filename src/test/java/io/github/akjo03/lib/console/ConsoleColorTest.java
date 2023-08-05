package io.github.akjo03.lib.console;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleColorTest {
	@Test
	public void testGetCode() {
		assertEquals("\033[0;30m", ConsoleColor.BLACK.getCode(false));
		assertEquals("\033[1;30m", ConsoleColor.BLACK.getCode(true));
	}

	@Test
	public void testColorize() {
		String text = "Hello, World!";
		assertEquals("\033[0;30m" + text + "\033[0m", ConsoleColor.BLACK.colorize(text, false));
		assertEquals("\033[1;30m" + text + "\033[0m", ConsoleColor.BLACK.colorize(text, true));
	}
}