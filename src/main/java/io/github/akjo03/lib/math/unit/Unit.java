package io.github.akjo03.lib.math.unit;

import io.github.akjo03.lib.array.StringArr2;
import io.github.akjo03.lib.lang.Language;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("unused")
public interface Unit<T extends Unit<T>> {
	@NotNull String getId();

	@NotNull BigDecimal getConversionFactor(@NotNull T unit);

	@NotNull Map<Language, StringArr2> getLocalizedNames();

	@NotNull StringArr2 getDefaultName();

	@NotNull Map<Language, String> getLocalizedAbbreviations();

	@NotNull String getDefaultAbbreviation();

	default @NotNull StringArr2 getLocalizedName(Language language) {
		StringArr2 name = getLocalizedNames().get(language);
		if (name == null) {
			name = Objects.requireNonNull(
					getLocalizedNames()
							.entrySet()
							.stream()
							.filter(nameP -> nameP.getKey().getName().startsWith(language.getLocale().getLanguage()))
							.findFirst()
							.orElseThrow(() -> new IllegalArgumentException("No name for language \"" + language.getName() + "\""))
							.getValue(),
					"Name for language \"" + language.getName() + "\" is null"
			);
		}
		return name;
	}

	default @NotNull String getLocalizedAbbreviation(Language language) {
		String abbreviation = getLocalizedAbbreviations().get(language);
		if (abbreviation == null) {
			abbreviation = Objects.requireNonNull(
					getLocalizedAbbreviations()
							.entrySet()
							.stream()
							.filter(abbreviationP -> abbreviationP.getKey().getName().startsWith(language.getLocale().getLanguage()))
							.findFirst()
							.orElseThrow(() -> new IllegalArgumentException("No abbreviation for language \"" + language.getName() + "\""))
							.getValue(),
					"Abbreviation for language \"" + language + "\" is null"
			);
		}
		return abbreviation;
	}

	@NotNull UnitSystem getUnitSystem();
}