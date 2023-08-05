package io.github.akjo03.lib.result;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ResultAggregator {
	private final List<Result<?>> results;

	public ResultAggregator() {
		this.results = new ArrayList<>();
	}

	public ResultAggregator add(Result<?> result) {
		results.add(result);
		return this;
	}

	@SuppressWarnings("unchecked")
	private <T> Result<T> aggregate(Supplier<T> onEmpty, Supplier<T> onSuccess) {
		if (results.isEmpty()) {
			T t = onEmpty.get();
			return t == null ? (Result<T>) Result.empty() : Result.success(t);
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

	public <T> Result<List<T>> aggregateAll(Function<Result<?>, Result<T>> resultTransformer) {
		List<Exception> exceptions = new ArrayList<>();
		List<T> values = new ArrayList<>();

		for (Result<?> result : results) {
			if (result.isError()) {
				exceptions.add(result.getError());
			} else {
				Result<T> transformedResult = resultTransformer.apply(result);
				if (transformedResult.isError()) {
					exceptions.add(transformedResult.getError());
				} else {
					values.add(transformedResult.get());
				}
			}
		}

		if (!exceptions.isEmpty()) {
			return Result.fail(new AggregatedException(exceptions));
		}

		return Result.success(values);
	}

	@SuppressWarnings("unchecked")
	public <T> Result<List<T>> aggregateAll() {
		return aggregateAll(result -> (Result<T>) result);
	}

	public <T> Result<T> aggregateBut(T t) {
		return aggregate(() -> t, () -> t);
	}
}