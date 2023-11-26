package io.github.akjo03.lib.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@SuppressWarnings({"unused", "rawtypes"})
public class JsonClassSerializer extends StdSerializer<Class> {
	protected JsonClassSerializer() {
		super(Class.class);
	}

	@Override
	public void serialize(@NotNull Class value, @NotNull JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeString(value.getName());
	}
}