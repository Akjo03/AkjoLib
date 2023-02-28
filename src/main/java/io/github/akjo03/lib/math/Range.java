package io.github.akjo03.lib.math;

import io.github.akjo03.lib.result.Result;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@SuppressWarnings("unused")
public class Range<T extends Number & Comparable<T>> {
	@NotNull protected final T min;
	@NotNull protected final T max;

	public Range(@NotNull T min, @NotNull T max) {
		this.min = calculateMin(min, max);
		this.max = calculateMax(min, max);
	}

	public boolean contains(@NotNull T number) {
		boolean lowerCheck = number.compareTo(min) <= -1;
		boolean higherCheck = number.compareTo(max) >= 1;

		return !lowerCheck && !higherCheck;
	}

	private @NotNull T calculateMin(@NotNull T min, @NotNull T max) {
		return min.compareTo(max) <= -1 ? min : max;
	}

	private @NotNull T calculateMax(@NotNull T min, @NotNull T max) {
		return min.compareTo(max) >= 1 ? min : max;
	}

	public <C> Result<C> checkRange(@NotNull T number, @NotNull Supplier<Result<C>> below, @NotNull Supplier<Result<C>> above, Result<C> defaultValue) {
		boolean lowerCheck = number.compareTo(min) <= -1;
		boolean higherCheck = number.compareTo(max) >= 1;

		if (lowerCheck) {
			return below.get();
		} else if (higherCheck) {
			return above.get();
		}

		return defaultValue;
	}
}