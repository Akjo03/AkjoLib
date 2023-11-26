package io.github.akjo03.lib.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@SuppressWarnings({"unused", "rawtypes"})
public class JsonClassDeserializer extends StdDeserializer<Class> {
	protected JsonClassDeserializer() {
		super(Class.class);
	}

	@Override
	public Class<?> deserialize(@NotNull JsonParser parser, DeserializationContext context) throws IOException {
		try {
			return Class.forName(parser.getText());
		} catch (ClassNotFoundException e) {
			throw new IOException(e);
		}
	}
}