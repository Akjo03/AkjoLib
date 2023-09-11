package io.github.akjo03.lib.lang;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Locale;

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
}