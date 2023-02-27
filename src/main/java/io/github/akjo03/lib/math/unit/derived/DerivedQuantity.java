package io.github.akjo03.lib.math.unit.derived;

import io.github.akjo03.lib.math.unit.Quantity;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SuppressWarnings("unused")
public abstract class DerivedQuantity<T extends DerivedUnit<T>> extends Quantity<T> implements Comparable<Quantity<T>> {
	protected DerivedQuantity(BigDecimal value, @NotNull T unit) {
		super(value, unit);
	}

	protected DerivedQuantity(double value, @NotNull T unit) {
		super(value, unit);
	}

	protected DerivedQuantity(float value, @NotNull T unit) {
		super(value, unit);
	}

	protected DerivedQuantity(long value, @NotNull T unit) {
		super(value, unit);
	}

	protected DerivedQuantity(int value, @NotNull T unit) {
		super(value, unit);
	}

	@Override
	public DerivedQuantity<T> convertTo(T unit) {
		return (DerivedQuantity<T>) super.convertTo(unit);
	}

	@Override
	public int compareTo(@NotNull Quantity<T> other) {
		return compareToScaled((DerivedQuantity<T>) other, 20);
	}

	protected int compareToScaled(@NotNull DerivedQuantity<T> other, int scale) {
		return super.compareToScaled(other, scale);
	}

	protected DerivedQuantity<T> add(@NotNull DerivedQuantity<T> quantity) {
		return (DerivedQuantity<T>) super.add(quantity);
	}

	protected DerivedQuantity<T> add(@NotNull BigDecimal value) {
		return (DerivedQuantity<T>) super.add(value);
	}

	protected DerivedQuantity<T> subtract(@NotNull DerivedQuantity<T> quantity) {
		return (DerivedQuantity<T>) super.subtract(quantity);
	}

	protected DerivedQuantity<T> subtract(@NotNull BigDecimal value) {
		return (DerivedQuantity<T>) super.subtract(value);
	}

	protected DerivedQuantity<T> multiply(@NotNull DerivedQuantity<T> quantity) {
		return (DerivedQuantity<T>) super.multiply(quantity);
	}

	protected DerivedQuantity<T> multiply(@NotNull BigDecimal value) {
		return (DerivedQuantity<T>) super.multiply(value);
	}

	protected DerivedQuantity<T> divide(@NotNull DerivedQuantity<T> quantity) {
		return (DerivedQuantity<T>) super.divide(quantity);
	}

	protected DerivedQuantity<T> divide(@NotNull BigDecimal value) {
		return (DerivedQuantity<T>) super.divide(value);
	}

	protected DerivedQuantity<T> negate() {
		return (DerivedQuantity<T>) super.negate();
	}

	protected DerivedQuantity<T> abs() {
		return (DerivedQuantity<T>) super.abs();
	}

	protected DerivedQuantity<T> pow(int n) {
		return (DerivedQuantity<T>) super.pow(n);
	}

	protected DerivedQuantity<T> sqrt() {
		return (DerivedQuantity<T>) super.sqrt();
	}

	protected DerivedQuantity<T> round(int scale) {
		return (DerivedQuantity<T>) super.round(scale);
	}

	protected DerivedQuantity<T> round(int scale, RoundingMode roundingMode) {
		return (DerivedQuantity<T>) super.round(scale, roundingMode);
	}
}