package io.github.akjo03.lib.math.unit.units.speed;

import io.github.akjo03.lib.math.unit.derived.DerivedQuantityRange;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class SpeedRange extends DerivedQuantityRange<SpeedUnit> {
	public SpeedRange(@NotNull Speed min, @NotNull Speed max) {
		super(min, max);
	}

	@Override
	public @NotNull Speed getMin() {
		return (Speed) super.getMin();
	}

	@Override
	public @NotNull Speed getMax() {
		return (Speed) super.getMax();
	}
}