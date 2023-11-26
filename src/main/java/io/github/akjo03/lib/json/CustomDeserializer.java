package io.github.akjo03.lib.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import io.github.akjo03.lib.result.Result;
import io.github.akjo03.lib.result.ResultAggregator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public abstract class CustomDeserializer<T> extends StdDeserializer<T> {
    protected CustomDeserializer(Class<T> type) {
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

    protected static <T> @NotNull List<T> deserializeList(@NotNull JsonNode node, @NotNull JsonParser parser, @NotNull Class<T> type) throws JsonProcessingException {
        List<T> list = new ArrayList<>();
        for (JsonNode childNode : node) {
            list.add(parser.getCodec().treeToValue(childNode, type));
        }
        return list;
    }
}