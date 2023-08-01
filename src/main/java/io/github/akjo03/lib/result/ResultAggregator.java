package io.github.akjo03.lib.result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
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

	public Result<Object> aggregateFirst() {
		return aggregate(() -> null, () -> results.get(0).get());
	}

	public Result<Object> aggregateLast() {
		return aggregate(() -> null, () -> results.get(results.size() - 1).get());
	}

	public Result<Object> aggregateFor(Predicate<Result<?>> predicate) {
		return aggregate(() -> null, () -> results.stream().filter(predicate).map(Result::get).findFirst().orElse(null));
	}

	public <T> Result<List<T>> aggregateAll(Function<Result<?>, T> resultTransformer) {
		List<Exception> exceptions = new ArrayList<>();
		List<T> values = new ArrayList<>();

		for (Result<?> result : results) {
			if (result.isError()) {
				exceptions.add(result.getError());
			} else {
				values.add(resultTransformer.apply(result));
			}
		}

		if (!exceptions.isEmpty()) {
			return Result.fail(new AggregatedException(exceptions));
		}

		return Result.success(values);
	}

	public <T> Result<T> aggregateBut(T t) {
		return aggregate(() -> t, () -> t);
	}
}