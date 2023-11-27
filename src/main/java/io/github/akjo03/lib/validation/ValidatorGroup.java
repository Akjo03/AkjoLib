package io.github.akjo03.lib.validation;

import io.github.akjo03.lib.result.Result;
import io.github.akjo03.lib.result.ResultAggregator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidatorGroup<T> implements Validator<T> {
    private final List<Validator<T>> validators;

    @Contract("_ -> new")
    @SafeVarargs
    public static <T> @NotNull ValidatorGroup<T> of(Validator<T>... validators) {
        return new ValidatorGroup<>(List.of(validators));
    }

    @Override
    public Result<T> validate(T t) {
        ResultAggregator aggregator = new ResultAggregator();
        validators.stream()
                .map(validator -> validator.validate(t))
                .forEach(aggregator::add);
        return aggregator.aggregateBut(t);
    }
}