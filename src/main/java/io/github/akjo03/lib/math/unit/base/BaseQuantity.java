package io.github.akjo03.lib.math.unit.base;

import io.github.akjo03.lib.math.unit.Quantity;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SuppressWarnings("unused")
public abstract class BaseQuantity<T extends BaseUnit<T>> extends Quantity<T> implements Comparable<Quantity<T>> {
	protected BaseQuantity(BigDecimal value, @NotNull T unit) {
		super(value, unit);
	}

	protected BaseQuantity(double value, @NotNull T unit) {
		super(value, unit);
	}

	protected BaseQuantity(float value, @NotNull T unit) {
		super(value, unit);
	}

	protected BaseQuantity(long value, @NotNull T unit) {
		super(value, unit);
	}

	protected BaseQuantity(int value, @NotNull T unit) {
		super(value, unit);
	}

	@Override
	public BaseQuantity<T> convertTo(T unit) {
		return (BaseQuantity<T>) super.convertTo(unit);
	}

	@Override
	public int compareTo(@NotNull Quantity<T> other) {
		return compareToScaled((BaseQuantity<T>) other, 20);
	}

	protected int compareToScaled(BaseQuantity<T> other, int scale) {
		return super.compareToScaled(other, scale);
	}

	protected BaseQuantity<T> add(@NotNull BaseQuantity<T> quantity) {
		return (BaseQuantity<T>) super.add(quantity);
	}

	protected BaseQuantity<T> add(@NotNull BigDecimal value) {
		return (BaseQuantity<T>) super.add(value);
	}

	protected BaseQuantity<T> subtract(@NotNull BaseQuantity<T> quantity) {
		return (BaseQuantity<T>) super.subtract(quantity);
	}

	protected BaseQuantity<T> subtract(@NotNull BigDecimal value) {
		return (BaseQuantity<T>) super.subtract(value);
	}

	protected BaseQuantity<T> multiply(@NotNull BaseQuantity<T> quantity) {
		return (BaseQuantity<T>) super.multiply(quantity);
	}

	protected BaseQuantity<T> multiply(@NotNull BigDecimal value) {
		return (BaseQuantity<T>) super.multiply(value);
	}

	protected BaseQuantity<T> divide(@NotNull BaseQuantity<T> quantity) {
		return (BaseQuantity<T>) super.divide(quantity);
	}

	protected BaseQuantity<T> divide(@NotNull BigDecimal value) {
		return (BaseQuantity<T>) super.divide(value);
	}

	protected BaseQuantity<T> negate() {
		return (BaseQuantity<T>) super.negate();
	}

	protected BaseQuantity<T> abs() {
		return (BaseQuantity<T>) super.abs();
	}

	protected BaseQuantity<T> pow(int n) {
		return (BaseQuantity<T>) super.pow(n);
	}

	protected BaseQuantity<T> sqrt() {
		return (BaseQuantity<T>) super.sqrt();
	}

	protected BaseQuantity<T> round(int scale) {
		return (BaseQuantity<T>) super.round(scale);
	}

	protected BaseQuantity<T> round(int scale, RoundingMode roundingMode) {
		return (BaseQuantity<T>) super.round(scale, roundingMode);
	}
}
