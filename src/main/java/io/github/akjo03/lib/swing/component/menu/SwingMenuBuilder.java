package io.github.akjo03.lib.swing.component.menu;

import io.github.akjo03.lib.swing.component.menu.item.SwingMenuItem;
import io.github.akjo03.lib.swing.util.key.SwingKey;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@NoArgsConstructor
@SuppressWarnings("unused")
public class SwingMenuBuilder implements SwingMenuComponentBuilder<JMenu, SwingMenu> {
	@NotNull private String name = "Untitled";
	@NotNull private String description = "";
	private int mnemonic = -1;
	private Icon icon = null;
	@NotNull private final List<@NotNull SwingMenuItem> items = new ArrayList<>();

	@NotNull private Consumer<SwingMenuItem> onSelectionChange = item -> {};

	public SwingMenuBuilder setName(@NotNull String name) {
		this.name = name;
		return this;
	}

	public SwingMenuBuilder setDescription(@NotNull String description) {
		this.description = description;
		return this;
	}

	public SwingMenuBuilder setMnemonic(@NotNull SwingKey key) {
		this.mnemonic = key.getCode();
		return this;
	}

	public SwingMenuBuilder setIcon(@NotNull String iconPath) {
		URL iconResource = ClassLoader.getSystemClassLoader().getResource(iconPath);
		if (iconResource != null) this.icon = new ImageIcon(
				new ImageIcon(iconResource).getImage().getScaledInstance(12, 12, 0)
		);
		return this;
	}

	public SwingMenuBuilder addItem(@NotNull SwingMenuItem item) {
		items.add(item);
		return this;
	}

	public SwingMenuBuilder onSelectionChange(@NotNull Consumer<SwingMenuItem> onSelectionChange) {
		this.onSelectionChange = onSelectionChange;
		return this;
	}

	@Override
	public SwingMenu build() {
		return new SwingMenu(name, description, mnemonic, icon, items, onSelectionChange);
	}
}