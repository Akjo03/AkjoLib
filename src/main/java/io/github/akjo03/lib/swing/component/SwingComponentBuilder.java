package io.github.akjo03.lib.swing.component;

import io.github.akjo03.lib.builder.Builder;

import java.awt.*;

@FunctionalInterface
@SuppressWarnings("unused")
public interface SwingComponentBuilder<T extends Component, C extends SwingComponent<T>> extends Builder<C> {
	C build();
}