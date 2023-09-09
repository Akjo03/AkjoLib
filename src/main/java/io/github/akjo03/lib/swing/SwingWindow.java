package io.github.akjo03.lib.swing;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public final class SwingWindow {
	public static void onWindowOpened(@NotNull Frame frame, @NotNull Consumer<WindowEvent> callback) {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onWindowClosed(@NotNull Frame frame, @NotNull Consumer<WindowEvent> callback) {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onWindowMinimized(@NotNull Frame frame, @NotNull Consumer<WindowEvent> callback) {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowIconified(WindowEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onWindowMaximized(@NotNull Frame frame, @NotNull Consumer<WindowEvent> callback) {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeiconified(WindowEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onWindowFocusGained(@NotNull Frame frame, @NotNull Consumer<WindowEvent> callback) {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowGainedFocus(WindowEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onWindowFocusLost(@NotNull Frame frame, @NotNull Consumer<WindowEvent> callback) {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowLostFocus(WindowEvent e) {
				callback.accept(e);
			}
		});
	}
}