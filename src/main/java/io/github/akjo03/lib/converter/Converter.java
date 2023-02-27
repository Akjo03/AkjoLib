package io.github.akjo03.lib.converter;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

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

	public U convertForward(T input) {
		return forward.apply(input);
	}

	public T convertBackward(U input) {
		return backward.apply(input);
	}

	public List<U> convertForward(@NotNull List<T> input) {
		return input.stream().map(this::convertForward).toList();
	}

	public List<T> convertBackward(@NotNull List<U> input) {
		return input.stream().map(this::convertBackward).toList();
	}
}