package io.github.akjo03.lib.swing.util.key;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public final class SwingKeyboard {
	public static void onKeyTyped(@NotNull JComponent component, @NotNull Consumer<KeyEvent> callback) {
		component.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onKeyPressed(@NotNull JComponent component, @NotNull Consumer<KeyEvent> callback) {
		component.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onKeyPressed(@NotNull JComponent component, @NotNull SwingKey key, @NotNull Consumer<KeyEvent> callback) {
		component.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == key.getCode()) callback.accept(e);
			}
		});
	}

	public static void onKeyReleased(@NotNull JComponent component, @NotNull Consumer<KeyEvent> callback) {
		component.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onKeyReleased(@NotNull JComponent component, @NotNull SwingKey key, @NotNull Consumer<KeyEvent> callback) {
		component.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == key.getCode()) callback.accept(e);
			}
		});
	}

	public static KeyStroke getKeyStroke(@NotNull SwingKey key) {
		return KeyStroke.getKeyStroke(key.getCode(), 0);
	}

	@SuppressWarnings("MagicConstant")
	public static KeyStroke getKeyStroke(@NotNull SwingKey key, @NotNull List<@NotNull SwingKeyModifier> modifiers) {
		return KeyStroke.getKeyStroke(key.getCode(), SwingKeyModifier.getModifier(modifiers));
	}
}