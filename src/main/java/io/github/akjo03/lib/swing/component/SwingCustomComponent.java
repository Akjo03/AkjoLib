package io.github.akjo03.lib.swing.component;

import javax.swing.*;

@FunctionalInterface
@SuppressWarnings("unused")
public interface SwingCustomComponent<T extends JComponent> {
	T getComponent();
}