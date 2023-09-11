package io.github.akjo03.lib.swing.util.dialog;

import io.github.akjo03.lib.error.ErrorTemplate;
import io.github.akjo03.lib.functional.ThrowingRunnable;
import io.github.akjo03.lib.swing.SwingRunnable;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public final class SwingDialogs {
	public static void showInfoMessage(@NotNull SwingRunnable app, @NotNull String title, @NotNull String heading, @NotNull String message) {
		String output = "<html><body style=\"padding-left: 6px; padding-right: 6px;\">" +
				"<h1 style=\"margin-top: 0; margin-bottom: 1px;\">" + heading + "</h1>" +
				"<p style=\"margin-top: 12px; margin-bottom: 8px;\">" + message + "</p>" +
				"</body></html>";
		SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(app.getFrame(), output, title, JOptionPane.INFORMATION_MESSAGE));
	}

	public static void showWarningMessage(@NotNull SwingRunnable app, @NotNull String title, @NotNull String heading, @NotNull String message) {
		String output = "<html><body style=\"padding-left: 6px; padding-right: 6px;\">" +
				"<h2 style=\"margin-top: 0; margin-bottom: 1px;\">" + heading + "</h2>" +
				"<p style=\"margin-top: 12px; margin-bottom: 8px;\">" + message + "</p>" +
				"</body></html>";
		SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(app.getFrame(), output, title, JOptionPane.WARNING_MESSAGE));
	}

	public static void showErrorMessage(@NotNull SwingRunnable app, @NotNull String title, @NotNull String heading, @NotNull String message) {
		String output = "<html><body style=\"padding-left: 6px; padding-right: 6px;\">" +
				"<h2 style=\"margin-top: 0; margin-bottom: 1px;\">" + heading + "</h2>" +
				"<p style=\"margin-top: 12px; margin-bottom: 8px;\">" + message + "</p>" +
				"</body></html>";
		SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(app.getFrame(), output, title, JOptionPane.ERROR_MESSAGE));
	}

	@SuppressWarnings("MagicConstant")
	public static void showQuestion(@NotNull SwingRunnable app, @NotNull String title, @NotNull String question, @NotNull SwingDialogQuestionType type, @NotNull Consumer<SwingDialogQuestionResult> resultConsumer) {
		String output = "<html><body style=\"padding-left: 6px; padding-right: 6px;\">" +
				"<h3 style=\"margin-top: 0; margin-bottom: 1px;\">" + question + "</h3>" +
				"</body></html>";
		SwingUtilities.invokeLater(() -> {
			int dialogResult = JOptionPane.showConfirmDialog(app.getFrame(), output, title, type.getValue());
			SwingDialogQuestionResult result = SwingDialogQuestionResult.fromValue(dialogResult, type);
			resultConsumer.accept(result);
		});
	}

	public static void onError(@NotNull ThrowingRunnable<Exception> runnable, @NotNull Consumer<Exception> exceptionConsumer, @NotNull ErrorTemplate error, @NotNull Object... args) {
		try {
			runnable.run();
		} catch (Exception e) {
			StringBuilder messageBuilder = new StringBuilder();
			messageBuilder.append("<html><body style=\"padding-left: 6px; padding-right: 6px;\">");
			messageBuilder.append("<h2 style=\"margin-top: 0; margin-bottom: 1px;\">").append(error.getErrorTitle()).append("</h2>");
			messageBuilder.append("<p><strong>").append("Code ").append(error.getErrorCode()).append("</strong></p>");
			messageBuilder.append("<p style=\"margin-top: 12px; margin-bottom: 8px;\">").append(error.getErrorMessage(args)).append("</p>");
			messageBuilder.append("<hr style=\"margin-bottom: 8px;\"/>");
			messageBuilder.append("<p style=\"margin-bottom: 4px;\">").append("<strong>Error Type: </strong>").append(e.getClass().getSimpleName()).append("</p>");
			messageBuilder.append("<p style=\"margin-bottom: 4px;\">").append("<strong>Error Details: </strong>").append(e.getMessage()).append("</p>");
			messageBuilder.append("</body></html>");

			SwingUtilities.invokeLater(() -> {
				JOptionPane.showMessageDialog(null, messageBuilder.toString(), "An error occurred!", JOptionPane.ERROR_MESSAGE);
				exceptionConsumer.accept(e);
			});
		}
	}
}