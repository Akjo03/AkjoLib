package io.github.akjo03.lib.swing.helper.dialog;

import io.github.akjo03.lib.error.ErrorTemplate;
import io.github.akjo03.lib.functional.ThrowingRunnable;

import javax.swing.*;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public final class SwingDialog {
	public static void onError(ThrowingRunnable<Exception> runnable, Consumer<Exception> exceptionConsumer, ErrorTemplate error, Object... args) {
		try {
			runnable.run();
		} catch (Exception e) {
			StringBuilder messageBuilder = new StringBuilder();
			messageBuilder.append("<html><body style=\"padding-left: 6px; padding-right: 6px;\">");
			messageBuilder.append("<h2 style=\"font-size: 2em; margin-top: 0; margin-bottom: 1px;\">").append(error.getErrorTitle()).append("</h2>");
			messageBuilder.append("<p style=\"font-size: 1.25em;\"><strong>").append("Code ").append(error.getErrorCode()).append("</strong></p>");
			messageBuilder.append("<p style=\"font-size: 1.05em; margin-top: 12px; margin-bottom: 8px;\">").append(error.getErrorMessage(args)).append("</p>");
			messageBuilder.append("<hr style=\"margin-bottom: 8px;\"/>");
			messageBuilder.append("<p style=\"font-size: 1.05em; margin-bottom: 4px;\">").append("<strong>Error Type: </strong>").append(e.getClass().getSimpleName()).append("</p>");
			messageBuilder.append("<p style=\"font-size: 1.05em; margin-bottom: 4px;\">").append("<strong>Error Details: </strong>").append(e.getMessage()).append("</p>");
			messageBuilder.append("</body></html>");

			SwingUtilities.invokeLater(() -> {
				JOptionPane.showMessageDialog(null, messageBuilder.toString(), "An error occurred!", JOptionPane.ERROR_MESSAGE);
				exceptionConsumer.accept(e);
			});
		}
	}
}