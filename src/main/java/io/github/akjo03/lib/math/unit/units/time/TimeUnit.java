package io.github.akjo03.lib.math.unit.units.time;

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
public enum TimeUnit implements BaseUnit<TimeUnit> {
	SECOND(new BigDecimal("1"), Map.ofEntries(
			Map.entry(Language.GERMAN, new StringArr2("Sekunde", "Sekunden")),
			Map.entry(Language.ENGLISH, new StringArr2("second", "seconds"))
	), new StringArr2("second", "seconds"), Map.ofEntries(
			Map.entry(Language.GERMAN, "s"),
			Map.entry(Language.ENGLISH, "s")
	), "s", UnitSystem.METRIC),
	MINUTE(new BigDecimal("60"), Map.ofEntries(
			Map.entry(Language.GERMAN, new StringArr2("Minute", "Minuten")),
			Map.entry(Language.ENGLISH, new StringArr2("minute", "minutes"))
	), new StringArr2("minute", "minutes"), Map.ofEntries(
			Map.entry(Language.GERMAN, "min"),
			Map.entry(Language.ENGLISH, "min")
	), "min", UnitSystem.METRIC),
	HOUR(new BigDecimal("3600"), Map.ofEntries(
			Map.entry(Language.GERMAN, new StringArr2("Stunde", "Stunden")),
			Map.entry(Language.ENGLISH, new StringArr2("hour", "hours"))
	), new StringArr2("hour", "hours"), Map.ofEntries(
			Map.entry(Language.GERMAN, "h"),
			Map.entry(Language.ENGLISH, "h")
	), "h", UnitSystem.METRIC),
	DAY(new BigDecimal("86400"), Map.ofEntries(
			Map.entry(Language.GERMAN, new StringArr2("Tag", "Tage")),
			Map.entry(Language.ENGLISH, new StringArr2("day", "days"))
	), new StringArr2("day", "days"), Map.ofEntries(
			Map.entry(Language.GERMAN, "d"),
			Map.entry(Language.ENGLISH, "d")
	), "d", UnitSystem.METRIC),
	WEEK(new BigDecimal("604800"), Map.ofEntries(
			Map.entry(Language.GERMAN, new StringArr2("Woche", "Wochen")),
			Map.entry(Language.ENGLISH, new StringArr2("week", "weeks"))
	), new StringArr2("week", "weeks"), Map.ofEntries(
			Map.entry(Language.GERMAN, "Wo."),
			Map.entry(Language.ENGLISH, "wk")
	), "wk", UnitSystem.METRIC),
	MONTH(new BigDecimal("2592000"), Map.ofEntries(
			Map.entry(Language.GERMAN, new StringArr2("Monat", "Monate")),
			Map.entry(Language.ENGLISH, new StringArr2("month", "months"))
	), new StringArr2("month", "months"), Map.ofEntries(
			Map.entry(Language.GERMAN, "mo"),
			Map.entry(Language.ENGLISH, "mo")
	), "mo", UnitSystem.METRIC),
	YEAR(new BigDecimal("31536000"), Map.ofEntries(
			Map.entry(Language.GERMAN, new StringArr2("Jahr", "Jahre")),
			Map.entry(Language.ENGLISH, new StringArr2("year", "years"))
	), new StringArr2("year", "years"), Map.ofEntries(
			Map.entry(Language.GERMAN, "a"),
			Map.entry(Language.ENGLISH, "yr")
	), "yr", UnitSystem.METRIC);

	@NotNull private final BigDecimal divisor;

	@NotNull private final Map<Language, StringArr2> localizedNames;

	@NotNull private final StringArr2 defaultName;

	@NotNull private final Map<Language, String> localizedAbbreviations;

	@NotNull private final String defaultAbbreviation;

	@NotNull private final UnitSystem unitSystem;

	@Override
	public TimeUnit getBaseUnit() { return SECOND; }

	@Override
	public @NotNull String getId() { return this.name(); }

	public static @Nullable TimeUnit getUnit(@NotNull String unitStr) {
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