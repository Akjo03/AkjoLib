package io.github.akjo03.lib.math.unit;

import io.github.akjo03.lib.array.StringArr2;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("unused")
public interface Unit<T extends Unit<T>> {
	@NotNull String getID();

	@NotNull BigDecimal getConversionFactor(@NotNull T unit);

	@NotNull Map<Locale, StringArr2> getLocalizedNames();

	@NotNull StringArr2 getDefaultName();

	@NotNull Map<Locale, String> getLocalizedAbbreviations();

	@NotNull String getDefaultAbbreviation();

	default @NotNull StringArr2 getLocalizedName(Locale locale) {
		StringArr2 name = getLocalizedNames().get(locale);
		if (name == null) {
			name = Objects.requireNonNull(
					getLocalizedNames()
							.entrySet()
							.stream()
							.filter(nameP -> nameP.getKey().getLanguage().startsWith(locale.getLanguage()))
							.findFirst()
							.orElseThrow(() -> new IllegalArgumentException("No name for locale " + locale))
							.getValue(),
					"Name for locale " + locale + " is null"
			);
		}
		return name;
	}

	default @NotNull String getLocalizedAbbreviation(Locale locale) {
		String abbreviation = getLocalizedAbbreviations().get(locale);
		if (abbreviation == null) {
			abbreviation = Objects.requireNonNull(
					getLocalizedAbbreviations()
							.entrySet()
							.stream()
							.filter(abbreviationP -> abbreviationP.getKey().getLanguage().startsWith(locale.getLanguage()))
							.findFirst()
							.orElseThrow(() -> new IllegalArgumentException("No abbreviation for locale " + locale))
							.getValue(),
					"Abbreviation for locale " + locale + " is null"
			);
		}
		return abbreviation;
	}

	@NotNull UnitSystem getUnitSystem();
}