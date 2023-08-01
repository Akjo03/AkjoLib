package io.github.akjo03.lib.result;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
public class AggregatedException extends RuntimeException {
	private final List<Exception> exceptions;

	public AggregatedException(List<Exception> exceptions) {
		this.exceptions = unwrap(exceptions);
	}

	private @NotNull List<Exception> unwrap(@NotNull List<Exception> oldExceptions) {
		List<Exception> newExceptions = new java.util.ArrayList<>();
		for (Exception e : oldExceptions) {
			if (e instanceof AggregatedException) {
				newExceptions.addAll(unwrap(((AggregatedException) e).getExceptions()));
			} else {
				newExceptions.add(e);
			}
		}
		return newExceptions;
	}

	public String getMessage(String reportTitle) {
		StringBuilder builder = new StringBuilder();
		builder.append(reportTitle).append(" (").append(exceptions.size()).append(" exception/-s):\n");
		for (int i = 0; i < exceptions.size(); i++) {
			Exception e = exceptions.get(i);
			builder.append("\tException ").append(i+1).append(" (").append(e.getClass().getSimpleName()).append("): ").append(e.getMessage()).append("\n");
		}
		builder.append("--- End of Report ---");
		return builder.toString();
	}

	@Override
	public String toString() {
		return getMessage("Aggregated Exception Report");
	}
}