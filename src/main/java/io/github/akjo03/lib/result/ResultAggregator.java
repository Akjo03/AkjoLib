package io.github.akjo03.lib.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ResultAggregator<T> {
	private final List<Result<T>> results;

	public ResultAggregator() {
		this.results = new ArrayList<>();
	}

	public ResultAggregator<T> add(Result<T> result) {
		results.add(result);
		return this;
	}

	private Result<T> aggregate(Supplier<T> onEmpty, Supplier<T> onSuccess) {
		if (results.isEmpty()) {
			return Result.success(onEmpty.get());
		}

		List<Exception> exceptions = new ArrayList<>();
		for (Result<T> result : results) {
			if (result.isError()) {
				exceptions.add(result.getError());
			}
		}

		if (exceptions.isEmpty()) {
			return Result.success(onSuccess.get());
		} else {
			return Result.fail(new AggregatedException(exceptions));
		}
	}

	public Result<T> aggregateFirst() {
		return aggregate(() -> null, () -> results.get(0).get());
	}

	public Result<T> aggregateLast() {
		return aggregate(() -> null, () -> results.get(results.size() - 1).get());
	}

	public Result<T> aggregateFor(Predicate<Result<T>> predicate) {
		return aggregate(() -> null, () -> {
			for (Result<T> result : results) {
				if (predicate.test(result)) {
					return result.get();
				}
			}
			return null;
		});
	}

	public Result<List<T>> aggregateAll() {
		if (results.isEmpty()) {
			return Result.success(Collections.emptyList());
		}

		List<T> values = new ArrayList<>();
		List<Exception> exceptions = new ArrayList<>();

		for (Result<T> result : results) {
			if (result.isError()) {
				exceptions.add(result.getError());
			} else {
				values.add(result.get());
			}
		}

		if (exceptions.isEmpty()) {
			return Result.success(values);
		} else {
			return Result.fail(new AggregatedException(exceptions));
		}
	}

	public Result<List<T>> aggregateAllFor(Predicate<Result<T>> predicate) {
		if (results.isEmpty()) {
			return Result.success(Collections.emptyList());
		}

		List<T> values = new ArrayList<>();
		List<Exception> exceptions = new ArrayList<>();

		for (Result<T> result : results) {
			if (result.isError()) {
				exceptions.add(result.getError());
			} else if (predicate.test(result)) {
				values.add(result.get());
			}
		}

		if (exceptions.isEmpty()) {
			return Result.success(values);
		} else {
			return Result.fail(new AggregatedException(exceptions));
		}
	}

	public Result<T> aggregateUsing(BinaryOperator<T> aggregator) {
		if (results.isEmpty()) {
			return Result.success(null);
		}

		List<Exception> exceptions = new ArrayList<>();
		T value = null;
		for (Result<T> result : results) {
			if (result.isError()) {
				exceptions.add(result.getError());
			} else {
				value = (value == null) ? result.get() : aggregator.apply(value, result.get());
			}
		}

		if (exceptions.isEmpty()) {
			return Result.success(value);
		} else {
			return Result.fail(new AggregatedException(exceptions));
		}
	}
}