package io.github.akjo03.lib.swing.component.menu;

import io.github.akjo03.lib.swing.component.menu.item.SwingMenuItem;
import io.github.akjo03.lib.swing.helper.mouse.SwingMouse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.List;
import java.util.function.Consumer;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SuppressWarnings("unused")
public class SwingMenu implements SwingMenuComponent<JMenu> {
	@NotNull private final String name;
	@NotNull private final String description;
	private final int mnemonic;
	private final Icon icon;
	@NotNull private final List<@NotNull SwingMenuItem> items;

	@NotNull private final Consumer<SwingMenuItem> onSelectionChange;

	@Override
	public JMenu getComponent() {
		JMenu component = new JMenu(name);
		component.setToolTipText(description);
		component.setMnemonic(mnemonic);
		if (icon != null) component.setIcon(icon);
		items.forEach(item -> {
			JMenuItem menuItem = item.getComponent();
			component.add(menuItem);
			SwingMouse.onMouseEntered(menuItem, e -> onSelectionChange.accept(item));
		});
		return component;
	}
}