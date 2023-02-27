package io.github.akjo03.lib.math.unit.derived;

import io.github.akjo03.lib.math.unit.QuantityRange;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public abstract class DerivedQuantityRange<T extends DerivedUnit<T>> extends QuantityRange<T> {
	protected DerivedQuantityRange(@NotNull DerivedQuantity<T> min, @NotNull DerivedQuantity<T> max) {
		super(min, max);
	}

	@Override
	public @NotNull DerivedQuantity<T> getMin() {
		return (DerivedQuantity<T>) super.getMin();
	}

	@Override
	public @NotNull DerivedQuantity<T> getMax() {
		return (DerivedQuantity<T>) super.getMax();
	}
}