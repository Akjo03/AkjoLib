package io.github.akjo03.lib.math.unit.base;

import io.github.akjo03.lib.math.unit.Unit;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SuppressWarnings("unused")
public interface BaseUnit<T extends BaseUnit<T>> extends Unit<T> {
	@Override
	default @NotNull BigDecimal getConversionFactor(@NotNull T unit) {
		return this.getDivisor().divide(unit.getDivisor(), 20, RoundingMode.HALF_UP).stripTrailingZeros();
	}

	BigDecimal getDivisor();

	T getBaseUnit();
}