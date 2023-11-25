package io.github.akjo03.lib.math.unit.units.area;

import io.github.akjo03.lib.array.StringArr2;
import io.github.akjo03.lib.lang.Language;
import io.github.akjo03.lib.math.unit.UnitSystem;
import io.github.akjo03.lib.math.unit.derived.DerivedUnit;
import io.github.akjo03.lib.math.unit.derived.dimension.UnitDimension;
import io.github.akjo03.lib.math.unit.units.length.LengthUnit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
@SuppressWarnings("unused")
public enum AreaUnit implements DerivedUnit<AreaUnit> {
	SQUARE_METRE(new UnitDimension(LengthUnit.METRE).power(2), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Quadratmeter", "Quadratmeter")),
					Map.entry(Language.ENGLISH, new StringArr2("square meter", "square meters"))
			), new StringArr2("square metre", "square metres"), Map.ofEntries(
					Map.entry(Language.GERMAN, "m²"),
					Map.entry(Language.ENGLISH, "m²")
			), "m²", UnitSystem.METRIC
	),
	SQUARE_CENTIMETRE(new UnitDimension(LengthUnit.CENTIMETRE).power(2), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Quadratzentimeter", "Quadratzentimeter")),
					Map.entry(Language.ENGLISH, new StringArr2("square centimetre", "square centimetres"))
			), new StringArr2("square centimetre", "square centimetres"), Map.ofEntries(
					Map.entry(Language.GERMAN, "cm²"),
					Map.entry(Language.ENGLISH, "cm²")
			), "cm²", UnitSystem.METRIC
	),
	SQUARE_MILLIMETRE(new UnitDimension(LengthUnit.MILLIMETRE).power(2), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Quadratmillimeter", "Quadratmillimeter")),
					Map.entry(Language.ENGLISH, new StringArr2("square millimetre", "square millimetres"))
			), new StringArr2("square millimetre", "square millimetres"), Map.ofEntries(
					Map.entry(Language.GERMAN, "mm²"),
					Map.entry(Language.ENGLISH, "mm²")
			), "mm²", UnitSystem.METRIC
	),
	SQUARE_KILOMETRE(new UnitDimension(LengthUnit.KILOMETRE).power(2), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Quadratkilometer", "Quadratkilometer")),
					Map.entry(Language.ENGLISH, new StringArr2("square kilometre", "square kilometres"))
			), new StringArr2("square kilometre", "square kilometres"), Map.ofEntries(
					Map.entry(Language.GERMAN, "km²"),
					Map.entry(Language.ENGLISH, "km²")
			), "km²", UnitSystem.METRIC
	),
	SQUARE_INCH(new UnitDimension(LengthUnit.INCH).power(2), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Quadratzoll", "Quadratzoll")),
					Map.entry(Language.ENGLISH, new StringArr2("square inch", "square inches"))
			), new StringArr2("square inch", "square inches"), Map.ofEntries(
					Map.entry(Language.GERMAN, "in²"),
					Map.entry(Language.ENGLISH, "in²")
			), "in²", UnitSystem.IMPERIAL
	),
	SQUARE_FOOT(new UnitDimension(LengthUnit.FOOT).power(2), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Quadratfuß", "Quadratfuß")),
					Map.entry(Language.ENGLISH, new StringArr2("square foot", "square feet"))
			), new StringArr2("square foot", "square feet"), Map.ofEntries(
					Map.entry(Language.GERMAN, "ft²"),
					Map.entry(Language.ENGLISH, "ft²")
			), "ft²", UnitSystem.IMPERIAL
	),
	SQUARE_YARD(new UnitDimension(LengthUnit.YARD).power(2), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Quadratyard", "Quadratyard")),
					Map.entry(Language.ENGLISH, new StringArr2("square yard", "square yards"))
			), new StringArr2("square foot", "square foot"), Map.ofEntries(
					Map.entry(Language.GERMAN, "yd²"),
					Map.entry(Language.ENGLISH, "yd²")
			), "yd²", UnitSystem.IMPERIAL
	),
	SQUARE_MILE(new UnitDimension(LengthUnit.MILE).power(2), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Quadratmeile", "Quadratmeile")),
					Map.entry(Language.ENGLISH, new StringArr2("square mile", "square miles"))
			), new StringArr2("square mile", "square mile"), Map.ofEntries(
					Map.entry(Language.GERMAN, "mi²"),
					Map.entry(Language.ENGLISH, "mi²")
			), "mi²", UnitSystem.IMPERIAL
	),
	HECTARE(new UnitDimension(AreaUnit.SQUARE_METRE).multiply(new BigDecimal("10000")), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Hektar", "Hektare")),
					Map.entry(Language.ENGLISH, new StringArr2("hectare", "hectares"))
			), new StringArr2("square foot", "square foot"), Map.ofEntries(
					Map.entry(Language.GERMAN, "ha"),
					Map.entry(Language.ENGLISH, "ha")
			), "ha", UnitSystem.UNDEFINED
	),
	ACRE(new UnitDimension(AreaUnit.SQUARE_FOOT).multiply(new BigDecimal("43560")), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Morgen", "Morgen")),
					Map.entry(Language.ENGLISH, new StringArr2("acre", "acres"))
			), new StringArr2("acre", "acres"), Map.ofEntries(
					Map.entry(Language.GERMAN, "Mg"),
					Map.entry(Language.ENGLISH, "ac")
			), "ac", UnitSystem.IMPERIAL
	);

	@NotNull private final UnitDimension dimension;

	@NotNull private final Map<Language, StringArr2> localizedNames;

	@NotNull private final StringArr2 defaultName;

	@NotNull private final Map<Language, String> localizedAbbreviations;

	@NotNull private final String defaultAbbreviation;

	@NotNull private final UnitSystem unitSystem;

	private static final Map<String, AreaUnit> UNIT_MAP = Arrays.stream(values())
			.collect(Collectors.toUnmodifiableMap(AreaUnit::getId, unit -> unit));

	@Override
	@Contract(pure = true)
	public @NotNull String getId() { return this.name(); }

	@Override
	public @NotNull String toString() {
		return this.getClass().getSimpleName() + "." + this.name();
	}

	public static Optional<AreaUnit> getUnit(@NotNull String unitStr) {
		return Optional.ofNullable(UNIT_MAP.get(unitStr));
	}
}