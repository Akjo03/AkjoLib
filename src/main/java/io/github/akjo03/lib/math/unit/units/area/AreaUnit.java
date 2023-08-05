package io.github.akjo03.lib.math.unit.units.area;

import io.github.akjo03.lib.array.StringArr2;
import io.github.akjo03.lib.math.unit.UnitSystem;
import io.github.akjo03.lib.math.unit.derived.DerivedUnit;
import io.github.akjo03.lib.math.unit.derived.dimension.UnitDimension;
import io.github.akjo03.lib.math.unit.units.length.LengthUnit;
import lombok.Getter;
import org.apache.commons.lang3.LocaleUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

@Getter
@SuppressWarnings("unused")
public enum AreaUnit implements DerivedUnit<AreaUnit> {
	SQUARE_METRE(new UnitDimension(LengthUnit.METRE).power(2), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Quadratmeter", "Quadratmeter")),
					Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("square meter", "square meters")),
					Map.entry(LocaleUtils.toLocale(Locale.UK) , new StringArr2("square metre", "square metres"))
			), new StringArr2("square metre", "square metres"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "m²"),
					Map.entry(LocaleUtils.toLocale(Locale.US), "m²"),
					Map.entry(LocaleUtils.toLocale(Locale.UK) , "m²")
			), "m²", UnitSystem.METRIC
	),
	SQUARE_CENTIMETRE(new UnitDimension(LengthUnit.CENTIMETRE).power(2), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Quadratzentimeter", "Quadratzentimeter")),
					Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("square centimetre", "square centimetres")),
					Map.entry(LocaleUtils.toLocale(Locale.UK) , new StringArr2("square centimetre", "square centimetres"))
			), new StringArr2("square centimetre", "square centimetres"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "cm²"),
					Map.entry(LocaleUtils.toLocale(Locale.US), "cm²"),
					Map.entry(LocaleUtils.toLocale(Locale.UK) , "cm²")
			), "cm²", UnitSystem.METRIC
	),
	SQUARE_MILLIMETRE(new UnitDimension(LengthUnit.MILLIMETRE).power(2), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Quadratmillimeter", "Quadratmillimeter")),
					Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("square millimetre", "square millimetres")),
					Map.entry(LocaleUtils.toLocale(Locale.UK) , new StringArr2("square millimetre", "square millimetres"))
			), new StringArr2("square millimetre", "square millimetres"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "mm²"),
					Map.entry(LocaleUtils.toLocale(Locale.US), "mm²"),
					Map.entry(LocaleUtils.toLocale(Locale.UK) , "mm²")
			), "mm²", UnitSystem.METRIC
	),
	SQUARE_KILOMETRE(new UnitDimension(LengthUnit.KILOMETRE).power(2), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Quadratkilometer", "Quadratkilometer")),
					Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("square kilometre", "square kilometres")),
					Map.entry(LocaleUtils.toLocale(Locale.UK) , new StringArr2("square kilometre", "square kilometres"))
			), new StringArr2("square kilometre", "square kilometres"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "km²"),
					Map.entry(LocaleUtils.toLocale(Locale.US), "km²"),
					Map.entry(LocaleUtils.toLocale(Locale.UK) , "km²")
			), "km²", UnitSystem.METRIC
	),
	SQUARE_INCH(new UnitDimension(LengthUnit.INCH).power(2), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Quadratzoll", "Quadratzoll")),
					Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("square inch", "square inches")),
					Map.entry(LocaleUtils.toLocale(Locale.UK) , new StringArr2("square inch", "square inches"))
			), new StringArr2("square inch", "square inches"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "in²"),
					Map.entry(LocaleUtils.toLocale(Locale.US), "in²"),
					Map.entry(LocaleUtils.toLocale(Locale.UK), "in²")
			), "in²", UnitSystem.IMPERIAL
	),
	SQUARE_FOOT(new UnitDimension(LengthUnit.FOOT).power(2), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Quadratfuß", "Quadratfuß")),
					Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("square foot", "square feet")),
					Map.entry(LocaleUtils.toLocale(Locale.UK) , new StringArr2("square foot", "square feet"))
			), new StringArr2("square foot", "square feet"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "ft²"),
					Map.entry(LocaleUtils.toLocale(Locale.US), "ft²"),
					Map.entry(LocaleUtils.toLocale(Locale.UK), "ft²")
			), "ft²", UnitSystem.IMPERIAL
	),
	SQUARE_YARD(new UnitDimension(LengthUnit.YARD).power(2), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Quadratyard", "Quadratyard")),
					Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("square yard", "square yards")),
					Map.entry(LocaleUtils.toLocale(Locale.UK) , new StringArr2("square yard", "square yards"))
			), new StringArr2("square foot", "square foot"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "yd²"),
					Map.entry(LocaleUtils.toLocale(Locale.US), "yd²"),
					Map.entry(LocaleUtils.toLocale(Locale.UK), "yd²")
			), "yd²", UnitSystem.IMPERIAL
	),
	SQUARE_MILE(new UnitDimension(LengthUnit.MILE).power(2), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Quadratmeile", "Quadratmeile")),
					Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("square mile", "square miles")),
					Map.entry(LocaleUtils.toLocale(Locale.UK) , new StringArr2("square mile", "square miles"))
			), new StringArr2("square mile", "square mile"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "mi²"),
					Map.entry(LocaleUtils.toLocale(Locale.US), "mi²"),
					Map.entry(LocaleUtils.toLocale(Locale.UK), "mi²")
			), "mi²", UnitSystem.IMPERIAL
	),
	HECTARE(new UnitDimension(AreaUnit.SQUARE_METRE).multiply(new BigDecimal("10000")), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Hektar", "Hektare")),
					Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("hectare", "hectares")),
					Map.entry(LocaleUtils.toLocale(Locale.UK) , new StringArr2("hectare", "hectares"))
			), new StringArr2("square foot", "square foot"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "ha"),
					Map.entry(LocaleUtils.toLocale(Locale.US), "ha"),
					Map.entry(LocaleUtils.toLocale(Locale.UK), "ha")
			), "ha", UnitSystem.UNDEFINED
	),
	ACRE(new UnitDimension(AreaUnit.SQUARE_FOOT).multiply(new BigDecimal("43560")), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Morgen", "Morgen")),
					Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("acre", "acres")),
					Map.entry(LocaleUtils.toLocale(Locale.UK) , new StringArr2("acre", "acres"))
			), new StringArr2("acre", "acres"), Map.ofEntries(
					Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "Mg"),
					Map.entry(LocaleUtils.toLocale(Locale.US), "ac"),
					Map.entry(LocaleUtils.toLocale(Locale.UK), "ac")
			), "ac", UnitSystem.IMPERIAL
	);

	@NotNull private final Map<Locale, StringArr2> localizedNames;

	@NotNull private final StringArr2 defaultName;

	@NotNull private final java.util.Map<Locale, String> localizedAbbreviations;

	@NotNull private final String defaultAbbreviation;

	@NotNull private final UnitSystem unitSystem;

	@NotNull private final UnitDimension dimension;

	AreaUnit(@NotNull UnitDimension dimension, @NotNull Map<Locale, StringArr2> localizedNames, @NotNull StringArr2 defaultName, @NotNull String defaultAbbreviation, @NotNull UnitSystem unitSystem) {
		this.dimension = dimension;
		this.localizedNames = localizedNames;
		this.defaultName = defaultName;
		this.localizedAbbreviations = generateLocalizedAbbreviations();
		this.defaultAbbreviation = defaultAbbreviation;
		this.unitSystem = unitSystem;
	}

	AreaUnit(@NotNull UnitDimension dimension, @NotNull Map<Locale, StringArr2> localizedNames, @NotNull StringArr2 defaultName, @NotNull Map<Locale, String> localizedAbbreviations, @NotNull String defaultAbbreviation, @NotNull UnitSystem unitSystem) {
		this.dimension = dimension;
		this.localizedNames = localizedNames;
		this.defaultName = defaultName;
		this.localizedAbbreviations = localizedAbbreviations;
		this.defaultAbbreviation = defaultAbbreviation;
		this.unitSystem = unitSystem;
	}

	@Override
	public @NotNull String getId() { return this.name(); }

	public static @Nullable AreaUnit getUnit(@NotNull String unitStr) {
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