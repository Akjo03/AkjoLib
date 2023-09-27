package io.github.akjo03.lib.swing.component.input;

import io.github.akjo03.lib.swing.component.SwingComponentBuilder;

import java.awt.*;

@FunctionalInterface
@SuppressWarnings("unused")
public interface SwingInputComponentBuilder<T extends Component, C extends SwingInputComponent<T>> extends SwingComponentBuilder<T, C> {
	C build();
}