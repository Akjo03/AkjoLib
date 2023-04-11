package io.github.akjo03.lib.math.unit.units.mass;

import io.github.akjo03.lib.math.unit.Quantity;
import io.github.akjo03.lib.math.unit.base.BaseQuantity;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SuppressWarnings("unused")
public class Mass extends BaseQuantity<MassUnit> implements Comparable<Quantity<MassUnit>> {
	public Mass(BigDecimal value, @NotNull MassUnit unit) {
		super(value, unit);
	}

	public Mass(double value, @NotNull MassUnit unit) {
		super(value, unit);
	}

	public Mass(float value, @NotNull MassUnit unit) {
		super(value, unit);
	}

	public Mass(long value, @NotNull MassUnit unit) {
		super(value, unit);
	}

	public Mass(int value, @NotNull MassUnit unit) {
		super(value, unit);
	}

	@Override
	public Mass convertTo(MassUnit unit) {
		return (Mass) super.convertTo(unit);
	}

	@Override
	public int compareTo(@NotNull Quantity<MassUnit> other) {
		return compareToScaled((Mass) other, 20);
	}

	public int compareToScaled(Mass other, int scale) {
		return super.compareToScaled(other, scale);
	}

	public Mass add(@NotNull Mass quantity) {
		return (Mass) super.add(quantity);
	}

	public Mass add(@NotNull BigDecimal value) {
		return (Mass) super.add(value);
	}

	public Mass subtract(@NotNull Mass quantity) {
		return (Mass) super.subtract(quantity);
	}

	public Mass subtract(@NotNull BigDecimal value) {
		return (Mass) super.subtract(value);
	}

	public Mass multiply(@NotNull Mass quantity) {
		return (Mass) super.multiply(quantity);
	}

	public Mass multiply(@NotNull BigDecimal value) {
		return (Mass) super.multiply(value);
	}

	public Mass divide(@NotNull Mass quantity) {
		return (Mass) super.divide(quantity);
	}

	public Mass divide(@NotNull BigDecimal value) {
		return (Mass) super.divide(value);
	}

	public Mass negate() {
		return (Mass) super.negate();
	}

	public Mass abs() {
		return (Mass) super.abs();
	}

	public Mass pow(int n) {
		return (Mass) super.pow(n);
	}

	public Mass sqrt() {
		return (Mass) super.sqrt();
	}

	public Mass round(int scale) {
		return (Mass) super.round(scale);
	}

	public Mass round(int scale, RoundingMode roundingMode) {
		return (Mass) super.round(scale, roundingMode);
	}

	@Override
	public String toObjectString() {
		return "Mass{" + "value=" + getValue() + ", unit=" + getUnit() + '}';
	}
}