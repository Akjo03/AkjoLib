package io.github.akjo03.lib.math.unit.derived.dimension;

import io.github.akjo03.lib.math.unit.Unit;
import io.github.akjo03.lib.math.unit.base.BaseUnit;
import io.github.akjo03.lib.math.unit.derived.DerivedUnit;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;

@SuppressWarnings("unused")
public class UnitDimension implements Iterable<UnitDimensionContainer> {
	@Getter
	@NotNull
	private final UnitDimensionContainer root;

	@NotNull private final LinkedList<UnitDimensionContainer> containers;

	public UnitDimension(@NotNull Unit<?> unit) {
		this.containers = new LinkedList<>();
		UnitDimensionContainer rootContainer = new UnitDimensionContainer(unit);
		this.containers.add(rootContainer);
		this.root = rootContainer;
	}

	public UnitDimension(@NotNull BigDecimal value) {
		this.containers = new LinkedList<>();
		UnitDimensionContainer rootContainer = new UnitDimensionContainer(value);
		this.containers.add(rootContainer);
		this.root = rootContainer;
	}

	public UnitDimension add(@NotNull Unit<?> unit) {
		containers.getFirst().setOperator(UnitDimensionOperator.ADD);
		containers.add(new UnitDimensionContainer(unit));
		return this;
	}

	public UnitDimension add(@NotNull BigDecimal value) {
		containers.getFirst().setOperator(UnitDimensionOperator.ADD);
		containers.add(new UnitDimensionContainer(value));
		return this;
	}

	public UnitDimension subtract(@NotNull Unit<?> unit) {
		containers.getFirst().setOperator(UnitDimensionOperator.SUBTRACT);
		containers.add(new UnitDimensionContainer(unit));
		return this;
	}

	public UnitDimension subtract(@NotNull BigDecimal value) {
		containers.getFirst().setOperator(UnitDimensionOperator.SUBTRACT);
		containers.add(new UnitDimensionContainer(value));
		return this;
	}

	public UnitDimension multiply(@NotNull Unit<?> unit) {
		containers.getFirst().setOperator(UnitDimensionOperator.MULTIPLY);
		containers.add(new UnitDimensionContainer(unit));
		return this;
	}

	public UnitDimension multiply(@NotNull BigDecimal value) {
		containers.getFirst().setOperator(UnitDimensionOperator.MULTIPLY);
		containers.add(new UnitDimensionContainer(value));
		return this;
	}

	public UnitDimension divide(@NotNull Unit<?> unit) {
		containers.getFirst().setOperator(UnitDimensionOperator.DIVIDE);
		containers.add(new UnitDimensionContainer(unit));
		return this;
	}

	public UnitDimension divide(@NotNull BigDecimal value) {
		containers.getFirst().setOperator(UnitDimensionOperator.DIVIDE);
		containers.add(new UnitDimensionContainer(value));
		return this;
	}

	public UnitDimension power(@NotNull Unit<?> unit) {
		containers.getFirst().setOperator(UnitDimensionOperator.POWER);
		containers.add(new UnitDimensionContainer(unit));
		return this;
	}

	public UnitDimension power(int value) {
		containers.getFirst().setOperator(UnitDimensionOperator.POWER);
		containers.add(new UnitDimensionContainer(BigDecimal.valueOf(value)));
		return this;
	}

	public UnitDimensionContainer getNext(@NotNull UnitDimensionContainer container) {
		try {
			return containers.get(containers.indexOf(container) + 1);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public BigDecimal calculate() {
		BigDecimal result = null;

		for (UnitDimensionContainer container : containers) {
			if (container.getOperator() != null) {
				UnitDimensionContainer nextContainer = getNext(container);
				if (nextContainer == null) {
					throw new IllegalStateException("UnitDimensionContainer is invalid!");
				}

				BigDecimal thisContainerValue = calculateValue(container);
				BigDecimal nextContainerValue = calculateValue(nextContainer);
				UnitDimensionOperator operator = container.getOperator();

				result = switch (operator) {
					case ADD -> thisContainerValue.add(nextContainerValue);
					case SUBTRACT -> thisContainerValue.subtract(nextContainerValue);
					case MULTIPLY -> thisContainerValue.multiply(nextContainerValue);
					case DIVIDE -> thisContainerValue.divide(nextContainerValue, 20, RoundingMode.HALF_UP);
					case POWER -> thisContainerValue.pow(nextContainerValue.intValue());
				};
			}
		}

		return result;
	}

	private BigDecimal calculateValue(@NotNull UnitDimensionContainer container) {
		BigDecimal result;
		if (container.getUnit() != null) {
			if (container.getUnit() instanceof BaseUnit<?> baseUnit) {
				result = baseUnit.getDivisor();
			} else if (container.getUnit() instanceof DerivedUnit<?> derivedUnit) {
				result = derivedUnit.getDimension().calculate();
			} else {
				throw new IllegalStateException("Unit is neither a BaseUnit nor a DerivedUnit!");
			}
		} else if (container.getValue() != null) {
			result = container.getValue();
		} else {
			throw new IllegalStateException("UnitDimensionContainer is invalid!");
		}
		return result;
	}

	@Override
	public @NotNull Iterator<UnitDimensionContainer> iterator() {
		return containers.iterator();
	}

	@Override
	public String toString() {
		return this.toStringLocalized(Locale.getDefault());
	}

	public String toStringLocalized(Locale locale) {
		StringBuilder sb = new StringBuilder();
		for (UnitDimensionContainer container : containers) {
			sb.append(container.toStringLocalized(locale));
		}
		return sb.toString();
	}
}