package io.github.akjo03.lib.math.unit.units.length;

import io.github.akjo03.lib.math.unit.base.BaseQuantityRange;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class LengthRange extends BaseQuantityRange<LengthUnit> {
	public LengthRange(@NotNull Length min, @NotNull Length max) {
		super(min, max);
	}

	@Override
	public @NotNull Length getMin() {
		return (Length) super.getMin();
	}

	@Override
	public @NotNull Length getMax() {
		return (Length) super.getMax();
	}
}