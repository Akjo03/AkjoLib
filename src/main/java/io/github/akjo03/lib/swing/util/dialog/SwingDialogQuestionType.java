package io.github.akjo03.lib.swing.util.dialog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;

@RequiredArgsConstructor
@Getter
@SuppressWarnings("unused")
public enum SwingDialogQuestionType {
	OK_CANCEL(JOptionPane.OK_CANCEL_OPTION),
	YES_NO(JOptionPane.YES_NO_OPTION),
	YES_NO_CANCEL(JOptionPane.YES_NO_CANCEL_OPTION);

	private final int value;
}