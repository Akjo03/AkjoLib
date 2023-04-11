package io.github.akjo03.lib.converter;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
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

	protected Converter(Function<T, U> forward, Function<U, T> backward) {
		this.forward = forward;
		this.backward = backward;
	}

	@Contract(value = "_, _ -> new", pure = true)
	public static <T, U> @NotNull Converter<T, U> of(Function<T, U> forward, Function<U, T> backward) {
		return new Converter<>(forward, backward) {};
	}

	/**
	 * Converts a value from the source type to the target type.
	 *
	 * @param input the value to convert
	 * @return the converted value
	 */
	public U convertForward(T input) {
		return forward.apply(input);
	}

	/**
	 * Converts a value from the target type back to the source type.
	 *
	 * @param input the value to convert
	 * @return the converted value
	 */
	public T convertBackward(U input) {
		return backward.apply(input);
	}

	/**
	 * Converts a list of values from the source type to the target type.
	 *
	 * @param input the list of values to convert
	 * @return the list of converted values
	 */
	public List<U> convertForward(@NotNull List<T> input) {
		return input.stream().map(this::convertForward).toList();
	}

	/**
	 * Converts a list of values from the target type back to the source type.
	 *
	 * @param input the list of values to convert
	 * @return the list of converted values
	 */
	public List<T> convertBackward(@NotNull List<U> input) {
		return input.stream().map(this::convertBackward).toList();
	}
}