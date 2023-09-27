package io.github.akjo03.lib.swing.component.input;

import io.github.akjo03.lib.swing.component.SwingComponent;

import java.awt.*;

@SuppressWarnings("unused")
public interface SwingInputComponent<T extends Component> extends SwingComponent<T> {
	String getName();
	String getValue();
	void setValue(String value);
}