package io.github.akjo03.lib.math.unit.derived.dimension;

import lombok.Getter;

@SuppressWarnings("unused")
public enum UnitDimensionOperator {
	ADD("+"),
	SUBTRACT("-"),
	MULTIPLY("*"),
	DIVIDE("/"),
	POWER("^");

	@Getter
	private final String symbol;

	UnitDimensionOperator(String symbol) {
		this.symbol = symbol;
	}
}