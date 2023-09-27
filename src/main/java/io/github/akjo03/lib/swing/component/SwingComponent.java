package io.github.akjo03.lib.swing.component;

import io.github.akjo03.lib.builder.Buildable;

import java.awt.*;

@FunctionalInterface
@SuppressWarnings("unused")
public interface SwingComponent<T extends Component> extends Buildable {
	T getComponent();
}