package io.github.akjo03.lib.result;

import io.github.akjo03.lib.validation.ValidatorGroup;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Aggregates multiple {@link Result} objects and provides methods to process them collectively.
 */
@SuppressWarnings("unused")
public class ResultAggregator {
	private static final String DEFAULT_REPORT_TITLE = "Aggregated Exception Report";

	private final List<Result<?>> results;

	@Contract(pure = true)
	public ResultAggregator() {
		this.results = new ArrayList<>();
	}

	public ResultAggregator add(Result<?> result) {
		results.add(result);
		return this;
	}

	public static <T> @NotNull ResultAggregator of(List<Result<T>> results) {
		ResultAggregator aggregator = new ResultAggregator();
		aggregator.results.addAll(results);
		return aggregator;
	}

	public static <T> @NotNull ResultAggregator of(@NotNull List<T> values, @NotNull ValidatorGroup<T> validatorGroup) {
		ResultAggregator aggregator = new ResultAggregator();
		values.stream()
				.map(validatorGroup::validate)
				.forEach(aggregator::add);
		return aggregator;
	}

	private <T> Result<T> aggregate(Supplier<T> onEmpty, Supplier<T> onSuccess, String reportTitle) {
		if (results.isEmpty()) {
			return Optional.ofNullable(onEmpty.get())
					.map(Result::success)
					.orElseGet(Result::empty);
		}

		List<Throwable> throwables = results.stream()
				.filter(Result::isError)
				.map(Result::getError)
				.toList();

		return throwables.isEmpty()
				? Result.success(onSuccess.get())
				: Result.fail(new AggregatedException(throwables, reportTitle));
	}

	private <T> Result<T> aggregate(Supplier<T> onEmpty, Supplier<T> onSuccess) {
		return aggregate(onEmpty, onSuccess, DEFAULT_REPORT_TITLE);
	}

	public Result<Object> aggregateFirst(String reportTitle) {
		return aggregate(() -> null, () -> results.get(0).get(), reportTitle);
	}

	public Result<Object> aggregateFirst() {
		return aggregateFirst(DEFAULT_REPORT_TITLE);
	}

	public Result<Object> aggregateLast(String reportTitle) {
		return aggregate(() -> null, () -> results.get(results.size() - 1).get(), reportTitle);
	}

	public Result<Object> aggregateLast() {
		return aggregateLast(DEFAULT_REPORT_TITLE);
	}

	public Result<Object> aggregateFor(Predicate<Result<?>> predicate, String reportTitle) {
		return aggregate(
				() -> null,
				() -> results.stream()
						.filter(predicate)
						.findFirst()
						.map(Result::get)
						.orElse(null),
				reportTitle
		);
	}

	public Result<Object> aggregateFor(Predicate<Result<?>> predicate) {
		return aggregateFor(predicate, DEFAULT_REPORT_TITLE);
	}

	public <T> Result<List<T>> aggregateAll(Function<Result<?>, Result<T>> resultTransformer, String reportTitle) {
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

		return throwables.isEmpty()
				? Result.success(values)
				: Result.fail(new AggregatedException(throwables, reportTitle));
	}

	public <T> Result<List<T>> aggregateAll(Function<Result<?>, Result<T>> resultTransformer) {
		return aggregateAll(resultTransformer, DEFAULT_REPORT_TITLE);
	}

	public void aggregateAll(Consumer<List<Result<?>>> validConsumer, Consumer<List<Throwable>> invalidConsumer) {
		List<Throwable> throwables = new ArrayList<>();
		List<Result<?>> values = new ArrayList<>();

		for (Result<?> result : results) {
			if (result.isError()) {
				throwables.add(result.getError());
			} else {
				values.add(result);
			}
		}

		if (throwables.isEmpty()) {
			validConsumer.accept(values);
		} else {
			invalidConsumer.accept(throwables);
		}
	}

	public <T> Result<List<T>> aggregateAll(Class<T> type) {
		return aggregateAll(result -> result.map(type::cast));
	}

	public Result<List<Object>> aggregateAll() {
		return aggregateAll(Object.class);
	}

	public <T> Result<T> aggregateBut(T t, String reportTitle) {
		return aggregate(() -> t, () -> t, reportTitle);
	}

	public <T> Result<T> aggregateBut(T t) {
		return aggregateBut(t, DEFAULT_REPORT_TITLE);
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