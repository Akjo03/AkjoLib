package io.github.akjo03.lib.swing.component.input.dropdown;

import io.github.akjo03.lib.swing.component.input.SwingInputComponentBuilder;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@SuppressWarnings("unused")
public class SwingDropdownFieldBuilder implements SwingInputComponentBuilder<JComboBox<String>, SwingDropdownField> {
	private String name = "";
	private Map<String, String> options = new HashMap<>();
	private String defaultValue;

	public SwingDropdownFieldBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public SwingDropdownFieldBuilder setOptions(Map<String, String> options) {
		this.options = options;
		return this;
	}

	public SwingDropdownFieldBuilder setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
		return this;
	}

	@Override
	public SwingDropdownField build() {
		SwingDropdownField dropdownField = new SwingDropdownField(name, options);
		dropdownField.setValue(defaultValue);
		return dropdownField;
	}
}