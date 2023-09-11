package io.github.akjo03.lib.swing;

import io.github.akjo03.lib.functional.ThrowingRunnable;
import io.github.akjo03.lib.lang.Language;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

@SuppressWarnings("unused")
public interface SwingRunnable extends ThrowingRunnable<Exception> {
	String getAppName();
	Language getLanguage();
	void updateLanguage(Language language) throws Exception;
	@Nullable String getLanguageBundle();
	JFrame getFrame();
	void close();
}