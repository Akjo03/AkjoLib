package io.github.akjo03.lib.swing.component.menu;

import io.github.akjo03.lib.swing.component.SwingComponentBuilder;

import javax.swing.*;

@FunctionalInterface
@SuppressWarnings("unused")
public interface SwingMenuComponentBuilder<T extends MenuElement, C extends SwingMenuComponent<T>> extends SwingComponentBuilder<T, C> {
	C build();
}