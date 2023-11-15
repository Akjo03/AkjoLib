package io.github.akjo03.lib.constructor;

import io.github.akjo03.lib.result.Result;
import io.github.akjo03.lib.result.ResultAggregator;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public interface Constructor<T extends Constructable, C extends Constructor<T, C>> {
    default @NotNull Result<T> construct() {
        ResultAggregator aggregator = new ResultAggregator();
        Result<T> result = build();
        aggregator.add(result);

        if (result.isError()) {
            return aggregator.aggregateButResult(result);
        }

        T constructable = result.get();

        if (result.isSuccess()) {
            aggregator.add(validate(constructable));
        }

        return aggregator.aggregateBut(constructable);
    }

    @NotNull Result<T> build();
    @NotNull Result<T> validate(@NotNull T constructable);
}