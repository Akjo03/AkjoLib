package io.github.akjo03.lib.swing;

import io.github.akjo03.lib.builder.Buildable;
import io.github.akjo03.lib.error.ErrorTemplate;
import io.github.akjo03.lib.lang.Language;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
@SuppressWarnings({"unused", "ClassCanBeRecord"})
public class SwingLaunchArguments implements Buildable {
	private final String appName;
	private final Language language;
	private final String languageBundle;
	private final Runnable onLaunch;
	private final Runnable onClosing;
	private final Consumer<Integer> onStartupError;
	private final Consumer<Exception> onGenericError;
	private final ErrorTemplate startupErrorTemplate;
	private final ErrorTemplate genericErrorTemplate;
	private final Object[] startupErrorArgs;
}