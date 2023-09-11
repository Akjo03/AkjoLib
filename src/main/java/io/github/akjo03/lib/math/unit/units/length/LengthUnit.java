package io.github.akjo03.lib.math.unit.units.length;

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
public enum LengthUnit implements BaseUnit<LengthUnit> {
	KILOMETRE(new BigDecimal("1000.0000"), Map.ofEntries(
			Map.entry(Language.GERMAN, new StringArr2("Kilometer", "Kilometer")),
			Map.entry(Language.ENGLISH, new StringArr2("kilometer", "kilometers"))
	), new StringArr2("kilometre", "kilometres"), Map.ofEntries(
			Map.entry(Language.GERMAN, "km"),
			Map.entry(Language.ENGLISH, "km")
	), "km", UnitSystem.METRIC),
	METRE(new BigDecimal("1.0000"), Map.ofEntries(
			Map.entry(Language.GERMAN, new StringArr2("Meter", "Meter")),
			Map.entry(Language.ENGLISH, new StringArr2("meter", "meters"))
	), new StringArr2("metre", "metres"), Map.ofEntries(
			Map.entry(Language.GERMAN,"m"),
			Map.entry(Language.ENGLISH,"m")
	), "m", UnitSystem.METRIC),
	DECIMETRE(new BigDecimal("0.1"), Map.ofEntries(
			Map.entry(Language.GERMAN, new StringArr2("Dezimeter", "Dezimeter")),
			Map.entry(Language.ENGLISH, new StringArr2("decimeter", "decimeters"))
	), new StringArr2("decimetre", "decimetres"), Map.ofEntries(
			Map.entry(Language.GERMAN,"dm"),
			Map.entry(Language.ENGLISH,"dm")
	), "dm", UnitSystem.METRIC),
	CENTIMETRE(new BigDecimal("0.0100"), Map.ofEntries(
			Map.entry(Language.GERMAN, new StringArr2("Zentimeter", "Zentimeter")),
			Map.entry(Language.ENGLISH, new StringArr2("centimeter", "centimeters"))
	), new StringArr2("centimetre", "centimetres"), Map.ofEntries(
			Map.entry(Language.GERMAN,"cm"),
			Map.entry(Language.ENGLISH,"cm")
	), "cm", UnitSystem.METRIC),
	MILLIMETRE(new BigDecimal("0.0010"), Map.ofEntries(
			Map.entry(Language.GERMAN, new StringArr2("Millimeter", "Millimeter")),
			Map.entry(Language.ENGLISH, new StringArr2("millimeter", "millimeters"))
	), new StringArr2("millimetre", "millimetres"), Map.ofEntries(
			Map.entry(Language.GERMAN,"mm"),
			Map.entry(Language.ENGLISH,"mm")
	), "mm", UnitSystem.METRIC),
	MILE(new BigDecimal("1609.3440"), Map.ofEntries(
			Map.entry(Language.GERMAN, new StringArr2("Meile", "Meilen")),
			Map.entry(Language.ENGLISH, new StringArr2("mile", "miles"))
	), new StringArr2("mile", "miles"), Map.ofEntries(
			Map.entry(Language.GERMAN,"mi"),
			Map.entry(Language.ENGLISH,"mi")
	), "mi", UnitSystem.IMPERIAL),
	NAUTICAL_MILE(new BigDecimal("1852.0000"), Map.ofEntries(
			Map.entry(Language.GERMAN, new StringArr2("Seemeile", "Seemeilen")),
			Map.entry(Language.ENGLISH, new StringArr2("nautical mile", "nautical miles"))
	), new StringArr2("nautical mile", "nautical miles"), Map.ofEntries(
			Map.entry(Language.GERMAN,"nmi"),
			Map.entry(Language.ENGLISH,"nmi")
	), "nmi", UnitSystem.UNDEFINED),
	YARD(new BigDecimal("0.9144"), Map.ofEntries(
			Map.entry(Language.GERMAN, new StringArr2("Yard", "Yards")),
			Map.entry(Language.ENGLISH, new StringArr2("yard", "yards"))
	), new StringArr2("yard", "yards"), Map.ofEntries(
			Map.entry(Language.GERMAN,"yd"),
			Map.entry(Language.ENGLISH,"yd")
	), "yd", UnitSystem.IMPERIAL),
	FOOT(new BigDecimal("0.3048"), Map.ofEntries(
			Map.entry(Language.GERMAN, new StringArr2("Fuß", "Fuß")),
			Map.entry(Language.ENGLISH, new StringArr2("foot", "feet"))
	), new StringArr2("foot", "feet"), Map.ofEntries(
			Map.entry(Language.GERMAN,"ft"),
			Map.entry(Language.ENGLISH,"ft")
	), "ft", UnitSystem.IMPERIAL),
	INCH(new BigDecimal("0.0254"), Map.ofEntries(
			Map.entry(Language.GERMAN, new StringArr2("Zoll", "Zoll")),
			Map.entry(Language.ENGLISH, new StringArr2("inch", "inches"))
	), new StringArr2("inch", "inches"), Map.ofEntries(
			Map.entry(Language.GERMAN,"in"),
			Map.entry(Language.ENGLISH,"in")
	), "in", UnitSystem.IMPERIAL);

	@NotNull private final BigDecimal divisor;

	@NotNull private final Map<Language, StringArr2> localizedNames;

	@NotNull private final StringArr2 defaultName;

	@NotNull private final Map<Language, String> localizedAbbreviations;

	@NotNull private final String defaultAbbreviation;

	@NotNull private final UnitSystem unitSystem;

	@Override
	public LengthUnit getBaseUnit() { return METRE;  }

	@Override
	public @NotNull String getId() { return this.name(); }

	public static @Nullable LengthUnit getUnit(@NotNull String unitStr) {
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