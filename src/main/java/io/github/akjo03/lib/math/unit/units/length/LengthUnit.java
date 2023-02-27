package io.github.akjo03.lib.math.unit.units.length;

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
public enum LengthUnit implements BaseUnit<LengthUnit> {
	/**
	 * @see <a href="https://en.wikipedia.org/wiki/Kilometre">Kilometre - Wikipedia</a>
	 */
	KILOMETRE(new BigDecimal("1000.0000"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Kilometer", "Kilometer")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("kilometer", "kilometers")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("kilometre", "kilometres"))
	), new StringArr2("kilometre", "kilometres"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "km"),
			Map.entry(LocaleUtils.toLocale(Locale.US), "km"),
			Map.entry(LocaleUtils.toLocale(Locale.UK), "km")
	), "km", UnitSystem.METRIC),
	/**
	 * @see <a href="https://en.wikipedia.org/wiki/Metre">Metre - Wikipedia</a>
	 */
	METRE(new BigDecimal("1.0000"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Meter", "Meter")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("meter", "meters")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("metre", "metres"))
	), new StringArr2("metre", "metres"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN),"m"),
			Map.entry(LocaleUtils.toLocale(Locale.US),"m"),
			Map.entry(LocaleUtils.toLocale(Locale.UK),"m")
	), "m", UnitSystem.METRIC),
	/**
	 * @see <a href="https://en.wikipedia.org/wiki/Decimetre">Decimetre - Wikipedia</a>
	 */
	DECIMETRE(new BigDecimal("0.1"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Dezimeter", "Dezimeter")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("decimeter", "decimeters")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("decimetre", "decimetres"))
	), new StringArr2("decimetre", "decimetres"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN),"dm"),
			Map.entry(LocaleUtils.toLocale(Locale.US),"dm"),
			Map.entry(LocaleUtils.toLocale(Locale.UK),"dm")
	), "dm", UnitSystem.METRIC),
	/**
	 * @see <a href="https://en.wikipedia.org/wiki/Centimetre">Centimetre - Wikipedia</a>
	 */
	CENTIMETRE(new BigDecimal("0.0100"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Zentimeter", "Zentimeter")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("centimeter", "centimeters")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("centimetre", "centimetres"))
	), new StringArr2("centimetre", "centimetres"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN),"cm"),
			Map.entry(LocaleUtils.toLocale(Locale.US),"cm"),
			Map.entry(LocaleUtils.toLocale(Locale.UK),"cm")
	), "cm", UnitSystem.METRIC),
	/**
	 * @see <a href="https://en.wikipedia.org/wiki/Millimetre">Millimetre - Wikipedia</a>
	 */
	MILLIMETRE(new BigDecimal("0.0010"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Millimeter", "Millimeter")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("millimeter", "millimeters")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("millimetre", "millimetres"))
	), new StringArr2("millimetre", "millimetres"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN),"mm"),
			Map.entry(LocaleUtils.toLocale(Locale.US),"mm"),
			Map.entry(LocaleUtils.toLocale(Locale.UK),"mm")
	), "mm", UnitSystem.METRIC),
	/**
	 * @see <a href="https://en.wikipedia.org/wiki/Mile">Mile - Wikipedia</a>
	 */
	MILE(new BigDecimal("1609.3440"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Meile", "Meilen")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("mile", "miles")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("mile", "miles"))
	), new StringArr2("mile", "miles"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN),"mi"),
			Map.entry(LocaleUtils.toLocale(Locale.US),"mi"),
			Map.entry(LocaleUtils.toLocale(Locale.UK),"mi")
	), "mi", UnitSystem.IMPERIAL),
	/**
	 * @see <a href="https://en.wikipedia.org/wiki/Nautical_mile">Nautical mile - Wikipedia</a>
	 */
	NAUTICAL_MILE(new BigDecimal("1852.0000"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Seemeile", "Seemeilen")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("nautical mile", "nautical miles")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("nautical mile", "nautical miles"))
	), new StringArr2("nautical mile", "nautical miles"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN),"nmi"),
			Map.entry(LocaleUtils.toLocale(Locale.US),"nmi"),
			Map.entry(LocaleUtils.toLocale(Locale.UK),"nmi")
	), "nmi", UnitSystem.UNDEFINED),
	/**
	 * @see <a href="https://en.wikipedia.org/wiki/Yard">Yard - Wikipedia</a>
	 */
	YARD(new BigDecimal("0.9144"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Yard", "Yards")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("yard", "yards")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("yard", "yards"))
	), new StringArr2("yard", "yards"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN),"yd"),
			Map.entry(LocaleUtils.toLocale(Locale.US),"yd"),
			Map.entry(LocaleUtils.toLocale(Locale.UK),"yd")
	), "yd", UnitSystem.IMPERIAL),
	/**
	 * @see <a href="https://en.wikipedia.org/wiki/Foot_(unit)">Foot (unit) - Wikipedia</a>
	 */
	FOOT(new BigDecimal("0.3048"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Fuß", "Fuß")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("foot", "feet")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("foot", "feet"))
	), new StringArr2("foot", "feet"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN),"ft"),
			Map.entry(LocaleUtils.toLocale(Locale.US),"ft"),
			Map.entry(LocaleUtils.toLocale(Locale.UK),"ft")
	), "ft", UnitSystem.IMPERIAL),
	/**
	 * @see <a href="https://en.wikipedia.org/wiki/Inch">Inch - Wikipedia</a>
	 */
	INCH(new BigDecimal("0.0254"), Map.ofEntries(
			Map.entry(Locale.GERMAN, new StringArr2("Zoll", "Zoll")),
			Map.entry(Locale.US, new StringArr2("inch", "inches")),
			Map.entry(Locale.UK, new StringArr2("inch", "inches"))
	), new StringArr2("inch", "inches"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN),"in"),
			Map.entry(LocaleUtils.toLocale(Locale.US),"in"),
			Map.entry(LocaleUtils.toLocale(Locale.UK),"in")
	), "in", UnitSystem.IMPERIAL);

	@Getter
	@NotNull
	private final BigDecimal divisor;

	@Getter
	@NotNull private final Map<Locale, StringArr2> localizedNames;

	@Getter
	@NotNull private final StringArr2 defaultName;

	@Getter
	@NotNull private final Map<Locale, String> localizedAbbreviations;

	@Getter
	@NotNull private final String defaultAbbreviation;

	@Getter
	@NotNull private final UnitSystem unitSystem;

	/**
	 * Defines a new unit of length.
	 *
	 * @param divisor The factor to convert from the base unit to this unit.
	 * @param localizedNames A list of translations for the name this unit. The locale is the key and the value is an array of two strings. The first string is the singular form of the unit and the second string is the plural form.
	 * @param defaultName Default name of this unit.
	 * @param localizedAbbreviations A list of translations for the abbreviation of this unit. The locale is the key and the value is the abbreviation.
	 * @param defaultAbbreviation Default abbreviation for this unit.
	 * @param unitSystem The unit system this unit belongs to.
	 */
	LengthUnit(@NotNull BigDecimal divisor, @NotNull Map<Locale, StringArr2> localizedNames, @NotNull StringArr2 defaultName, @NotNull Map<Locale, String> localizedAbbreviations, @NotNull String defaultAbbreviation, @NotNull UnitSystem unitSystem) {
		this.divisor = divisor;
		this.localizedNames = localizedNames;
		this.defaultName = defaultName;
		this.localizedAbbreviations = localizedAbbreviations;
		this.defaultAbbreviation = defaultAbbreviation;
		this.unitSystem = unitSystem;
	}



	@Override
	public LengthUnit getBaseUnit() {
		return METRE;
	}

	@Override
	public @NotNull String getID() {
		return this.name();
	}

	public static @Nullable LengthUnit getUnit(@NotNull String unitStr) {
		for (LengthUnit unit : values()) {
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