package io.github.akjo03.lib.swing.component.input;

import io.github.akjo03.lib.swing.component.SwingComponent;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class SwingInputCollection implements SwingComponent<JPanel> {
	private final Map<String, SwingInputComponent<?>> inputs = new LinkedHashMap<>();

	public SwingInputCollection() {}

	public SwingInputCollection(@NotNull Map<String, SwingInputComponent<?>> inputs) {
		this.inputs.putAll(inputs);
	}

	@Override
	public JPanel getComponent() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(3, 3, 3, 3);

		inputs.forEach((id, input) -> {
			panel.add(new JLabel(input.getName()), constraints);
			constraints.gridx++;
			panel.add(input.getComponent(), constraints);
			constraints.gridx = 0;
			constraints.gridy++;
		});

		return panel;
	}

	public void consume(@NotNull Consumer<Map<String, String>> consumer) {
		Map<String, String> values = new HashMap<>();
		inputs.forEach((id, input) -> values.put(id, input.getValue()));
		consumer.accept(values);
	}
}