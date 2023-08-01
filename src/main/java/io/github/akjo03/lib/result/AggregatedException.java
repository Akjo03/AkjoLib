package io.github.akjo03.lib.result;

import lombok.Getter;

import java.util.List;

@Getter
public class AggregatedException extends RuntimeException {
	private final List<Exception> exceptions;

	public AggregatedException(List<Exception> exceptions) {
		this.exceptions = exceptions;
	}

	@Override
	public String getMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append("Aggregated Exception Report (").append(exceptions.size()).append(" exceptions):\n");
		for (int i = 0; i < exceptions.size(); i++) {
			Exception e = exceptions.get(i);
			builder.append("Exception ").append(i).append("(").append(e.getClass().getSimpleName()).append("): ").append(e.getMessage()).append("\n");
		}
		builder.append("--- End of Report ---");
		return builder.toString();
	}

	@Override
	public String toString() {
		return getMessage();
	}
}