package io.github.akjo03.lib.math.unit.units.area;

import io.github.akjo03.lib.math.unit.Quantity;
import io.github.akjo03.lib.math.unit.derived.DerivedQuantity;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SuppressWarnings("unused")
public class Area extends DerivedQuantity<AreaUnit> implements Comparable<Quantity<AreaUnit>> {
	public Area(BigDecimal value, @NotNull AreaUnit unit) {
		super(value, unit);
	}

	public Area(double value, @NotNull AreaUnit unit) {
		super(value, unit);
	}

	public Area(float value, @NotNull AreaUnit unit) {
		super(value, unit);
	}

	public Area(long value, @NotNull AreaUnit unit) {
		super(value, unit);
	}

	public Area(int value, @NotNull AreaUnit unit) {
		super(value, unit);
	}

	@Override
	public Area convertTo(AreaUnit unit) {
		return (Area) super.convertTo(unit);
	}

	@Override
	public int compareTo(@NotNull Quantity<AreaUnit> other) {
		return compareToScaled((Area) other, 20);
	}

	public int compareToScaled(@NotNull Area other, int scale) {
		return super.compareToScaled(other, scale);
	}

	public Area add(@NotNull Area quantity) {
		return (Area) super.add(quantity);
	}

	public Area add(@NotNull BigDecimal value) {
		return (Area) super.add(value);
	}

	public Area subtract(@NotNull Area quantity) {
		return (Area) super.subtract(quantity);
	}

	public Area subtract(@NotNull BigDecimal value) {
		return (Area) super.subtract(value);
	}

	public Area multiply(@NotNull Area quantity) {
		return (Area) super.multiply(quantity);
	}

	public Area multiply(@NotNull BigDecimal value) {
		return (Area) super.multiply(value);
	}

	public Area divide(@NotNull Area quantity) {
		return (Area) super.divide(quantity);
	}

	public Area divide(@NotNull BigDecimal value) {
		return (Area) super.divide(value);
	}

	public Area negate() {
		return (Area) super.negate();
	}

	public Area abs() {
		return (Area) super.abs();
	}

	public Area pow(int n) {
		return (Area) super.pow(n);
	}

	public Area sqrt() {
		return (Area) super.sqrt();
	}

	public Area round(int scale) {
		return (Area) super.round(scale);
	}

	public Area round(int scale, RoundingMode roundingMode) {
		return (Area) super.round(scale, roundingMode);
	}

	@Override
	public String toObjectString() {
		return "Area{" + "value=" + getValue() + ", unit=" + getUnit() + '}';
	}
}