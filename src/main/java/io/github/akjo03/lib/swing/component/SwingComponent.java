package io.github.akjo03.lib.swing.component;

import io.github.akjo03.lib.builder.Buildable;

@FunctionalInterface
@SuppressWarnings("unused")
public interface SwingComponent<T> extends Buildable {
	T getComponent();
}