package io.github.akjo03.lib.swing.component.menu.item;

import io.github.akjo03.lib.swing.component.menu.SwingMenuComponentBuilder;
import io.github.akjo03.lib.swing.util.key.SwingKey;
import io.github.akjo03.lib.swing.util.key.SwingKeyModifier;
import io.github.akjo03.lib.swing.util.key.SwingKeyboard;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.net.URL;
import java.util.List;

@NoArgsConstructor
@SuppressWarnings("unused")
public class SwingMenuItemBuilder implements SwingMenuComponentBuilder<JMenuItem, SwingMenuItem> {
	@NotNull private String name = "";
	@NotNull private String description = "";
	private int mnemonic = -1;
	private KeyStroke keyStroke = null;
	private Icon icon = null;

	@NotNull private Runnable onClick = () -> {};

	public SwingMenuItemBuilder setName(@NotNull String name) {
		this.name = name;
		return this;
	}

	public SwingMenuItemBuilder setDescription(@NotNull String description) {
		this.description = description;
		return this;
	}

	public SwingMenuItemBuilder setMnemonic(@NotNull SwingKey key) {
		this.mnemonic = key.getCode();
		return this;
	}

	public SwingMenuItemBuilder setKeyStroke(@NotNull SwingKey key) {
		this.keyStroke = SwingKeyboard.getKeyStroke(key);
		return this;
	}

	public SwingMenuItemBuilder setKeyStroke(@NotNull SwingKey key, @NotNull List<@NotNull SwingKeyModifier> modifiers) {
		this.keyStroke = SwingKeyboard.getKeyStroke(key, modifiers);
		return this;
	}

	public SwingMenuItemBuilder setIcon(@NotNull String iconPath) {
		URL iconResource = ClassLoader.getSystemClassLoader().getResource(iconPath);
		if (iconResource != null) this.icon = new ImageIcon(
				new ImageIcon(iconResource).getImage().getScaledInstance(12, 12, 0)
		);
		return this;
	}

	public SwingMenuItemBuilder onClick(@NotNull Runnable onClick) {
		this.onClick = onClick;
		return this;
	}

	@Override
	public SwingMenuItem build() {
		return new SwingMenuItem(name, description, mnemonic, keyStroke, icon, onClick);
	}
}