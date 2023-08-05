package io.github.akjo03.lib.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class JsonPrettyPrinterTest {
	@Mock
	private JsonGenerator jsonGenerator;

	private JsonPrettyPrinter jsonPrettyPrinter;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		jsonPrettyPrinter = new JsonPrettyPrinter();
	}

	@Test
	void createInstance() {
		PrettyPrinter instance = jsonPrettyPrinter.createInstance();
		assertEquals(JsonPrettyPrinter.class, instance.getClass());
	}

	@Test
	void createInstance_shouldThrowException() {
		JsonPrettyPrinter customPrettyPrinter = new JsonPrettyPrinter(jsonPrettyPrinter) {};
		assertThrows(IllegalStateException.class, customPrettyPrinter::createInstance);
	}

	@Test
	public void writeObjectFieldValueSeparator() throws IOException {
		jsonPrettyPrinter.writeObjectFieldValueSeparator(jsonGenerator);
		verify(jsonGenerator, times(1)).writeRaw(": ");
	}

	@Test
	public void writeEndArray() throws IOException {
		jsonPrettyPrinter.writeEndArray(jsonGenerator, 0);
		verify(jsonGenerator, times(1)).writeRaw(']');
	}

	@Test
	public void writeEndObject() throws IOException {
		jsonPrettyPrinter.writeEndObject(jsonGenerator, 0);
		verify(jsonGenerator, times(1)).writeRaw('}');
	}
}