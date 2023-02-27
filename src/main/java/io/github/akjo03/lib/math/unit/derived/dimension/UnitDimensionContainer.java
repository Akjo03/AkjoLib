package io.github.akjo03.lib.math.unit.derived.dimension;

import io.github.akjo03.lib.math.unit.Unit;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Locale;

@SuppressWarnings("unused")
public class UnitDimensionContainer {
	@Getter
	private final Unit<?> unit;
	@Getter
	private final BigDecimal value;

	private UnitDimensionOperator operator;

	public UnitDimensionContainer(Unit<?> unit) {
		this.value = null;
		this.unit = unit;
	}

	public UnitDimensionContainer(BigDecimal value) {
		this.value = value;
		this.unit = null;
	}

	UnitDimensionOperator getOperator() {
		return operator;
	}
	void setOperator(UnitDimensionOperator operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return toStringLocalized(Locale.getDefault());
	}

	public @NotNull String toStringLocalized(Locale locale) {
		String symbol = "";
		if (unit != null) {
			symbol = unit.getLocalizedAbbreviation(locale);
		} else if (value != null) {
			symbol = value.toString();
		}
		return symbol + (operator != null ? operator.getSymbol() : "");
	}
}