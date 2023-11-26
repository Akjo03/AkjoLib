package io.github.akjo03.lib.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.github.akjo03.lib.result.Result;

import java.io.IOException;

@SuppressWarnings("unused")
public abstract class CustomSerializer<T> extends StdSerializer<T> {
    protected CustomSerializer(Class<T> type) {
        super(type);
    }

    @Override
    public void serialize(T obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Result<T> result = validate(obj);

        if (result.isError()) {
            throw new IOException(result.getError().getMessage());
        }

        Result<T> jsonResult = toJson(obj);

        if (jsonResult.isError()) {
            throw new IOException(jsonResult.getError().getMessage());
        }

        jsonGenerator.writeObject(jsonResult.get());
    }

    public abstract Result<T> toJson(T t);

    public abstract Result<T> validate(T t);
}