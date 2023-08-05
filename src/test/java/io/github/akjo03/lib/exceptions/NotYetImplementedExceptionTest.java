package io.github.akjo03.lib.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotYetImplementedExceptionTest {
	@Test
	void testDefaultConstructor() {
		NotYetImplementedException e = new NotYetImplementedException();
		assertEquals("This feature is not yet implemented.", e.getMessage());
	}

	@Test
	void testMessageConstructor() {
		String msg = "Custom message";
		NotYetImplementedException e = new NotYetImplementedException(msg);
		assertEquals(msg, e.getMessage());
	}
}