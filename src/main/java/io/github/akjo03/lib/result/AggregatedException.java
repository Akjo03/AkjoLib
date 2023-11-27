package io.github.akjo03.lib.result;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@SuppressWarnings("unused")
public class AggregatedException extends RuntimeException {
	private final List<Throwable> throwables;
	@Setter private String reportTitle = "Aggregated Exception Report";

	public AggregatedException(List<Throwable> throwables, String reportTitle) {
		this.throwables = unwrap(throwables);
		this.reportTitle = reportTitle;
	}

	private @NotNull List<Throwable> unwrap(@NotNull List<Throwable> oldExceptions) {
		List<Throwable> newThrowables = new ArrayList<>();
		for (Throwable e : oldExceptions) {
			if (e instanceof AggregatedException) {
				newThrowables.addAll(unwrap(((AggregatedException) e).getThrowables()));
			} else {
				newThrowables.add(e);
			}
		}
		return newThrowables;
	}

	@Override
	public String getMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append(reportTitle).append(" (").append(throwables.size()).append(" exception(s)):\n");
		for (int i = 0; i < throwables.size(); i++) {
			Throwable t = throwables.get(i);
			builder.append("\tException ").append(i+1).append(" (").append(t.getClass().getSimpleName()).append("): ").append(t.getMessage()).append("\n");
		}
		return builder.toString();
	}

	public static void print(Throwable e, String reportTitle) {
		if (e instanceof AggregatedException) {
			System.err.println(e.getMessage());
		} else {
			System.err.println(reportTitle + " (1 exception):\n\tException 1 (" + e.getClass().getSimpleName() + "): " + e.getMessage());
		}
	}
}