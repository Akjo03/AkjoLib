package io.github.akjo03.lib.swing.component.menu.bar;

import io.github.akjo03.lib.swing.component.menu.SwingMenu;
import io.github.akjo03.lib.swing.component.menu.SwingMenuComponentBuilder;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@NoArgsConstructor
@SuppressWarnings("unused")
public class SwingMenuBarBuilder implements SwingMenuComponentBuilder<JMenuBar, SwingMenuBar> {
	@NotNull private final List<@NotNull SwingMenu> menus = new ArrayList<>();

	@NotNull private Consumer<SwingMenu> onSelectionChange = e -> {};

	public SwingMenuBarBuilder addMenu(@NotNull SwingMenu menu) {
		menus.add(menu);
		return this;
	}

	public SwingMenuBarBuilder onSelectionChange(@NotNull Consumer<SwingMenu> onSelectionChange) {
		this.onSelectionChange = onSelectionChange;
		return this;
	}

	@Override
	public SwingMenuBar build() {
		return new SwingMenuBar(menus, onSelectionChange);
	}
}