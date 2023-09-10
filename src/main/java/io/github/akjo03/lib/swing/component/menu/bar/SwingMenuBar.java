package io.github.akjo03.lib.swing.component.menu.bar;

import io.github.akjo03.lib.swing.component.menu.SwingMenu;
import io.github.akjo03.lib.swing.component.menu.SwingMenuComponent;
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
public class SwingMenuBar implements SwingMenuComponent<JMenuBar> {
	@NotNull private final List<@NotNull SwingMenu> menus;

	@NotNull private final Consumer<SwingMenu> onSelectionChange;

	@Override
	public JMenuBar getComponent() {
		JMenuBar component = new JMenuBar();
		menus.forEach(menu -> component.add(menu.getComponent()));
		component.getSelectionModel().addChangeListener(e -> onSelectionChange.accept(menus.get(component.getSelectionModel().getSelectedIndex())));
		return component;
	}
}