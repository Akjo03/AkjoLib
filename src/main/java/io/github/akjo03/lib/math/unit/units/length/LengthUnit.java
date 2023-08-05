package io.github.akjo03.lib.math.unit.units.length;

import io.github.akjo03.lib.array.StringArr2;
import io.github.akjo03.lib.math.unit.UnitSystem;
import io.github.akjo03.lib.math.unit.base.BaseUnit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.LocaleUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

@Getter
@RequiredArgsConstructor
@SuppressWarnings("unused")
public enum LengthUnit implements BaseUnit<LengthUnit> {
	KILOMETRE(new BigDecimal("1000.0000"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Kilometer", "Kilometer")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("kilometer", "kilometers")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("kilometre", "kilometres"))
	), new StringArr2("kilometre", "kilometres"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "km"),
			Map.entry(LocaleUtils.toLocale(Locale.US), "km"),
			Map.entry(LocaleUtils.toLocale(Locale.UK), "km")
	), "km", UnitSystem.METRIC),
	METRE(new BigDecimal("1.0000"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Meter", "Meter")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("meter", "meters")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("metre", "metres"))
	), new StringArr2("metre", "metres"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN),"m"),
			Map.entry(LocaleUtils.toLocale(Locale.US),"m"),
			Map.entry(LocaleUtils.toLocale(Locale.UK),"m")
	), "m", UnitSystem.METRIC),
	DECIMETRE(new BigDecimal("0.1"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Dezimeter", "Dezimeter")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("decimeter", "decimeters")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("decimetre", "decimetres"))
	), new StringArr2("decimetre", "decimetres"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN),"dm"),
			Map.entry(LocaleUtils.toLocale(Locale.US),"dm"),
			Map.entry(LocaleUtils.toLocale(Locale.UK),"dm")
	), "dm", UnitSystem.METRIC),
	CENTIMETRE(new BigDecimal("0.0100"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Zentimeter", "Zentimeter")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("centimeter", "centimeters")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("centimetre", "centimetres"))
	), new StringArr2("centimetre", "centimetres"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN),"cm"),
			Map.entry(LocaleUtils.toLocale(Locale.US),"cm"),
			Map.entry(LocaleUtils.toLocale(Locale.UK),"cm")
	), "cm", UnitSystem.METRIC),
	MILLIMETRE(new BigDecimal("0.0010"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Millimeter", "Millimeter")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("millimeter", "millimeters")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("millimetre", "millimetres"))
	), new StringArr2("millimetre", "millimetres"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN),"mm"),
			Map.entry(LocaleUtils.toLocale(Locale.US),"mm"),
			Map.entry(LocaleUtils.toLocale(Locale.UK),"mm")
	), "mm", UnitSystem.METRIC),
	MILE(new BigDecimal("1609.3440"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Meile", "Meilen")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("mile", "miles")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("mile", "miles"))
	), new StringArr2("mile", "miles"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN),"mi"),
			Map.entry(LocaleUtils.toLocale(Locale.US),"mi"),
			Map.entry(LocaleUtils.toLocale(Locale.UK),"mi")
	), "mi", UnitSystem.IMPERIAL),
	NAUTICAL_MILE(new BigDecimal("1852.0000"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Seemeile", "Seemeilen")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("nautical mile", "nautical miles")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("nautical mile", "nautical miles"))
	), new StringArr2("nautical mile", "nautical miles"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN),"nmi"),
			Map.entry(LocaleUtils.toLocale(Locale.US),"nmi"),
			Map.entry(LocaleUtils.toLocale(Locale.UK),"nmi")
	), "nmi", UnitSystem.UNDEFINED),
	YARD(new BigDecimal("0.9144"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Yard", "Yards")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("yard", "yards")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("yard", "yards"))
	), new StringArr2("yard", "yards"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN),"yd"),
			Map.entry(LocaleUtils.toLocale(Locale.US),"yd"),
			Map.entry(LocaleUtils.toLocale(Locale.UK),"yd")
	), "yd", UnitSystem.IMPERIAL),
	FOOT(new BigDecimal("0.3048"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Fuß", "Fuß")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("foot", "feet")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("foot", "feet"))
	), new StringArr2("foot", "feet"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN),"ft"),
			Map.entry(LocaleUtils.toLocale(Locale.US),"ft"),
			Map.entry(LocaleUtils.toLocale(Locale.UK),"ft")
	), "ft", UnitSystem.IMPERIAL),
	INCH(new BigDecimal("0.0254"), Map.ofEntries(
			Map.entry(Locale.GERMAN, new StringArr2("Zoll", "Zoll")),
			Map.entry(Locale.US, new StringArr2("inch", "inches")),
			Map.entry(Locale.UK, new StringArr2("inch", "inches"))
	), new StringArr2("inch", "inches"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN),"in"),
			Map.entry(LocaleUtils.toLocale(Locale.US),"in"),
			Map.entry(LocaleUtils.toLocale(Locale.UK),"in")
	), "in", UnitSystem.IMPERIAL);

	@NotNull private final BigDecimal divisor;

	@NotNull private final Map<Locale, StringArr2> localizedNames;

	@NotNull private final StringArr2 defaultName;

	@NotNull private final Map<Locale, String> localizedAbbreviations;

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