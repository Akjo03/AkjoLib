package io.github.akjo03.lib.math.unit.units.time;

import io.github.akjo03.lib.math.unit.base.BaseQuantityRange;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class TimeRange extends BaseQuantityRange<TimeUnit> {
	public TimeRange(@NotNull Time min, @NotNull Time max) {
		super(min, max);
	}

	@Override
	public @NotNull Time getMin() {
		return (Time) super.getMin();
	}

	@Override
	public @NotNull Time getMax() {
		return (Time) super.getMax();
	}
}