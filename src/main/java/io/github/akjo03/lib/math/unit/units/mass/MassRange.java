package io.github.akjo03.lib.math.unit.units.mass;

import io.github.akjo03.lib.math.unit.base.BaseQuantityRange;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class MassRange extends BaseQuantityRange<MassUnit> {
	public MassRange(@NotNull Mass min, @NotNull Mass max) {
		super(min, max);
	}

	@Override
	public @NotNull Mass getMin() {
		return (Mass) super.getMin();
	}

	@Override
	public @NotNull Mass getMax() {
		return (Mass) super.getMax();
	}
}