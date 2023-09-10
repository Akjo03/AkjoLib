package io.github.akjo03.lib.swing.component.menu.item;

import io.github.akjo03.lib.swing.component.menu.SwingMenuComponent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SuppressWarnings("unused")
public class SwingMenuItem implements SwingMenuComponent<JMenuItem> {
	@NotNull private final String name;
	@NotNull private final String description;
	private final int mnemonic;
	private final KeyStroke keyStroke;
	private final Icon icon;

	@NotNull private final Runnable onClick;

	@Override
	public JMenuItem getComponent() {
		JMenuItem item = new JMenuItem(name);
		item.setToolTipText(description);
		item.setMnemonic(mnemonic);
		item.setAccelerator(keyStroke);
		item.addActionListener(e -> onClick.run());
		if (icon != null) item.setIcon(icon);
		return item;
	}
}