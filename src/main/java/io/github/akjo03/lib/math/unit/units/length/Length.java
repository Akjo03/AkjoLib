package io.github.akjo03.lib.math.unit.units.length;

import io.github.akjo03.lib.math.unit.Quantity;
import io.github.akjo03.lib.math.unit.base.BaseQuantity;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SuppressWarnings("unused")
public class Length extends BaseQuantity<LengthUnit> implements Comparable<Quantity<LengthUnit>> {
	public Length(BigDecimal value, @NotNull LengthUnit unit) {
		super(value, unit);
	}

	public Length(double value, @NotNull LengthUnit unit) {
		super(value, unit);
	}

	public Length(float value, @NotNull LengthUnit unit) {
		super(value, unit);
	}

	public Length(long value, @NotNull LengthUnit unit) {
		super(value, unit);
	}

	public Length(int value, @NotNull LengthUnit unit) {
		super(value, unit);
	}

	@Override
	public Length convertTo(LengthUnit unit) {
		return (Length) super.convertTo(unit);
	}

	@Override
	public int compareTo(@NotNull Quantity<LengthUnit> other) {
		return compareToScaled((Length) other, 20);
	}

	public int compareToScaled(Length other, int scale) {
		return super.compareToScaled(other, scale);
	}

	public Length add(@NotNull Length quantity) {
		return (Length) super.add(quantity);
	}

	public Length add(@NotNull BigDecimal value) {
		return (Length) super.add(value);
	}

	public Length subtract(@NotNull Length quantity) {
		return (Length) super.subtract(quantity);
	}

	public Length subtract(@NotNull BigDecimal value) {
		return (Length) super.subtract(value);
	}

	public Length multiply(@NotNull Length quantity) {
		return (Length) super.multiply(quantity);
	}

	public Length multiply(@NotNull BigDecimal value) {
		return (Length) super.multiply(value);
	}

	public Length divide(@NotNull Length quantity) {
		return (Length) super.divide(quantity);
	}

	public Length divide(@NotNull BigDecimal value) {
		return (Length) super.divide(value);
	}

	public Length negate() {
		return (Length) super.negate();
	}

	public Length abs() {
		return (Length) super.abs();
	}

	public Length pow(int n) {
		return (Length) super.pow(n);
	}

	public Length sqrt() {
		return (Length) super.sqrt();
	}

	public Length round(int scale) {
		return (Length) super.round(scale);
	}

	public Length round(int scale, RoundingMode roundingMode) {
		return (Length) super.round(scale, roundingMode);
	}

	@Override
	public String toObjectString() {
		return "Length{" + "value=" + getValue() + ", unit=" + getUnit() + '}';
	}
}