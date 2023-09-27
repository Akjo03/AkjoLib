package io.github.akjo03.lib.swing.util.document;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("unused")
public abstract class DocumentAdapter implements DocumentListener {
	@Override
	public void insertUpdate(DocumentEvent e) {
		onChange(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		onChange(e);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		onChange(e);
	}

	public abstract void onChange(DocumentEvent e);
}