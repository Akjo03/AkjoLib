package io.github.akjo03.lib.math.unit;

import io.github.akjo03.lib.array.StringArr2;
import io.github.akjo03.lib.lang.Language;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

@Getter
@SuppressWarnings("unused")
public abstract class Quantity<T extends Unit<T>> extends Number implements Comparable<Quantity<T>> {
	@NotNull protected transient BigDecimal value;
	@NotNull protected transient T unit;

	protected Quantity(@NotNull BigDecimal value, @NotNull T unit) {
		this.value = value;
		this.unit = unit;
	}

	protected Quantity(double value, @NotNull T unit) {
		this.value = BigDecimal.valueOf(value);
		this.unit = unit;
	}

	protected Quantity(float value, @NotNull T unit) {
		this.value = BigDecimal.valueOf(value);
		this.unit = unit;
	}

	protected Quantity(long value, @NotNull T unit) {
		this.value = BigDecimal.valueOf(value);
		this.unit = unit;
	}

	protected Quantity(int value, @NotNull T unit) {
		this.value = BigDecimal.valueOf(value);
		this.unit = unit;
	}

	@Override
	public int intValue() {
		return value.intValue();
	}

	@Override
	public long longValue() {
		return value.longValue();
	}

	@Override
	public float floatValue() {
		return value.floatValue();
	}

	@Override
	public double doubleValue() {
		return value.doubleValue();
	}

	protected Quantity<T> convertTo(T unit) {
		BigDecimal conversionFactor = this.unit.getConversionFactor(unit);
		this.value = this.value.multiply(conversionFactor);
		this.unit = unit;
		return this;
	}

	protected int compareToScaled(@NotNull Quantity<T> other, int scale) {
		BigDecimal thisScaled = this.value.setScale(scale, RoundingMode.HALF_UP);
		BigDecimal otherScaled = other.convertTo(this.unit).getValue().setScale(scale, RoundingMode.HALF_UP);
		return thisScaled.compareTo(otherScaled);
	}

	protected Quantity<T> add(@NotNull Quantity<T> quantity) {
		this.value = this.value.add(quantity.convertTo(this.unit).getValue());
		return this;
	}

	protected Quantity<T> add(@NotNull BigDecimal value) {
		this.value = this.value.add(value);
		return this;
	}

	protected Quantity<T> subtract(@NotNull Quantity<T> quantity) {
		this.value = this.value.subtract(quantity.convertTo(this.unit).getValue());
		return this;
	}

	protected Quantity<T> subtract(@NotNull BigDecimal value) {
		this.value = this.value.subtract(value);
		return this;
	}

	protected Quantity<T> multiply(@NotNull Quantity<T> quantity) {
		this.value = this.value.multiply(quantity.convertTo(this.unit).getValue());
		return this;
	}

	protected Quantity<T> multiply(@NotNull BigDecimal value) {
		this.value = this.value.multiply(value);
		return this;
	}

	protected Quantity<T> divide(@NotNull Quantity<T> quantity) {
		this.value = this.value.divide(quantity.convertTo(this.unit).getValue(), 20, RoundingMode.HALF_UP);
		return this;
	}

	protected Quantity<T> divide(@NotNull BigDecimal value) {
		this.value = this.value.divide(value, 20, RoundingMode.HALF_UP);
		return this;
	}

	protected Quantity<T> negate() {
		this.value = this.value.negate();
		return this;
	}

	protected Quantity<T> abs() {
		this.value = this.value.abs();
		return this;
	}

	protected Quantity<T> pow(int n) {
		this.value = this.value.pow(n);
		return this;
	}

	protected Quantity<T> sqrt() {
		this.value = this.value.sqrt(new MathContext(50, RoundingMode.HALF_UP));
		return this;
	}

	protected Quantity<T> round(int scale) {
		this.value = this.value.setScale(scale, RoundingMode.HALF_UP);
		return this;
	}

	protected Quantity<T> round(int scale, RoundingMode roundingMode) {
		this.value = this.value.setScale(scale, roundingMode);
		return this;
	}

	@Override
	public String toString() {
		return toStringLocalized(Language.ENGLISH);
	}

	public abstract String toObjectString();

	public String toStringLocalized(Language language) {
		StringArr2 name = this.unit.getLocalizedName(language);

		DecimalFormat df = getDecimalFormat(language);
		if (this.value.compareTo(BigDecimal.ONE) > 0) {
			return df.format(this.value) + " " + name.getSecond();
		} else {
			return df.format(this.value) + " " + name.getFirst();
		}
	}

	public String toStringLocalizedWithAbbreviation(Language language) {
		String abbreviation = this.unit.getLocalizedAbbreviation(language);

		DecimalFormat df = getDecimalFormat(language);
		return df.format(this.value) + " " + abbreviation;
	}

	private @NotNull DecimalFormat getDecimalFormat(Language language) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(20);
		DecimalFormatSymbols dfs = new DecimalFormatSymbols(language.getLocale());
		if (this.value.compareTo(BigDecimal.ONE) > 0 || this.value.compareTo(BigDecimal.ONE.negate()) < 0) {
			dfs.setExponentSeparator("e+");
		} else {
			dfs.setExponentSeparator("e");
		}
		df.setDecimalFormatSymbols(dfs);
		return df;
	}
}