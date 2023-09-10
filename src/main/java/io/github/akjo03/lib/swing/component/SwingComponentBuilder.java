package io.github.akjo03.lib.swing.component;

import io.github.akjo03.lib.builder.Builder;

@FunctionalInterface
@SuppressWarnings("unused")
public interface SwingComponentBuilder<T, C extends SwingComponent<T>> extends Builder<C> {
	C build();
}