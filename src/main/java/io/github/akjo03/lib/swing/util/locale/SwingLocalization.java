package io.github.akjo03.lib.swing.util.locale;

import io.github.akjo03.lib.lang.Language;
import io.github.akjo03.lib.swing.SwingRunnable;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@SuppressWarnings("unused")
public final class SwingLocalization {
	private static final String BUNDLE_NAME = "Swing";
	private static ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, Language.ENGLISH.getLocale());

	public static void setLocale(@NotNull SwingRunnable app, @NotNull Language language) {
		Locale.setDefault(language.getLocale());
		app.getFrame().setLocale(language.getLocale());
		try {
			ResourceBundle.clearCache();
			bundle = ResourceBundle.getBundle(BUNDLE_NAME, language.getLocale());
		} catch (Exception ignored) {}

		UIManager.put("OptionPane.yesButtonText", bundle.getString("general.yes"));
		UIManager.put("OptionPane.noButtonText", bundle.getString("general.no"));
		UIManager.put("OptionPane.cancelButtonText", bundle.getString("general.cancel"));
		UIManager.put("OptionPane.okButtonText", bundle.getString("general.ok"));
	}

	public static @NotNull String getLabel(@NotNull SwingRunnable app, @NotNull String key) {
		String appBundleName = app.getLanguageBundle();
		Locale appLocale = app.getFrame().getLocale();
		ResourceBundle appBundle = null;

		if (appBundleName != null) {
			try {
				appBundle = ResourceBundle.getBundle(appBundleName, appLocale);
			} catch (MissingResourceException ignored) {}
		}

		if (appBundle != null) {
			try {
				return appBundle.getString(key);
			} catch (MissingResourceException ignored) {}
		}

		if (appBundleName != null) {
			try {
				appBundle = ResourceBundle.getBundle(appBundleName, Language.ENGLISH.getLocale());
				return appBundle.getString(key);
			} catch (MissingResourceException ignored) {}
		}

		try {
			bundle = ResourceBundle.getBundle(BUNDLE_NAME, appLocale);
			return bundle.getString(key);
		} catch (MissingResourceException ignored) {}

		try {
			bundle = ResourceBundle.getBundle(BUNDLE_NAME, Language.ENGLISH.getLocale());
			return bundle.getString(key);
		} catch (MissingResourceException ignored) {}

		return "!" + key + "!";
	}
}