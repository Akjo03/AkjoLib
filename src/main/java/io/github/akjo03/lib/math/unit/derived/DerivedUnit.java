package io.github.akjo03.lib.math.unit.derived;

import io.github.akjo03.lib.math.unit.Unit;
import io.github.akjo03.lib.math.unit.derived.dimension.UnitDimension;
import io.github.akjo03.lib.math.unit.derived.dimension.UnitDimensionContainer;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@SuppressWarnings("unused")
public interface DerivedUnit<T extends DerivedUnit<T>> extends Unit<T> {
	UnitDimension getDimension();

	@Override
	default @NotNull BigDecimal getConversionFactor(@NotNull T unit) {
		return this.getDimension().calculate().divide(unit.getDimension().calculate(), 20, RoundingMode.HALF_UP).stripTrailingZeros();
	}

	default @NotNull Map<Locale, String> generateLocalizedAbbreviations() {
		List<Locale> locales = new ArrayList<>();
		for (UnitDimensionContainer container : getDimension()) {
			if (container.getValue() == null && container.getUnit() != null) {
				container.getUnit().getLocalizedAbbreviations().keySet().stream().filter(locale -> !locales.contains(locale)).forEach(locales::add);
			}
		}
		Map<Locale, String> abbreviations = new HashMap<>();
		for (Locale locale : locales) {
			abbreviations.put(locale, getDimension().toStringLocalized(locale));
		}
		return abbreviations;
	}
}