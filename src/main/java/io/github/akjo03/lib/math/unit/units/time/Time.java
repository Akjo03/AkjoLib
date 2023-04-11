package io.github.akjo03.lib.math.unit.units.time;

import io.github.akjo03.lib.math.unit.Quantity;
import io.github.akjo03.lib.math.unit.base.BaseQuantity;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SuppressWarnings("unused")
public class Time extends BaseQuantity<TimeUnit> implements Comparable<Quantity<TimeUnit>>{
	public Time(BigDecimal value, @NotNull TimeUnit unit) {
		super(value, unit);
	}

	public Time(double value, @NotNull TimeUnit unit) {
		super(value, unit);
	}

	public Time(float value, @NotNull TimeUnit unit) {
		super(value, unit);
	}

	public Time(long value, @NotNull TimeUnit unit) {
		super(value, unit);
	}

	public Time(int value, @NotNull TimeUnit unit) {
		super(value, unit);
	}

	@Override
	public Time convertTo(TimeUnit unit) {
		return (Time) super.convertTo(unit);
	}

	@Override
	public int compareTo(@NotNull Quantity<TimeUnit> other) {
		return compareToScaled((Time) other, 20);
	}

	public int compareToScaled(Time other, int scale) {
		return super.compareToScaled(other, scale);
	}

	public Time add(@NotNull Time quantity) {
		return (Time) super.add(quantity);
	}

	public Time add(@NotNull BigDecimal value) {
		return (Time) super.add(value);
	}

	public Time subtract(@NotNull Time quantity) {
		return (Time) super.subtract(quantity);
	}

	public Time subtract(@NotNull BigDecimal value) {
		return (Time) super.subtract(value);
	}

	public Time multiply(@NotNull Time quantity) {
		return (Time) super.multiply(quantity);
	}

	public Time multiply(@NotNull BigDecimal value) {
		return (Time) super.multiply(value);
	}

	public Time divide(@NotNull Time quantity) {
		return (Time) super.divide(quantity);
	}

	public Time divide(@NotNull BigDecimal value) {
		return (Time) super.divide(value);
	}

	public Time negate() {
		return (Time) super.negate();
	}

	public Time abs() {
		return (Time) super.abs();
	}

	public Time pow(int n) {
		return (Time) super.pow(n);
	}

	public Time sqrt() {
		return (Time) super.sqrt();
	}

	public Time round(int scale) {
		return (Time) super.round(scale);
	}

	public Time round(int scale, RoundingMode roundingMode) {
		return (Time) super.round(scale, roundingMode);
	}

	@Override
	public String toObjectString() {
		return "Time{" + "value=" + getValue() + ", unit=" + getUnit() + '}';
	}
}