package io.github.akjo03.lib.swing.component.input.text;

import io.github.akjo03.lib.swing.component.input.SwingInputComponent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings("unused")
public class SwingTextField implements SwingInputComponent<JFormattedTextField> {
	@Getter private final String name;
	@Getter private String value = "";

	private JTextField component;

	@Override
	public JFormattedTextField getComponent() {
		JFormattedTextField component = new JFormattedTextField();
		component.setName(name);
		component.setText(value);
		component.addPropertyChangeListener("value", event -> value = component.getText());

		this.component = component;
		return component;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
		if (component != null) { component.setText(value); }
	}
}