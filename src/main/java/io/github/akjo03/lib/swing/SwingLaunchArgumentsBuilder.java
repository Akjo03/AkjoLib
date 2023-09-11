package io.github.akjo03.lib.swing;

import io.github.akjo03.lib.builder.Builder;
import io.github.akjo03.lib.error.ErrorTemplate;
import io.github.akjo03.lib.lang.Language;

import java.util.function.Consumer;

@SuppressWarnings("unused")
public class SwingLaunchArgumentsBuilder implements Builder<SwingLaunchArguments> {
	private String appName = "Untitled App";
	private Language language = Language.ENGLISH;
	private String languageBundle = null;
	private Runnable onLaunch = () -> {};
	private Runnable onClosing = () -> {};
	private Consumer<Integer> onStartupError = (e) -> {};
	private Consumer<Exception> onGenericError = (e) -> {};
	private ErrorTemplate startupErrorTemplate = new ErrorTemplate(
			1, "Startup Error",
			"An error occurred while launching the application."
	);
	private ErrorTemplate genericErrorTemplate = new ErrorTemplate(
			0, "Generic Error",
			"An error occurred while running the application."
	);
	private Object[] startupErrorArgs;

	public SwingLaunchArgumentsBuilder setAppName(String appName) {
		this.appName = appName;
		return this;
	}

	public SwingLaunchArgumentsBuilder setLanguage(Language language) {
		this.language = language;
		return this;
	}

	public SwingLaunchArgumentsBuilder setLanguageBundle(String languageBundle) {
		this.languageBundle = languageBundle;
		return this;
	}

	public SwingLaunchArgumentsBuilder onLaunch(Runnable onLaunch) {
		this.onLaunch = onLaunch;
		return this;
	}

	public SwingLaunchArgumentsBuilder onClosing(Runnable onClosing) {
		this.onClosing = onClosing;
		return this;
	}

	public SwingLaunchArgumentsBuilder onStartupError(Consumer<Integer> onStartupError) {
		this.onStartupError = onStartupError;
		return this;
	}

	public SwingLaunchArgumentsBuilder onGenericError(Consumer<Exception> onGenericError) {
		this.onGenericError = onGenericError;
		return this;
	}

	public SwingLaunchArgumentsBuilder setStartupErrorTemplate(ErrorTemplate startupErrorTemplate) {
		this.startupErrorTemplate = startupErrorTemplate;
		return this;
	}

	public SwingLaunchArgumentsBuilder setGenericErrorTemplate(ErrorTemplate genericErrorTemplate) {
		this.genericErrorTemplate = genericErrorTemplate;
		return this;
	}

	public SwingLaunchArgumentsBuilder setStartupErrorArgs(Object... startupErrorArgs) {
		this.startupErrorArgs = startupErrorArgs;
		return this;
	}

	@Override
	public SwingLaunchArguments build() {
		return new SwingLaunchArguments(
				appName,
				language,
				languageBundle,
				onLaunch,
				onClosing,
				onStartupError,
				onGenericError,
				startupErrorTemplate,
				genericErrorTemplate,
				startupErrorArgs
		);
	}
}