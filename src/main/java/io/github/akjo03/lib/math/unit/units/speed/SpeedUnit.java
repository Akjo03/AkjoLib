package io.github.akjo03.lib.math.unit.units.speed;

import io.github.akjo03.lib.array.StringArr2;
import io.github.akjo03.lib.lang.Language;
import io.github.akjo03.lib.math.unit.UnitSystem;
import io.github.akjo03.lib.math.unit.derived.DerivedUnit;
import io.github.akjo03.lib.math.unit.derived.dimension.UnitDimension;
import io.github.akjo03.lib.math.unit.units.length.LengthUnit;
import io.github.akjo03.lib.math.unit.units.time.TimeUnit;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Map;

@Getter
@SuppressWarnings("unused")
public enum SpeedUnit implements DerivedUnit<SpeedUnit> {
	METRES_PER_SECOND(new UnitDimension(LengthUnit.METRE).divide(TimeUnit.SECOND), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Meter pro Sekunde", "Meter pro Sekunde")),
					Map.entry(Language.ENGLISH, new StringArr2("meter per second", "meters per second"))
			), new StringArr2("metre per second", "metres per second"), "m/s", UnitSystem.METRIC
	),
	KILOMETRES_PER_HOUR(new UnitDimension(LengthUnit.KILOMETRE).divide(TimeUnit.HOUR), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Kilometer pro Stunde", "Kilometer pro Stunde")),
					Map.entry(Language.ENGLISH, new StringArr2("kilometer per hour", "kilometers per hour"))
			), new StringArr2("kilometre per hour", "kilometres per hour"), "km/h", UnitSystem.METRIC
	),
	MILES_PER_HOUR(new UnitDimension(LengthUnit.MILE).divide(TimeUnit.HOUR), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Meilen pro Stunde", "Meilen pro Stunde")),
					Map.entry(Language.ENGLISH, new StringArr2("miles per hour", "miles per hour"))
			), new StringArr2("mile per hour", "miles per hour"), Map.ofEntries(
					Map.entry(Language.GERMAN, "mph"),
					Map.entry(Language.ENGLISH, "mph")
			), "mph", UnitSystem.IMPERIAL
	),
	KNOT(new UnitDimension(LengthUnit.NAUTICAL_MILE).divide(TimeUnit.HOUR), Map.ofEntries(
					Map.entry(Language.GERMAN, new StringArr2("Knoten", "Knoten")),
					Map.entry(Language.ENGLISH, new StringArr2("knot", "knots"))
			), new StringArr2("knot", "knots"), Map.ofEntries(
					Map.entry(Language.GERMAN, "kn"),
					Map.entry(Language.ENGLISH, "kn")
			), "kn", UnitSystem.IMPERIAL
	);

	@NotNull private final Map<Language, StringArr2> localizedNames;

	@NotNull private final StringArr2 defaultName;

	@NotNull private final Map<Language, String> localizedAbbreviations;

	@NotNull private final String defaultAbbreviation;

	@NotNull private final UnitSystem unitSystem;

	@NotNull private final UnitDimension dimension;

	SpeedUnit(@NotNull UnitDimension dimension, @NotNull Map<Language, StringArr2> localizedNames, @NotNull StringArr2 defaultName, @NotNull String defaultAbbreviation, @NotNull UnitSystem unitSystem) {
		this.dimension = dimension;
		this.localizedNames = localizedNames;
		this.defaultName = defaultName;
		this.localizedAbbreviations = generateLocalizedAbbreviations();
		this.defaultAbbreviation = defaultAbbreviation;
		this.unitSystem = unitSystem;
	}

	SpeedUnit(@NotNull UnitDimension dimension, @NotNull Map<Language, StringArr2> localizedNames, @NotNull StringArr2 defaultName, @NotNull Map<Language, String> localizedAbbreviations, @NotNull String defaultAbbreviation, @NotNull UnitSystem unitSystem) {
		this.dimension = dimension;
		this.localizedNames = localizedNames;
		this.defaultName = defaultName;
		this.localizedAbbreviations = localizedAbbreviations;
		this.defaultAbbreviation = defaultAbbreviation;
		this.unitSystem = unitSystem;
	}

	@Override
	public @NotNull String getId() { return this.name(); }

	public static @Nullable SpeedUnit getUnit(@NotNull String unitStr) {
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