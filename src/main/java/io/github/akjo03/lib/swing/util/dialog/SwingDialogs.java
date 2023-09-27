package io.github.akjo03.lib.swing.util.dialog;

import io.github.akjo03.lib.error.ErrorTemplate;
import io.github.akjo03.lib.functional.ThrowingRunnable;
import io.github.akjo03.lib.swing.SwingRunnable;
import io.github.akjo03.lib.swing.component.input.SwingInputCollection;
import io.github.akjo03.lib.swing.util.locale.SwingLocalization;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public final class SwingDialogs {
	public static void showInfoMessage(@Nullable SwingRunnable app, @NotNull String title, @NotNull String heading, @NotNull String message) {
		String output = "<html><body style=\"padding-left: 6px; padding-right: 6px;\">" +
				"<h1 style=\"margin-top: 0; margin-bottom: 1px;\">" + heading + "</h1>" +
				"<p style=\"margin-top: 12px; margin-bottom: 8px;\">" + message + "</p>" +
				"</body></html>";
		SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
				app != null ? app.getFrame() : null,
				output,
				title,
				JOptionPane.INFORMATION_MESSAGE
		));
	}

	public static void showWarningMessage(@Nullable SwingRunnable app, @NotNull String title, @NotNull String heading, @NotNull String message) {
		String output = "<html><body style=\"padding-left: 6px; padding-right: 6px;\">" +
				"<h2 style=\"margin-top: 0; margin-bottom: 1px;\">" + heading + "</h2>" +
				"<p style=\"margin-top: 12px; margin-bottom: 8px;\">" + message + "</p>" +
				"</body></html>";
		SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
				app != null ? app.getFrame() : null,
				output,
				title,
				JOptionPane.WARNING_MESSAGE
		));
	}

	public static void showErrorMessage(@Nullable SwingRunnable app, @NotNull String title, @NotNull String heading, @NotNull String message) {
		String output = "<html><body style=\"padding-left: 6px; padding-right: 6px;\">" +
				"<h2 style=\"margin-top: 0; margin-bottom: 1px;\">" + heading + "</h2>" +
				"<p style=\"margin-top: 12px; margin-bottom: 8px;\">" + message + "</p>" +
				"</body></html>";
		SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
				app != null ? app.getFrame() : null,
				output,
				title,
				JOptionPane.ERROR_MESSAGE
		));
	}

	@SuppressWarnings("MagicConstant")
	public static void showQuestion(@Nullable SwingRunnable app, @NotNull String title, @NotNull String question, @NotNull SwingDialogQuestionType type, @NotNull Consumer<SwingDialogQuestionResult> resultConsumer) {
		String output = "<html><body style=\"padding-left: 6px; padding-right: 6px;\">" +
				"<h3 style=\"margin-top: 0; margin-bottom: 1px;\">" + question + "</h3>" +
				"</body></html>";
		SwingUtilities.invokeLater(() -> {
			SwingDialogQuestionResult result = SwingDialogQuestionResult.fromValue(JOptionPane.showConfirmDialog(
					app != null ? app.getFrame() : null,
					output,
					title,
					type.getValue()
			), type);
			resultConsumer.accept(result);
		});
	}

	public static void showQuestion(@Nullable SwingRunnable app, @NotNull String title, @NotNull String question, @NotNull SwingInputCollection inputs, @NotNull Consumer<Map<String, String>> resultConsumer) {
		SwingUtilities.invokeLater(() -> {
			int dialogResult = JOptionPane.showOptionDialog(
					app != null ? app.getFrame() : null,
					inputs.getComponent(),
					title,
					JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					new String[] {
							app != null ? SwingLocalization.getLabel(app, "general.submit") : "Submit",
							app != null ? SwingLocalization.getLabel(app, "general.cancel") : "Cancel"
					}, app != null ? SwingLocalization.getLabel(app, "general.submit") : "Submit"
			);

			if (dialogResult == JOptionPane.OK_OPTION) inputs.consume(resultConsumer);
		});
	}

	public static void onError(@Nullable SwingRunnable app, @NotNull ThrowingRunnable<Exception> runnable, @NotNull Consumer<Exception> exceptionConsumer, @NotNull ErrorTemplate error, @NotNull Object... args) {
		try {
			runnable.run();
		} catch (Exception e) {
			StringBuilder messageBuilder = new StringBuilder();
			messageBuilder.append("<html><body style=\"padding-left: 6px; padding-right: 6px;\">");
			messageBuilder.append("<h2 style=\"margin-top: 0; margin-bottom: 3px;\">").append(error.getErrorTitle()).append("</h2>");
			messageBuilder.append("<p><strong>").append(SwingLocalization.getLabel(app, "dialog.error.code")).append(" ").append(error.getErrorCode()).append("</strong></p>");
			messageBuilder.append("<p style=\"margin-top: 12px; margin-bottom: 8px;\">").append(error.getErrorMessage(args)).append("</p>");
			messageBuilder.append("<hr style=\"margin-bottom: 8px;\"/>");
			messageBuilder.append("<p style=\"margin-bottom: 4px;\">").append("<strong>").append(SwingLocalization.getLabel(app, "dialog.error.type")).append(" </strong>").append(e.getClass().getSimpleName()).append("</p>");
			messageBuilder.append("<p style=\"margin-bottom: 4px;\">").append("<strong>").append(SwingLocalization.getLabel(app, "dialog.error.details")).append(" </strong>").append(e.getMessage()).append("</p>");
			messageBuilder.append("</body></html>");

			SwingUtilities.invokeLater(() -> {
				JOptionPane.showMessageDialog(
						app != null ? app.getFrame() : null,
						messageBuilder.toString(),
						SwingLocalization.getLabel(app, "dialog.error.title"),
						JOptionPane.ERROR_MESSAGE
				);
				exceptionConsumer.accept(e);
			});
		}
	}
}