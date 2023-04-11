package io.github.akjo03.lib.math.unit.units.time;

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
public enum TimeUnit implements BaseUnit<TimeUnit> {
	SECOND(new BigDecimal("1"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Sekunde", "Sekunden")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("second", "seconds")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("second", "seconds"))
	), new StringArr2("second", "seconds"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "s"),
			Map.entry(LocaleUtils.toLocale(Locale.US), "s"),
			Map.entry(LocaleUtils.toLocale(Locale.UK), "s")
	), "s", UnitSystem.METRIC),
	MINUTE(new BigDecimal("60"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Minute", "Minuten")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("minute", "minutes")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("minute", "minutes"))
	), new StringArr2("minute", "minutes"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "min"),
			Map.entry(LocaleUtils.toLocale(Locale.US), "min"),
			Map.entry(LocaleUtils.toLocale(Locale.UK), "min")
	), "min", UnitSystem.METRIC),
	HOUR(new BigDecimal("3600"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Stunde", "Stunden")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("hour", "hours")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("hour", "hours"))
	), new StringArr2("hour", "hours"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "h"),
			Map.entry(LocaleUtils.toLocale(Locale.US), "h"),
			Map.entry(LocaleUtils.toLocale(Locale.UK), "h")
	), "h", UnitSystem.METRIC),
	DAY(new BigDecimal("86400"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Tag", "Tage")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("day", "days")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("day", "days"))
	), new StringArr2("day", "days"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "d"),
			Map.entry(LocaleUtils.toLocale(Locale.US), "d"),
			Map.entry(LocaleUtils.toLocale(Locale.UK), "d")
	), "d", UnitSystem.METRIC),
	WEEK(new BigDecimal("604800"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Woche", "Wochen")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("week", "weeks")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("week", "weeks"))
	), new StringArr2("week", "weeks"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "Wo."),
			Map.entry(LocaleUtils.toLocale(Locale.US), "wk"),
			Map.entry(LocaleUtils.toLocale(Locale.UK), "wk")
	), "wk", UnitSystem.METRIC),
	MONTH(new BigDecimal("2592000"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Monat", "Monate")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("month", "months")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("month", "months"))
	), new StringArr2("month", "months"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "mo"),
			Map.entry(LocaleUtils.toLocale(Locale.US), "mo"),
			Map.entry(LocaleUtils.toLocale(Locale.UK), "mo")
	), "mo", UnitSystem.METRIC),
	YEAR(new BigDecimal("31536000"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), new StringArr2("Jahr", "Jahre")),
			Map.entry(LocaleUtils.toLocale(Locale.US), new StringArr2("year", "years")),
			Map.entry(LocaleUtils.toLocale(Locale.UK), new StringArr2("year", "years"))
	), new StringArr2("year", "years"), Map.ofEntries(
			Map.entry(LocaleUtils.toLocale(Locale.GERMAN), "a"),
			Map.entry(LocaleUtils.toLocale(Locale.US), "yr"),
			Map.entry(LocaleUtils.toLocale(Locale.UK), "yr")
	), "yr", UnitSystem.METRIC);

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

	TimeUnit(@NotNull BigDecimal divisor, @NotNull Map<Locale, StringArr2> localizedNames, @NotNull StringArr2 defaultName, @NotNull Map<Locale, String> localizedAbbreviations, @NotNull String defaultAbbreviation, @NotNull UnitSystem unitSystem) {
		this.divisor = divisor;
		this.localizedNames = localizedNames;
		this.defaultName = defaultName;
		this.localizedAbbreviations = localizedAbbreviations;
		this.defaultAbbreviation = defaultAbbreviation;
		this.unitSystem = unitSystem;
	}

	@Override
	public TimeUnit getBaseUnit() {
		return SECOND;
	}

	@Override
	public @NotNull String getId() {
		return this.name();
	}

	public static @Nullable TimeUnit getUnit(@NotNull String unitStr) {
		for (TimeUnit unit : values()) {
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