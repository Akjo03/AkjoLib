package io.github.akjo03.lib.swing.helper.mouse;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public enum SwingMouseButton {
	LEFT, RIGHT, MIDDLE, OTHER;

	public static void checkMouseEvent(MouseEvent event, SwingMouseButton button, Consumer<MouseEvent> callback) {
		if (SwingUtilities.isLeftMouseButton(event) && button == LEFT) callback.accept(event);
		else if (SwingUtilities.isRightMouseButton(event) && button == RIGHT) callback.accept(event);
		else if (SwingUtilities.isMiddleMouseButton(event) && button == MIDDLE) callback.accept(event);
		else if (button == OTHER) callback.accept(event);
	}
}