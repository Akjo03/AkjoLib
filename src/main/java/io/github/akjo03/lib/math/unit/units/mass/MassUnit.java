package io.github.akjo03.lib.math.unit.units.mass;

import io.github.akjo03.lib.array.StringArr2;
import io.github.akjo03.lib.math.unit.UnitSystem;
import io.github.akjo03.lib.math.unit.base.BaseUnit;
import lombok.Getter;
import org.apache.commons.lang3.LocaleUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Map;

@SuppressWarnings("unused")
public enum MassUnit implements BaseUnit<MassUnit> {
	KILOGRAM(new BigDecimal("1.000000000000"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Kilogramm", "Kilogramm")),
					Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("kilogram", "kilograms")),
					Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("kilogram", "kilograms"))
			), new StringArr2("kilogram", "kilograms"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "kg"),
					Map.entry(LocaleUtils.toLocale(Locale.US), "kg"),
					Map.entry(LocaleUtils.toLocale(Locale.UK), "kg")
			), "kg", UnitSystem.METRIC
	),
	GRAM(new BigDecimal("0.001000000000"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Gramm", "Gramm")),
					Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("gram", "grams")),
					Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("gram", "grams"))
			), new StringArr2("gram", "grams"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "g"),
					Map.entry(LocaleUtils.toLocale(Locale.US), "g"),
					Map.entry(LocaleUtils.toLocale(Locale.UK), "g")
			), "g", UnitSystem.METRIC
	),
	MILLIGRAM(new BigDecimal("0.000001000000"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Milligramm", "Milligramm")),
					Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("milligram", "milligrams")),
					Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("milligram", "milligrams"))
			), new StringArr2("milligram", "milligrams"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "mg"),
					Map.entry(LocaleUtils.toLocale(Locale.US), "mg"),
					Map.entry(LocaleUtils.toLocale(Locale.UK), "mg")
			), "mg", UnitSystem.METRIC
	),
	MICROGRAM(new BigDecimal("0.000000001000"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Mikrogramm", "Mikrogramm")),
					Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("microgram", "micrograms")),
					Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("microgram", "micrograms"))
			), new StringArr2("microgram", "micrograms"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "µg"),
					Map.entry(LocaleUtils.toLocale(Locale.US), "µg"),
					Map.entry(LocaleUtils.toLocale(Locale.UK), "µg")
			), "µg", UnitSystem.METRIC
	),
	POUND(new BigDecimal("0.453592370000"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Pfund", "Pfund")),
					Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("pound", "pounds")),
					Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("pound", "pounds"))
			), new StringArr2("pound", "pounds"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "lb"),
					Map.entry(LocaleUtils.toLocale(Locale.US), "lb"),
					Map.entry(LocaleUtils.toLocale(Locale.UK), "lb")
			), "lb", UnitSystem.IMPERIAL
	),
	OUNCE(new BigDecimal("0.028349523125"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Unze", "Unzen")),
					Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("ounce", "ounces")),
					Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("ounce", "ounces"))
			), new StringArr2("ounce", "ounces"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "oz"),
					Map.entry(LocaleUtils.toLocale(Locale.US), "oz"),
					Map.entry(LocaleUtils.toLocale(Locale.UK), "oz")
			), "oz", UnitSystem.IMPERIAL
	),
	CARAT(new BigDecimal("0.000200000000"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Karat", "Karat")),
					Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("carat", "carats")),
					Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("carat", "carats"))
			), new StringArr2("ounce", "ounces"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "kt"),
					Map.entry(LocaleUtils.toLocale(Locale.US), "ct"),
					Map.entry(LocaleUtils.toLocale(Locale.UK), "ct")
			), "ct", UnitSystem.UNDEFINED
	),
	METRIC_TON(new BigDecimal("1000.000000000000"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Metrische Tonne", "Metrische Tonnen")),
					Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("metric ton", "metric tons")),
					Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("metric ton", "metric tons"))
			), new StringArr2("metric ton", "metric tons"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "t"),
					Map.entry(LocaleUtils.toLocale(Locale.US), "t"),
					Map.entry(LocaleUtils.toLocale(Locale.UK), "t")
			), "ct", UnitSystem.METRIC
	),
	LONG_TON(new BigDecimal("1016.046908800000"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Britische Tonne", "Britische Tonnen")),
					Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("long ton", "long tons")),
					Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("long ton", "long tons"))
			), new StringArr2("long ton", "long tons"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "tn. l."),
					Map.entry(LocaleUtils.toLocale(Locale.US), "tn. l."),
					Map.entry(LocaleUtils.toLocale(Locale.UK), "tn. l.")
			), "tn. l.", UnitSystem.IMPERIAL
	),
	SHORT_TON(new BigDecimal("907.184740000000"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Amerikanische Tonne", "Amerikanische Tonnen")),
					Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("short ton", "short tons")),
					Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("short ton", "short tons"))
			), new StringArr2("short ton", "short tons"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "tn. sh."),
					Map.entry(LocaleUtils.toLocale(Locale.US), "tn. sh."),
					Map.entry(LocaleUtils.toLocale(Locale.UK), "tn. sh.")
			), "tn. sh.", UnitSystem.IMPERIAL
	);

	@Getter
	@NotNull
	private final BigDecimal divisor;

	@Getter
	@NotNull
	private final Map<Locale, StringArr2> localizedNames;

	@Getter
	@NotNull
	private final StringArr2 defaultName;

	@Getter
	@NotNull
	private final Map<Locale, String> localizedAbbreviations;

	@Getter
	@NotNull
	private final String defaultAbbreviation;

	@Getter
	@NotNull
	private final UnitSystem unitSystem;

	MassUnit(@NotNull BigDecimal divisor, @NotNull Map<Locale, StringArr2> localizedNames, @NotNull StringArr2 defaultName, @NotNull Map<Locale, String> localizedAbbreviations, @NotNull String defaultAbbreviation, @NotNull UnitSystem unitSystem) {
		this.divisor = divisor;
		this.localizedNames = localizedNames;
		this.defaultName = defaultName;
		this.localizedAbbreviations = localizedAbbreviations;
		this.defaultAbbreviation = defaultAbbreviation;
		this.unitSystem = unitSystem;
	}

	@Override
	public MassUnit getBaseUnit() {
		return KILOGRAM;
	}

	@Override
	public @NotNull String getId() {
		return this.name();
	}

	public static @Nullable MassUnit getUnit(@NotNull String unitStr) {
		for (MassUnit unit : values()) {
			if (unit.toString().equals(unitStr)) {
				return unit;
			}
		}
		return null;
	}

	@Override
	public @NotNull String toString() {
		return this.getClass().getSimpleName() + "." + this.name();
	}
}