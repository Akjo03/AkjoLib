package io.github.akjo03.lib.converter;

import io.github.akjo03.lib.result.Result;
import io.github.akjo03.lib.result.ResultAggregator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * A utility class for converting between different types, providing a generic way to
 * create converters between any two types using provided conversion functions.
 *
 * @param <T> the source type
 * @param <U> the target type
 */
@SuppressWarnings("unused")
public abstract class Converter<T, U> {
	private final Function<T, U> forward;
	private final Function<U, T> backward;

	private final ResultAggregator resultAggregator;

	protected Converter(Function<T, U> forward, Function<U, T> backward, ResultAggregator resultAggregator) {
		this.forward = forward;
		this.backward = backward;
		this.resultAggregator = resultAggregator;
	}

	@Contract(value = "_, _ -> new", pure = true)
	public static <T, U> @NotNull Converter<T, U> of(Function<T, U> forward, Function<U, T> backward) {
		return new Converter<>(forward, backward, new ResultAggregator()) {};
	}

	/**
	 * Converts a value from the source type to the target type.
	 *
	 * @param input the value to convert
	 * @return the converted value
	 */
	public Result<U> convertForward(T input) {
		Result<U> result = Result.from(() -> forward.apply(input));
		resultAggregator.add(result);
		return result;
	}

	/**
	 * Converts a value from the source type to the target type.
	 *
	 * @param input the value to convert
	 * @return the converted value
	 */
	public Result<U> convertForward(@NotNull Result<T> input) {
		Result<U> result = input.flatMap(this::convertForward);
		resultAggregator.add(result);
		return result;
	}

	/**
	 * Converts a value from the source type to the target type.
	 *
	 * @param input the value to convert
	 * @return the converted value
	 */
	@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
	public Result<U> convertForward(@NotNull Optional<T> input) {
		Result<U> result = Result.fromOptional(input).flatMap(this::convertForward);
		resultAggregator.add(result);
		return result;
	}

	/**
	 * Converts a value from the target type back to the source type.
	 *
	 * @param input the value to convert
	 * @return the converted value
	 */
	public Result<T> convertBackward(U input) {
		return Result.from(() -> backward.apply(input));
	}

	/**
	 * Converts a value from the target type back to the source type.
	 *
	 * @param input the value to convert
	 * @return the converted value
	 */
	public Result<T> convertBackward(@NotNull Result<U> input) {
		return input.flatMap(this::convertBackward);
	}

	/**
	 * Converts a value from the target type back to the source type.
	 *
	 * @param input the value to convert
	 * @return the converted value
	 */
	@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
	public Result<T> convertBackward(@NotNull Optional<U> input) {
		return Result.fromOptional(input).flatMap(this::convertBackward);
	}

	/**
	 * Converts a list of values from the source type to the target type.
	 *
	 * @param input the list of values to convert
	 * @return the list of converted values
	 */
	public ResultAggregator convertForward(@NotNull List<T> input) {
		return ResultAggregator.of(input.parallelStream().map(this::convertForward).toList());
	}

	/**
	 * Converts a list of values from the target type back to the source type.
	 *
	 * @param input the list of values to convert
	 * @return the list of converted values
	 */
	public ResultAggregator convertBackward(@NotNull List<U> input) {
		return ResultAggregator.of(input.parallelStream().map(this::convertBackward).toList());
	}

	/**
	 * Chains this Converter with another Converter for sequential type conversion.
	 * Converts from T to U using this Converter, and then from U to V using the provided Converter.
	 *
	 * @param <V> the final target type
	 * @param after the Converter to apply after this Converter
	 * @return a new Converter that converts from T to V
	 */
	public <V> Converter<T, V> then(@NotNull Converter<U, V> after) {
		return new Converter<>(forward.andThen(after.forward), after.backward.andThen(backward), resultAggregator) {};
	}

	/**
	 * Chains this Converter with another Converter for sequential type conversion.
	 * Converts from V to U using the provided Converter, and then from U to T using this Converter.
	 *
	 * @param <V> the initial source type
	 * @param before the Converter to apply before this Converter
	 * @return a new Converter that converts from V to T
	 */
	public <V> Converter<V, U> compose(@NotNull Converter<V, T> before) {
		return new Converter<>(forward.compose(before.forward), before.backward.compose(backward), resultAggregator) {};
	}
}