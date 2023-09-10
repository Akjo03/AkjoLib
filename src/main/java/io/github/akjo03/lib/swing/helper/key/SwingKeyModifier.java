package io.github.akjo03.lib.swing.helper.key;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyEvent;
import java.util.List;

@SuppressWarnings("unused")
public enum SwingKeyModifier {
	ALT, CTRL, SHIFT, META, ALT_GRAPH;

	@Contract(pure = true)
	public static int getModifier(@NotNull List<@NotNull SwingKeyModifier> modifiers) {
		int modifier = 0;
		for (SwingKeyModifier mod : modifiers) {
			if (mod == ALT) modifier |= KeyEvent.ALT_DOWN_MASK;
			else if (mod == CTRL) modifier |= KeyEvent.CTRL_DOWN_MASK;
			else if (mod == SHIFT) modifier |= KeyEvent.SHIFT_DOWN_MASK;
			else if (mod == META) modifier |= KeyEvent.META_DOWN_MASK;
			else if (mod == ALT_GRAPH) modifier |= KeyEvent.ALT_GRAPH_DOWN_MASK;
		}
		return modifier;
	}
}