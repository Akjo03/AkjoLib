package io.github.akjo03.lib.swing.helper.menu;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

@SuppressWarnings("unused")
public abstract class MenuAdapter implements MenuListener {
	@Override
	public void menuSelected(MenuEvent e) {}

	@Override
	public void menuDeselected(MenuEvent e) {}

	@Override
	public void menuCanceled(MenuEvent e) {}
}