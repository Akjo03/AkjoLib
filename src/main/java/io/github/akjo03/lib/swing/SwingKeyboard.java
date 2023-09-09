package io.github.akjo03.lib.swing;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public final class SwingKeyboard {
	public static void onKeyTyped(@NotNull Frame frame, @NotNull Consumer<KeyEvent> callback) {
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onKeyPressed(@NotNull Frame frame, @NotNull Consumer<KeyEvent> callback) {
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onKeyPressed(@NotNull Frame frame, @NotNull SwingKey key, @NotNull Consumer<KeyEvent> callback) {
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == key.getCode()) callback.accept(e);
			}
		});
	}

	public static void onKeyReleased(@NotNull Frame frame, @NotNull Consumer<KeyEvent> callback) {
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onKeyReleased(@NotNull Frame frame, @NotNull SwingKey key, @NotNull Consumer<KeyEvent> callback) {
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == key.getCode()) callback.accept(e);
			}
		});
	}
}