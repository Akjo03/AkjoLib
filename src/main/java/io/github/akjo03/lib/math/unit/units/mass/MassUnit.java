package io.github.akjo03.lib.math.unit.units.mass;

import io.github.akjo03.lib.array.StringArr2;
import io.github.akjo03.lib.lang.Language;
import io.github.akjo03.lib.math.unit.UnitSystem;
import io.github.akjo03.lib.math.unit.base.BaseUnit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

@Getter
@RequiredArgsConstructor
@SuppressWarnings("unused")
public enum MassUnit implements BaseUnit<MassUnit> {
	KILOGRAM(new BigDecimal("1.000000000000"), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Kilogramm", "Kilogramm")),
					Map.entry(Language.ENGLISH, new StringArr2("kilogram", "kilograms"))
			), new StringArr2("kilogram", "kilograms"), Map.ofEntries(
					Map.entry(Language.GERMAN, "kg"),
					Map.entry(Language.ENGLISH, "kg")
			), "kg", UnitSystem.METRIC
	),
	GRAM(new BigDecimal("0.001000000000"), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Gramm", "Gramm")),
					Map.entry(Language.ENGLISH, new StringArr2("gram", "grams"))
			), new StringArr2("gram", "grams"), Map.ofEntries(
					Map.entry(Language.GERMAN, "g"),
					Map.entry(Language.ENGLISH, "g")
			), "g", UnitSystem.METRIC
	),
	MILLIGRAM(new BigDecimal("0.000001000000"), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Milligramm", "Milligramm")),
					Map.entry(Language.ENGLISH, new StringArr2("milligram", "milligrams"))
			), new StringArr2("milligram", "milligrams"), Map.ofEntries(
					Map.entry(Language.GERMAN, "mg"),
					Map.entry(Language.ENGLISH, "mg")
			), "mg", UnitSystem.METRIC
	),
	MICROGRAM(new BigDecimal("0.000000001000"), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Mikrogramm", "Mikrogramm")),
					Map.entry(Language.ENGLISH, new StringArr2("microgram", "micrograms"))
			), new StringArr2("microgram", "micrograms"), Map.ofEntries(
					Map.entry(Language.GERMAN, "µg"),
					Map.entry(Language.ENGLISH, "µg")
			), "µg", UnitSystem.METRIC
	),
	POUND(new BigDecimal("0.453592370000"), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Pfund", "Pfund")),
					Map.entry(Language.ENGLISH, new StringArr2("pound", "pounds"))
			), new StringArr2("pound", "pounds"), Map.ofEntries(
					Map.entry(Language.GERMAN, "lb"),
					Map.entry(Language.ENGLISH, "lb")
			), "lb", UnitSystem.IMPERIAL
	),
	OUNCE(new BigDecimal("0.028349523125"), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Unze", "Unzen")),
					Map.entry(Language.ENGLISH, new StringArr2("ounce", "ounces"))
			), new StringArr2("ounce", "ounces"), Map.ofEntries(
					Map.entry(Language.GERMAN, "oz"),
					Map.entry(Language.ENGLISH, "oz")
			), "oz", UnitSystem.IMPERIAL
	),
	CARAT(new BigDecimal("0.000200000000"), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Karat", "Karat")),
					Map.entry(Language.ENGLISH, new StringArr2("carat", "carats"))
			), new StringArr2("ounce", "ounces"), Map.ofEntries(
					Map.entry(Language.GERMAN, "kt"),
					Map.entry(Language.ENGLISH, "ct")
			), "ct", UnitSystem.UNDEFINED
	),
	METRIC_TON(new BigDecimal("1000.000000000000"), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Metrische Tonne", "Metrische Tonnen")),
					Map.entry(Language.ENGLISH, new StringArr2("metric ton", "metric tons"))
			), new StringArr2("metric ton", "metric tons"), Map.ofEntries(
					Map.entry(Language.GERMAN, "t"),
					Map.entry(Language.ENGLISH, "t")
			), "ct", UnitSystem.METRIC
	),
	LONG_TON(new BigDecimal("1016.046908800000"), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Britische Tonne", "Britische Tonnen")),
					Map.entry(Language.ENGLISH, new StringArr2("long ton", "long tons"))
			), new StringArr2("long ton", "long tons"), Map.ofEntries(
					Map.entry(Language.GERMAN, "tn. l."),
					Map.entry(Language.ENGLISH, "tn. l.")
			), "tn. l.", UnitSystem.IMPERIAL
	),
	SHORT_TON(new BigDecimal("907.184740000000"), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Amerikanische Tonne", "Amerikanische Tonnen")),
					Map.entry(Language.ENGLISH, new StringArr2("short ton", "short tons"))
			), new StringArr2("short ton", "short tons"), Map.ofEntries(
					Map.entry(Language.GERMAN, "tn. sh."),
					Map.entry(Language.ENGLISH, "tn. sh.")
			), "tn. sh.", UnitSystem.IMPERIAL
	);

	@NotNull private final BigDecimal divisor;

	@NotNull private final Map<Language, StringArr2> localizedNames;

	@NotNull private final StringArr2 defaultName;

	@NotNull private final Map<Language, String> localizedAbbreviations;

	@NotNull private final String defaultAbbreviation;

	@NotNull private final UnitSystem unitSystem;

	@Override
	public MassUnit getBaseUnit() { return KILOGRAM; }

	@Override
	public @NotNull String getId() { return this.name(); }

	public static @Nullable MassUnit getUnit(@NotNull String unitStr) {
		return Arrays.stream(values())
				.filter(unit -> unit.toString().equals(unitStr))
				.findFirst()
				.orElse(null);
	}

	@Override
	public @NotNull String toString() {
		return this.getClass().getSimpleName() + "." + this.name();
	}
}