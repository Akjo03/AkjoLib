package io.github.akjo03.lib.swing;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public final class SwingMouse {
	public static void onMouseClicked(@NotNull Frame frame, @NotNull Consumer<MouseEvent> callback) {
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onMouseClicked(@NotNull Frame frame, @NotNull SwingMouseButton button, @NotNull Consumer<MouseEvent> callback) {
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingMouseButton.checkMouseEvent(e, button, callback);
			}
		});
	}

	public static void onMousePressed(@NotNull Frame frame, @NotNull Consumer<MouseEvent> callback) {
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onMousePressed(@NotNull Frame frame, @NotNull SwingMouseButton button, @NotNull Consumer<MouseEvent> callback) {
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				SwingMouseButton.checkMouseEvent(e, button, callback);
			}
		});
	}

	public static void onMouseReleased(@NotNull Frame frame, @NotNull Consumer<MouseEvent> callback) {
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onMouseReleased(@NotNull Frame frame, @NotNull SwingMouseButton button, @NotNull Consumer<MouseEvent> callback) {
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				SwingMouseButton.checkMouseEvent(e, button, callback);
			}
		});
	}

	public static void onMouseEntered(@NotNull Frame frame, @NotNull Consumer<MouseEvent> callback) {
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onMouseExited(@NotNull Frame frame, @NotNull Consumer<MouseEvent> callback) {
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onMouseDragged(@NotNull Frame frame, @NotNull Consumer<MouseEvent> callback) {
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onMouseMoved(@NotNull Frame frame, @NotNull Consumer<MouseEvent> callback) {
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				callback.accept(e);
			}
		});
	}
}