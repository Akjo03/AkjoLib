package io.github.akjo03.lib.math.unit.units.speed;

import io.github.akjo03.lib.math.unit.Quantity;
import io.github.akjo03.lib.math.unit.derived.DerivedQuantity;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SuppressWarnings("unused")
public class Speed extends DerivedQuantity<SpeedUnit> implements Comparable<Quantity<SpeedUnit>> {
	public Speed(BigDecimal value, @NotNull SpeedUnit unit) {
		super(value, unit);
	}

	public Speed(double value, @NotNull SpeedUnit unit) {
		super(value, unit);
	}

	public Speed(float value, @NotNull SpeedUnit unit) {
		super(value, unit);
	}

	public Speed(long value, @NotNull SpeedUnit unit) {
		super(value, unit);
	}

	public Speed(int value, @NotNull SpeedUnit unit) {
		super(value, unit);
	}

	@Override
	public Speed convertTo(SpeedUnit unit) {
		return (Speed) super.convertTo(unit);
	}

	@Override
	public int compareTo(@NotNull Quantity<SpeedUnit> other) {
		return compareToScaled((Speed) other, 20);
	}

	public int compareToScaled(@NotNull Speed other, int scale) {
		return super.compareToScaled(other, scale);
	}

	public Speed add(@NotNull Speed quantity) {
		return (Speed) super.add(quantity);
	}

	public Speed add(@NotNull BigDecimal value) {
		return (Speed) super.add(value);
	}

	public Speed subtract(@NotNull Speed quantity) {
		return (Speed) super.subtract(quantity);
	}

	public Speed subtract(@NotNull BigDecimal value) {
		return (Speed) super.subtract(value);
	}

	public Speed multiply(@NotNull Speed quantity) {
		return (Speed) super.multiply(quantity);
	}

	public Speed multiply(@NotNull BigDecimal value) {
		return (Speed) super.multiply(value);
	}

	public Speed divide(@NotNull Speed quantity) {
		return (Speed) super.divide(quantity);
	}

	public Speed divide(@NotNull BigDecimal value) {
		return (Speed) super.divide(value);
	}

	public Speed negate() {
		return (Speed) super.negate();
	}

	public Speed abs() {
		return (Speed) super.abs();
	}

	public Speed pow(int n) {
		return (Speed) super.pow(n);
	}

	public Speed sqrt() {
		return (Speed) super.sqrt();
	}

	public Speed round(int scale) {
		return (Speed) super.round(scale);
	}

	public Speed round(int scale, RoundingMode roundingMode) {
		return (Speed) super.round(scale, roundingMode);
	}

	@Override
	public String toObjectString() {
		return "Speed{" + "value=" + getValue() + ", unit=" + getUnit() + '}';
	}
}