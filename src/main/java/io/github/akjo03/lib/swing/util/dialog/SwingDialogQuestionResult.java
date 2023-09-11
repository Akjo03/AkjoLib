package io.github.akjo03.lib.swing.util.dialog;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

@RequiredArgsConstructor
@SuppressWarnings("unused")
public enum SwingDialogQuestionResult {
	OK(JOptionPane.OK_OPTION),
	YES(JOptionPane.YES_OPTION),
	NO(JOptionPane.NO_OPTION),
	CANCEL(JOptionPane.CANCEL_OPTION),
	CLOSE(JOptionPane.CLOSED_OPTION);

	private final int value;

	public static SwingDialogQuestionResult fromValue(int value, @NotNull SwingDialogQuestionType type) {
		return switch (type) {
			case YES_NO -> switch (value) {
				case JOptionPane.YES_OPTION -> YES;
				case JOptionPane.NO_OPTION -> NO;
				default -> CLOSE;
			};
			case YES_NO_CANCEL -> switch (value) {
				case JOptionPane.YES_OPTION -> YES;
				case JOptionPane.NO_OPTION -> NO;
				case JOptionPane.CANCEL_OPTION -> CANCEL;
				default -> CLOSE;
			};
			case OK_CANCEL -> switch (value) {
				case JOptionPane.OK_OPTION -> OK;
				case JOptionPane.CANCEL_OPTION -> CANCEL;
				default -> CLOSE;
			};
		};
	}
}