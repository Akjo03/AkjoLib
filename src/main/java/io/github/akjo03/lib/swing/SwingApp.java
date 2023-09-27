package io.github.akjo03.lib.swing;

import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import io.github.akjo03.lib.functional.Initializable;
import io.github.akjo03.lib.lang.Language;
import io.github.akjo03.lib.swing.util.dialog.SwingDialogs;
import io.github.akjo03.lib.swing.util.locale.SwingLocalization;
import io.github.akjo03.lib.swing.util.window.SwingWindow;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.WindowEvent;

@SuppressWarnings("unused")
public abstract class SwingApp extends JFrame implements Initializable, SwingRunnable {
	@Setter(AccessLevel.PROTECTED) @Getter private String appName = "Untitled App";
	@Setter(AccessLevel.PROTECTED) @Getter private Language language = Language.ENGLISH;
	@Setter(AccessLevel.PROTECTED) @Getter private String languageBundle = null;
	@Setter(AccessLevel.PROTECTED) private Runnable onClosing = () -> {};

	public static void launch(
			@NotNull SwingApp app,
			@NotNull SwingLaunchArguments arguments
	) {
		SwingDialogs.onError(null, () -> {
			SwingLocalization.setLocale(app, arguments.getLanguage());
			FlatOneDarkIJTheme.setup();

			app.setAppName(arguments.getAppName());
			app.setLanguage(arguments.getLanguage());
			app.setLanguageBundle(arguments.getLanguageBundle());
			app.setOnClosing(arguments.getOnClosing());
			app.initialize();
		}, (e) -> arguments.getOnStartupError().accept(
				arguments.getStartupErrorTemplate().getErrorStatus()),
				arguments.getStartupErrorTemplate(),
				arguments.getStartupErrorArgs()
		);

		SwingUtilities.invokeLater(() -> SwingDialogs.onError(app, () -> {
			arguments.getOnLaunch().run();
			app.run();
		}, arguments.getOnGenericError(), arguments.getGenericErrorTemplate()));
	}

	@Override
	public void initialize() throws Exception {
		preInit();
		SwingWindow.initialize(this, appName);
		SwingWindow.onWindowClosing(this, (event) -> onClosing.run());
		postInit();
	}

	@Override
	public void updateLanguage(Language language) throws Exception {
		SwingLocalization.setLocale(this, language);
		setLanguage(language);
		initialize();
	}

	protected void preInit() {}
	protected void postInit() {}

	@Override
	public void run() {}

	public void close() { dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); }

	@Override
	public JFrame getFrame() { return this; }
}