package io.github.akjo03.lib.swing.component.input.text;

import io.github.akjo03.lib.swing.component.input.SwingInputComponentBuilder;
import lombok.NoArgsConstructor;

import javax.swing.*;

@NoArgsConstructor
@SuppressWarnings("unused")
public class SwingTextFieldBuilder implements SwingInputComponentBuilder<JFormattedTextField, SwingTextField> {
	private String name = "";
	private String defaultValue = "";

	public SwingTextFieldBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public SwingTextFieldBuilder setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
		return this;
	}

	@Override
	public SwingTextField build() {
		SwingTextField textField = new SwingTextField(name);
		textField.setValue(defaultValue);
		return textField;
	}
}