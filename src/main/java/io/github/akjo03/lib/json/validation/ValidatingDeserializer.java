package io.github.akjo03.lib.json.validation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import io.github.akjo03.lib.result.Result;
import io.github.akjo03.lib.result.ResultAggregator;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

@SuppressWarnings("unused")
public abstract class ValidatingDeserializer<T> extends StdDeserializer<T> {
    protected ValidatingDeserializer(Class<T> type) {
        super(type);
    }

    @Override
    public @Nullable T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ResultAggregator aggregator = new ResultAggregator();
        Result<T> result = fromJson(jsonParser, deserializationContext);
        aggregator.add(result);

        if (result.isError()) {
            throw MismatchedInputException.from(jsonParser, this.getValueType(), result.getError().getMessage());
        }

        T deserializedObject = result.get();

        if (result.isSuccess()) {
            aggregator.add(validate(deserializedObject));
        }

        return aggregator.aggregateBut(deserializedObject).getOrNull();
    }

    public abstract Result<T> fromJson(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException ;

    public abstract Result<T> validate(T t);
}