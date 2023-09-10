package io.github.akjo03.lib.swing;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import io.github.akjo03.lib.functional.Initializable;
import io.github.akjo03.lib.swing.helper.dialog.SwingDialog;
import io.github.akjo03.lib.swing.helper.window.SwingWindow;
import lombok.AccessLevel;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.WindowEvent;

@SuppressWarnings("unused")
public abstract class SwingApp extends JFrame implements Initializable, SwingRunnable {
	@Setter(AccessLevel.PROTECTED) private String appName = "Untitled App";
	@Setter(AccessLevel.PROTECTED) private Runnable onClosing = () -> {};

	public static void launch(
			@NotNull SwingApp app,
			@NotNull SwingLaunchArguments arguments
	) {
		SwingDialog.onError(() -> {
			FlatOneDarkIJTheme.setup();
			FlatLaf.updateUI();

			app.setAppName(arguments.getAppName());
			app.setOnClosing(arguments.getOnClosing());
			app.initialize();
		}, (e) -> arguments.getOnStartupError().accept(arguments.getStartupErrorTemplate().getErrorCode()), arguments.getStartupErrorTemplate(), arguments.getStartupErrorArgs());

		SwingUtilities.invokeLater(() -> SwingDialog.onError(() -> {
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

	protected void preInit() {}
	protected void postInit() {}

	@Override
	public void run() {}

	public void close() { dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); }
}