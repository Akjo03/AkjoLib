package io.github.akjo03.lib.math.unit;

import io.github.akjo03.lib.math.Range;
import org.jetbrains.annotations.NotNull;

public abstract class QuantityRange<T extends Unit<T>> extends Range<Quantity<T>> {
	protected QuantityRange(@NotNull Quantity<T> min, @NotNull Quantity<T> max) {
		super(min, max);
	}
}
