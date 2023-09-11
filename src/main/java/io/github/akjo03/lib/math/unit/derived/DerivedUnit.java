package io.github.akjo03.lib.math.unit.derived;

import io.github.akjo03.lib.lang.Language;
import io.github.akjo03.lib.math.unit.Unit;
import io.github.akjo03.lib.math.unit.derived.dimension.UnitDimension;
import io.github.akjo03.lib.math.unit.derived.dimension.UnitDimensionContainer;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public interface DerivedUnit<T extends DerivedUnit<T>> extends Unit<T> {
	UnitDimension getDimension();

	@Override
	default @NotNull BigDecimal getConversionFactor(@NotNull T unit) {
		return this.getDimension().calculate().divide(unit.getDimension().calculate(), 20, RoundingMode.HALF_UP).stripTrailingZeros();
	}

	default @NotNull Map<Language, String> generateLocalizedAbbreviations() {
		List<Language> languages = new ArrayList<>();
		for (UnitDimensionContainer container : getDimension()) {
			if (container.getValue() == null && container.getUnit() != null) {
				container.getUnit().getLocalizedAbbreviations().keySet().stream().filter(locale -> !languages.contains(locale)).forEach(languages::add);
			}
		}
		Map<Language, String> abbreviations = new HashMap<>();
		for (Language language : languages) {
			abbreviations.put(language, getDimension().toStringLocalized(language));
		}
		return abbreviations;
	}
}