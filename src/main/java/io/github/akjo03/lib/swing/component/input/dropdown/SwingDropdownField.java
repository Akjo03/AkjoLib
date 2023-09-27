package io.github.akjo03.lib.swing.component.input.dropdown;

import io.github.akjo03.lib.swing.component.input.SwingInputComponent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings("unused")
public class SwingDropdownField implements SwingInputComponent<JComboBox<String>> {
	@Getter private final String name;
	@Getter private final Map<String, String> options;
	private String displayName;

	private JComboBox<String> component;

	@Override
	public JComboBox<String> getComponent() {
		JComboBox<String> component = new JComboBox<>();
		component.setName(name);
		options.forEach((display, value) -> component.addItem(display));
		component.setSelectedItem(displayName);
		component.addActionListener(e -> displayName = (String) component.getSelectedItem());

		this.component = component;
		return component;
	}

	@Override
	public String getValue() {
		return options.get(displayName);
	}

	public void setValue(String value) {
		this.displayName = value;
		if (component != null) component.setSelectedItem(value);
	}
}