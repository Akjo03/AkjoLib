package io.github.akjo03.lib.lang;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
@SuppressWarnings("unused")
public enum Language {
	ENGLISH("English", new Locale("en")),
	GERMAN("Deutsch", new Locale("de")),
	SPANISH("Español", new Locale("es")),
	PORTUGUESE("Português", new Locale("pt")),
	FRENCH("Français", new Locale("fr")),
	ITALIAN("Italiano", new Locale("it")),
	RUSSIAN("Русский", new Locale("ru"));

	private final String name;
	private final Locale locale;

	public String getCode() { return locale.getLanguage(); }

	public static @Unmodifiable @NotNull List<Language> getLanguages() {
		return List.of(Language.values());
	}

	public static @Unmodifiable @NotNull List<String> getLanguageNames() {
		return Stream.of(Language.values())
				.map(Language::getName).toList();
	}

	public static @Unmodifiable @NotNull List<String> getLanguageCodes() {
		return Stream.of(Language.values())
				.map(Language::getCode).toList();
	}

	public static @Unmodifiable @NotNull Map<String, String> getLanguageMap() {
		return IntStream.range(0, values().length).boxed()
				.collect(Collectors.toMap(
						getLanguageNames()::get,
						getLanguageCodes()::get
				));
	}

	public static @NotNull Language getLanguage(Locale locale) {
		return Stream.of(Language.values())
				.filter(language -> language.getLocale().equals(locale))
				.findFirst().orElse(Stream.of(Language.values())
						.filter(language -> language.getLocale().equals(Locale.getDefault()))
						.findFirst().orElse(ENGLISH));
	}

	public static @NotNull Language getLanguage(String code) {
		return Stream.of(Language.values())
				.filter(language -> language.getCode().equals(code))
				.findFirst().orElse(Stream.of(Language.values())
						.filter(language -> language.getLocale().equals(Locale.getDefault()))
						.findFirst().orElse(ENGLISH));
	}
}