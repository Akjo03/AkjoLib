package io.github.akjo03.lib.result;

import io.github.akjo03.lib.annotation.PotentiallyDangerous;
import io.github.akjo03.lib.functional.ThrowingSupplier;
import io.github.akjo03.lib.validation.Validator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * A generic container class for results of operations, which can be a success, a failure with a throwable,
 * or an empty result. Inspired by the Result pattern in other programming languages.
 * Credit goes to <a href="https://github.com/samwho/result">samwho/result</a>.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings({"unused", "UnusedReturnValue"})
public final class Result<S> implements Supplier<S>, Serializable {
	private final Throwable throwable;
	private final S successValue;
	private final boolean isEmpty;

	@Contract("_ -> new")
	public static <S> @NotNull Result<S> success(S s) {
		return new Result<>(null, Objects.requireNonNull(s), false);
	}

	@Contract("_ -> new")
	public static <S> @NotNull Result<S> fail(Throwable e) {
		return new Result<>(Objects.requireNonNull(e), null, false);
	}

	@Contract(value = " -> new", pure = true)
	public static @NotNull Result<Void> empty() {
		return new Result<>(null, null, true);
	}

	@Contract("_ -> new")
	public static <S> @NotNull Result<S> empty(Class<S> type) {
		return new Result<>(null, null, true);
	}

	public static <S> Result<S> from(@NotNull ThrowingSupplier<S, ? extends Throwable> s) {
		Objects.requireNonNull(s);
		try {
			return success(s.get());
		} catch (Throwable t) {
			return fail(t);
		}
	}

	@Contract("_ -> new")
	@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
	public static <S> @NotNull Result<S> fromOptional(@NotNull Optional<S> optional) {
		Objects.requireNonNull(optional);
		return optional.map(Result::success).orElseGet(() -> new Result<>(null, null, true));
	}

	public static <S> Result<S> flattenOptional(@NotNull Result<Optional<S>> result) {
		if (result.isError()) return Result.fail(result.getError());
		if (result.get().isEmpty()) return Result.fail(new NoSuchElementException("Optional is empty"));
		return Result.success(result.get().get());
	}

	public static <S> CompletableFuture<Result<S>> fromFuture(@NotNull CompletableFuture<S> future) {
		return future.thenApply(Result::success)
				.exceptionally(Result::fail);
	}

	@Contract(pure = true)
	public boolean isSuccess() {
		return isEmpty || successValue != null;
	}

	@Contract(pure = true)
	public boolean isError() {
		return throwable != null;
	}

	@Contract(pure = true)
	public boolean isEmpty() {
		return isEmpty;
	}

	@PotentiallyDangerous
	public Throwable getError() throws NoSuchElementException {
		if (!isError()) { throw new NoSuchElementException("Attempted to retrieve error on non-erroneous result"); }
		return throwable;
	}

	@Override
	@PotentiallyDangerous
	public S get() throws NoSuchElementException {
		if (isError()) { throw new NoSuchElementException("Attempted to retrieve value on erroneous result"); }
		return successValue;
	}

	@Contract(pure = true)
	public S getOrElse(S other) {
		if (isError()) { return other; }
		return successValue;
	}

	public S getOrApply(@NotNull Function<Throwable, S> function) {
		if (isError()) { return function.apply(throwable); }
		return successValue;
	}

	@Contract(pure = true)
	public @Nullable S getOrNull() {
		if (isError()) { return null; }
		return successValue;
	}

	@Contract(pure = true)
	public S getOrThrow() throws Throwable {
		if (isError()) { throw throwable; }
		return successValue;
	}

	public <T extends Throwable> S orElseThrow(@NotNull Supplier<T> throwableSupplier) throws T {
		if (isError()) { throw throwableSupplier.get(); }
		return successValue;
	}

	public <T extends Throwable> S orElseThrow(@NotNull Function<Throwable, T> throwableFunction) throws T {
		if (isError()) { throw throwableFunction.apply(throwable); }
		return successValue;
	}


	@SuppressWarnings("unchecked")
	public <N> Result<N> map(@NotNull Function<S, N> f) {
		if (isError()) { return (Result<N>) this; }
		return Result.from(() -> f.apply(successValue));
	}

	@SuppressWarnings("unchecked")
	public <N> Result<N> flatMap(@NotNull Function<S, Result<N>> f) {
		if (isError()) { return (Result<N>) this; }
		return f.apply(successValue);
	}

	public Result<S> mapError(@NotNull Function<Throwable, Throwable> f) {
		if (isError()) { return Result.fail(f.apply(throwable)); }
		return this;
	}

	public <N> @NotNull Result<N> transform(@NotNull Function<S, N> successFunction, @NotNull Function<Throwable, N> errorFunction) {
		if (isError()) {
			return Result.success(errorFunction.apply(throwable));
		}
		return Result.success(successFunction.apply(successValue));
	}

	public Result<S> onErrorTransform(@NotNull BiFunction<S, Throwable, S> transformFunction) {
		if (isError()) {
			return Result.success(transformFunction.apply(successValue, throwable));
		}
		return this;
	}

	public Result<S> recover(@NotNull Function<Throwable, S> recoverFunction) {
		if (isError()) { return Result.success(recoverFunction.apply(throwable)); }
		return this;
	}

	public static <S, T, R> Result<R> combine(@NotNull Result<S> first, @NotNull Result<T> second, @NotNull BiFunction<S, T, R> combiner) {
		if (first.isError()) return Result.fail(first.getError());
		if (second.isError()) return Result.fail(second.getError());
		return Result.success(combiner.apply(first.get(), second.get()));
	}

	public <T> T fold(@NotNull Function<S, T> successFunction, @NotNull Function<Throwable, T> errorFunction) {
		return isSuccess() ? successFunction.apply(successValue) : errorFunction.apply(throwable);
	}

	public Result<S> filter(@NotNull Predicate<S> predicate, @NotNull Supplier<Throwable> exceptionSupplier) {
		if (isSuccess() && !predicate.test(successValue)) {
			return Result.fail(exceptionSupplier.get());
		}
		return this;
	}

	@Contract("_ -> this")
	public Result<S> ifSuccess(@NotNull Consumer<S> consumer) {
		if (isSuccess()) { consumer.accept(successValue); }
		return this;
	}

	@Contract("_, _ -> this")
	public Result<S> ifSuccessAnd(@NotNull Predicate<S> condition, @NotNull Consumer<S> consumer) {
		if (isSuccess() && condition.test(successValue)) {
			consumer.accept(successValue);
		}
		return this;
	}

	@Contract("_ -> this")
	public Result<S> ifError(@NotNull Consumer<Throwable> consumer) {
		if (isError()) consumer.accept(throwable);
		return this;
	}

	@Contract("_, _ -> this")
	public <T extends Exception> Result<S> ifError(@NotNull Class<T> type, @NotNull Consumer<T> consumer) {
		if (isError() && type.isInstance(throwable)) {
			consumer.accept(type.cast(throwable));
		}
		return this;
	}

	@Contract("_, _ -> this")
	public Result<S> ifErrorAnd(@NotNull Predicate<Throwable> condition, @NotNull Consumer<Throwable> consumer) {
		if (isError() && condition.test(throwable)) {
			consumer.accept(throwable);
		}
		return this;
	}

	@Contract("_, _ -> this")
	public Result<S> ifPresent(@NotNull Consumer<S> success, @NotNull Consumer<Throwable> error) {
		if (isSuccess()) { success.accept(successValue); }
		else error.accept(throwable);
		return this;
	}

	@Contract("_, _, _ -> this")
	public Result<S> ifPresentAnd(@NotNull Predicate<S> successCondition, @NotNull Consumer<S> success, @NotNull Consumer<Throwable> error) {
		if (isSuccess() && successCondition.test(successValue)) { success.accept(successValue); }
		else error.accept(throwable);
		return this;
	}

	@Contract("_ -> this")
	public Result<S> ifEmpty(@NotNull Runnable runnable) {
		if (isEmpty()) { runnable.run(); }
		return this;
	}

	@Contract("_, _ -> this")
	public Result<S> ifEmptyAnd(@NotNull Predicate<Result<S>> condition, @NotNull Runnable runnable) {
		if (isEmpty() && condition.test(this)) { runnable.run(); }
		return this;
	}

	@Contract("_ -> this")
	public Result<S> ifNotEmpty(@NotNull Consumer<S> consumer) {
		if (!isEmpty()) { consumer.accept(successValue); }
		return this;
	}

	@Contract("_, _ -> this")
	public Result<S> ifNotEmptyAnd(@NotNull Predicate<S> condition, @NotNull Consumer<S> consumer) {
		if (!isEmpty() && condition.test(successValue)) { consumer.accept(successValue); }
		return this;
	}

	public Result<S> wrapError(@NotNull BiFunction<String, Throwable, Throwable> f, @NotNull String message) {
		if (isError()) { return Result.fail(f.apply(message, throwable)); }
		return this;
	}

	public Result<S> wrapError(@NotNull Function<Throwable, Throwable> f) {
		return mapError(f);
	}

	@Contract(pure = true)
	public Optional<S> asOptional() {
		return Optional.ofNullable(successValue);
	}

	public Stream<S> asStream() {
		if (isSuccess()) { return Stream.of(successValue); }
		return Stream.empty();
	}

	public Stream<Throwable> errorAsStream() {
		if (isError()) { return Stream.of(throwable); }
		return Stream.empty();
	}

	public Result<S> validate(@NotNull Validator<S> validator) {
		if (isSuccess()) { return validator.validate(successValue); }
		return this;
	}

	public Result<S> validate(@NotNull Validator<S> validator, @NotNull Consumer<Throwable> errorConsumer) {
		if (isSuccess()) { return validator.validate(successValue); }
		errorConsumer.accept(throwable);
		return this;
	}

	public Result<S> validateIf(@NotNull Predicate<S> predicate, @NotNull Validator<S> validator) {
		if (isSuccess() && predicate.test(successValue)) { return validator.validate(successValue); }
		return this;
	}

	public Result<S> validateIf(@NotNull Predicate<S> predicate, @NotNull Validator<S> validator, @NotNull Consumer<Throwable> errorConsumer) {
		if (isSuccess() && predicate.test(successValue)) { return validator.validate(successValue); }
		errorConsumer.accept(throwable);
		return this;
	}

	@Override
	@Contract(value = "null -> false", pure = true)
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Result<?> result = (Result<?>) o;

		boolean successValueEquals = Objects.equals(successValue, result.successValue);
		boolean throwableEquals = Objects.equals(throwable, result.throwable);
		boolean isEmptyEquals = isEmpty == result.isEmpty;

		return successValueEquals && throwableEquals && isEmptyEquals;
	}

	@Override
	public int hashCode() {
		return Objects.hash(throwable, successValue, isEmpty);
	}

	@Override
	public @NotNull String toString() {
		if (isSuccess()) {
			return "Result.Success(" + successValue + ")";
		} else if (isError()) {
			return "Result.Error(" + throwable + ")";
		} else {
			return "Result.Empty()";
		}
	}
}