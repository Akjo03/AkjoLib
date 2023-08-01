package io.github.akjo03.lib.result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ResultAggregator {
	private final List<Result<?>> results;

	public ResultAggregator() {
		this.results = new CopyOnWriteArrayList<>();
	}

	public ResultAggregator add(Result<?> result) {
		results.add(result);
		return this;
	}

	private <T> Result<T> aggregate(Supplier<T> onEmpty, Supplier<T> onSuccess) {
		if (results.isEmpty()) {
			return Result.success(onEmpty.get());
		}

		List<Exception> exceptions = new ArrayList<>();
		for (Result<?> result : results) {
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

	public Result<?> aggregateFirst() {
		return aggregate(() -> null, () -> results.get(0).get());
	}

	public Result<?> aggregateLast() {
		return aggregate(() -> null, () -> results.get(results.size() - 1).get());
	}

	public Result<?> aggregateFor(Predicate<Result<?>> predicate) {
		return aggregate(() -> null, () -> {
			for (Result<?> result : results) {
				if (predicate.test(result)) {
					return result.get();
				}
			}
			return null;
		});
	}

	public Result<?> aggregateAll() {
		return aggregate(() -> null, () -> results.stream()
				.filter(Result::isSuccess)
				.map(Result::get)
				.toArray()
		);
	}

	public <T> Result<T> aggregateBut(T t) {
		return aggregate(() -> t, () -> t);
	}
}