package io.github.akjo03.lib.math;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

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
}