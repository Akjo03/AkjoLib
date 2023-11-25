package io.github.akjo03.lib.result;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Aggregates multiple {@link Result} objects and provides methods to process them collectively.
 */
@SuppressWarnings("unused")
public class ResultAggregator {
	private final List<Result<?>> results;

	@Contract(pure = true)
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
			return Optional.ofNullable(onEmpty.get())
					.map(Result::success)
					.orElseGet(() -> (Result<T>) Result.empty());
		}

		List<Throwable> throwables = collectErrors();

		return throwables.isEmpty() ? Result.success(onSuccess.get()) : Result.fail(new AggregatedException(throwables));
	}

	private @NotNull List<Throwable> collectErrors() {
		List<Throwable> throwables = new ArrayList<>();
		for (Result<?> result : results) {
			if (result.isError()) {
				throwables.add(result.getError());
			}
		}
		return throwables;
	}

	public Result<Object> aggregateFirst() {
		return aggregate(() -> null, () -> results.get(0).get());
	}

	public Result<Object> aggregateLast() {
		return aggregate(() -> null, () -> results.get(results.size() - 1).get());
	}

	public Result<Object> aggregateFor(Predicate<Result<?>> predicate) {
		return aggregate(
				() -> null,
				() -> results.stream()
						.filter(predicate)
						.findFirst()
						.map(Result::get)
						.orElse(null));
	}

	public <T> Result<List<T>> aggregateAll(Function<Result<?>, Result<T>> resultTransformer) {
		List<Throwable> throwables = new ArrayList<>();
		List<T> values = new ArrayList<>();

		for (Result<?> result : results) {
			if (result.isError()) {
				throwables.add(result.getError());
			} else {
				Result<T> transformedResult = resultTransformer.apply(result);
				if (transformedResult.isError()) {
					throwables.add(transformedResult.getError());
				} else {
					values.add(transformedResult.get());
				}
			}
		}

		return throwables.isEmpty() ? Result.success(values) : Result.fail(new AggregatedException(throwables));
	}

	@SuppressWarnings("unchecked")
	public <T> Result<List<T>> aggregateAll() {
		return aggregateAll(result -> (Result<T>) result);
	}

	public <T> Result<T> aggregateBut(T t) {
		return aggregate(() -> t, () -> t);
	}

	public boolean isAllSuccess() {
		return results.stream().allMatch(Result::isSuccess);
	}

	public boolean isAnyError() {
		return results.stream().anyMatch(Result::isError);
	}

	public Stream<Result<?>> asStream() {
		return results.stream();
	}
}