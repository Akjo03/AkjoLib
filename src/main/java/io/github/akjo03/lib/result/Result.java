package io.github.akjo03.lib.result;

import io.github.akjo03.lib.functional.ThrowingSupplier;
import io.github.akjo03.lib.validation.Validator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.*;
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

	/**
	 * Creates a successful result with the given value.
	 * @param s The value to wrap in a successful result.
	 * @param <S> The type of the value.
	 * @return A successful result with the given value.
	 */
	@Contract("_ -> new")
	public static <S> @NotNull Result<S> success(S s) {
		return new Result<>(null, Objects.requireNonNull(s), false);
	}

	/**
	 * Creates an erroneous result with the given throwable.
	 * @param e The throwable to wrap in an erroneous result.
	 * @param <S> The type of the value.
	 * @return An erroneous result with the given throwable.
	 */
	@Contract("_ -> new")
	public static <S> @NotNull Result<S> fail(Throwable e) {
		return new Result<>(Objects.requireNonNull(e), null, false);
	}

	/**
	 * Creates an empty result.
	 * @param <S> The type of the value.
	 * @return An empty result.
	 */
	@Contract(value = " -> new", pure = true)
	public static <S> @NotNull Result<S> empty() {
		return new Result<>(null, null, true);
	}

	/**
	 * Creates an empty result with the given type.
	 * @param type The class for the given type of value.
	 * @param <S> The type of the value.
	 * @return An empty result with the given type.
	 */
	@Contract("_ -> new")
	public static <S> @NotNull Result<S> empty(Class<S> type) {
		return new Result<>(null, null, true);
	}

	/**
	 * Creates a result from the given supplier. If the supplier throws an exception, an erroneous result is returned.
	 * @param s The supplier to wrap in a result.
	 * @param <S> The type of the value.
	 * @return A result with the value from the given supplier.
	 */
	public static <S> Result<S> from(@NotNull ThrowingSupplier<S, ? extends Throwable> s) {
		Objects.requireNonNull(s);
		try {
			return success(s.get());
		} catch (Throwable t) {
			return fail(t);
		}
	}

	/**
	 * Creates a result from the given optional. If the optional is empty, an empty result is returned.
	 * @param optional The optional to wrap in a result.
	 * @param <S> The type of the value.
	 * @return A result with the value from the given optional.
	 */
	@Contract("_ -> new")
	@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
	public static <S> @NotNull Result<S> fromOptional(@NotNull Optional<S> optional) {
		Objects.requireNonNull(optional);
		return optional
				.map(Result::success)
				.orElseGet(() -> new Result<>(null, null, true));
	}

	/**
	 * Flattens a result of an optional into a result of the value.
	 * @param result The result of an optional to flatten.
	 * @param <S> The type of the value.
	 * @return A result of the value of the optional.
	 */
	public static <S> Result<S> flattenOptional(@NotNull Result<Optional<S>> result) {
		if (result.isError()) return Result.fail(result.getError());
		if (result.get().isEmpty()) return Result.fail(new NoSuchElementException("Optional is empty"));
		return Result.success(result.get().get());
	}

	/**
	 * Creates a result from the given future. If the future throws an exception, an erroneous result is returned.
	 * @param future The future to wrap in a result.
	 * @param <S> The type of the value.
	 * @return A result with the value from the given future.
	 */
	public static <S> CompletableFuture<Result<S>> fromFuture(@NotNull CompletableFuture<S> future) {
		return future.thenApply(Result::success)
				.exceptionally(Result::fail);
	}

	/**
	 * @return Whether the result is successful.
	 */
	@Contract(pure = true)
	public boolean isSuccess() {
		return isEmpty || successValue != null;
	}

	/**
	 * @return Whether the result is erroneous.
	 */
	@Contract(pure = true)
	public boolean isError() {
		return throwable != null;
	}

	/**
	 * @return Whether the result is empty.
	 */
	@Contract(pure = true)
	public boolean isEmpty() {
		return isEmpty;
	}

	/**
	 * @return Returns the value if the result is successful.
	 * @apiNote This method is potentially <strong>unsafe</strong>! Use {@link #isSuccess()} to check if the result is successful.
	 * @throws NoSuchElementException If the result is not successful.
	 */
	@Override
	public S get() throws NoSuchElementException {
		if (isError()) { throw new NoSuchElementException("Attempted to retrieve value on erroneous result"); }
		return successValue;
	}

	/**
	 * @return Returns the error if the result is erroneous.
	 * @apiNote This method is potentially <strong>unsafe</strong>! Use {@link #isError()} to check if the result is erroneous.
	 * @throws NoSuchElementException If the result is not erroneous.
	 */
	public Throwable getError() throws NoSuchElementException {
		if (!isError()) { throw new NoSuchElementException("Attempted to retrieve error on non-erroneous result"); }
		return throwable;
	}

	/**
	 * @param other The value to return if the result is erroneous.
	 * @return Returns the value if the result is successful, otherwise returns the given value.
	 */
	@Contract(pure = true)
	public S getOrElse(S other) {
		if (isError()) { return other; }
		return successValue;
	}

	/**
	 * @param function The function to apply to the error if the result is erroneous.
	 * @return Returns the value if the result is successful, otherwise returns the result of the given function.
	 */
	public S getOrApply(@NotNull Function<Throwable, S> function) {
		if (isError()) { return function.apply(throwable); }
		return successValue;
	}

	/**
	 * @return Returns the value if the result is successful, otherwise returns null.
	 */
	@Contract(pure = true)
	public @Nullable S getOrNull() {
		if (isError()) { return null; }
		return successValue;
	}

	/**
	 * @return Returns the value if the result is successful, otherwise throws the error.
	 * @throws Throwable If the result is erroneous.
	 */
	@Contract(pure = true)
	public S getOrThrow() throws Throwable {
		if (isError()) { throw throwable; }
		return successValue;
	}

	/**
	 * @param throwableSupplier The supplier to use to throw an exception if the result is erroneous.
	 * @param <T> The type of the exception to throw.
	 * @return Returns the value if the result is successful, otherwise throws the exception from the given supplier.
	 * @throws T If the result is erroneous.
	 */
	public <T extends Throwable> S orElseThrow(@NotNull Supplier<T> throwableSupplier) throws T {
		if (isError()) { throw throwableSupplier.get(); }
		return successValue;
	}

	/**
	 * @param throwableFunction The function to use to throw an exception if the result is erroneous. The throwable is passed as an argument.
	 * @param <T> The type of the exception to throw.
	 * @return Returns the value if the result is successful, otherwise throws the exception from the given function.
	 * @throws T If the result is erroneous.
	 */
	public <T extends Throwable> S orElseThrow(@NotNull Function<Throwable, T> throwableFunction) throws T {
		if (isError()) { throw throwableFunction.apply(throwable); }
		return successValue;
	}

	/**
	 * @param throwableSupplier The supplier to use to throw an exception if the result is erroneous.
	 * @param <T> The type of the exception to throw.
	 * @return Returns this result if the result is successful, otherwise throws the exception from the given supplier.
	 * @throws T If the result is erroneous.
	 */
	@Contract("_ -> this")
	public <T extends Throwable> Result<S> onErrorThrow(@NotNull Supplier<T> throwableSupplier) throws T {
		if (isError()) { throw throwableSupplier.get(); }
		return this;
	}

	/**
	 * @param throwableFunction The function to use to throw an exception if the result is erroneous. The throwable is passed as an argument.
	 * @param <T> The type of the exception to throw.
	 * @return Returns this result if the result is successful, otherwise throws the exception from the given function.
	 * @throws T If the result is erroneous.
	 */
	@Contract("_ -> this")
	public <T extends Throwable> Result<S> onErrorThrow(@NotNull Function<Throwable, T> throwableFunction) throws T {
		if (isError()) { throw throwableFunction.apply(throwable); }
		return this;
	}

	/**
	 * Maps the value of a successful result using the provided function. If the result is erroneous, it is returned as is.
	 * @param f The function to apply to the value of a successful result.
	 * @return A new Result instance with the mapped value if the original result was successful; otherwise, the original erroneous result.
	 */
	@SuppressWarnings("unchecked")
	public <N> Result<N> map(@NotNull Function<S, N> f) {
		if (isError()) { return (Result<N>) this; }
		return Result.from(() -> f.apply(successValue));
	}

	/**
	 * Maps the value of a successful result using the provided function if the predicate is met. If the result is erroneous, it is returned as is.
	 * @param predicate The predicate to check against the value of a successful result.
	 * @param f The function to apply to the value of a successful result.
	 * @param <N> The type of the new value.
	 * @return A new Result instance with the mapped value if the original result was successful and the predicate was met; otherwise, the original erroneous result.
	 */
	@SuppressWarnings("unchecked")
	public <N> Result<N> mapWhen(@NotNull Predicate<S> predicate, @NotNull Function<S, N> f) {
		if (predicate.test(successValue)) {
			return Result.from(() -> f.apply(successValue));
		}
        return (Result<N>) this;
	}

	/**
	 * Applies the provided function to the value of a successful result, which should return another Result. If the result is erroneous, it is returned as is.
	 * @param f The function to apply to the value of a successful result.
	 * @return A new Result instance resulting from the function application if the original result was successful; otherwise, the original erroneous result.
	 */
	@SuppressWarnings("unchecked")
	public <N> Result<N> flatMap(@NotNull Function<S, Result<N>> f) {
		if (isError()) { return (Result<N>) this; }
		return f.apply(successValue);
	}

	/**
	 * Applies the provided function to the value of a successful result, which should return another Result, if the predicate is met. If the result is erroneous, it is returned as is.
	 * @param predicate The predicate to check against the value of a successful result.
	 * @param f The function to apply to the value of a successful result.
	 * @param <N> The type of the new value.
	 * @return A new Result instance resulting from the function application if the original result was successful and the predicate was met; otherwise, the original erroneous result.
	 */
	@SuppressWarnings("unchecked")
	public <N> Result<N> flatMapWhen(@NotNull Predicate<S> predicate, @NotNull Function<S, Result<N>> f) {
		if (predicate.test(successValue)) {
			return f.apply(successValue);
		}
		return (Result<N>) this;
	}

	/**
	 * Maps the throwable of an erroneous result using the provided function. If the result is successful, it is returned as is.
	 * @param f The function to apply to the throwable of an erroneous result.
	 * @return A new Result instance with the mapped throwable if the original result was erroneous; otherwise, the original successful result.
	 */
	public Result<S> mapError(@NotNull Function<Throwable, Throwable> f) {
		if (isError()) { return Result.fail(f.apply(throwable)); }
		return this;
	}

	/**
	 * Maps the throwable of an erroneous result using the provided function. If the result is successful, it is returned as is.
	 * @param fSupplier The supplier for the function to apply to the throwable of an erroneous result.
	 * @return A new Result instance with the mapped throwable if the original result was erroneous; otherwise, the original successful result.
	 */
	public Result<S> mapErrorLazy(Supplier<Function<Throwable, Throwable>> fSupplier) {
		if (isError()) { return Result.fail(fSupplier.get().apply(throwable)); }
		return this;
	}

	/**
	 * Transforms the current Result<S> into a Result<N> by applying either the successFunction or errorFunction.
	 * @param successFunction The function to apply if the result is successful.
	 * @param errorFunction The function to apply if the result is erroneous.
	 * @param <N> The type of the new result.
	 * @return A new Result instance after applying the corresponding function.
	 */
	public <N> @NotNull Result<N> transform(@NotNull Function<S, N> successFunction, @NotNull Function<Throwable, N> errorFunction) {
		if (isError()) {
			return Result.success(errorFunction.apply(throwable));
		}
		return Result.success(successFunction.apply(successValue));
	}

	/**
	 * Applies a transformation function to the error of this Result, returning a new success value.
	 * @param transformFunction The function to apply if the result is erroneous.
	 * @return A new Result instance with the transformed value if the original was erroneous; otherwise, the original result.
	 */
	public Result<S> onErrorTransform(@NotNull BiFunction<S, Throwable, S> transformFunction) {
		if (isError()) {
			return Result.success(transformFunction.apply(successValue, throwable));
		}
		return this;
	}

	/**
	 * Recovers from an error by applying a function to the throwable and returning a new success value.
	 * @param recoverFunction The function to apply to the throwable.
	 * @return A new Result instance with the recovered value if the original was erroneous; otherwise, the original result.
	 */
	public Result<S> recover(@NotNull Function<Throwable, S> recoverFunction) {
		if (isError()) { return Result.success(recoverFunction.apply(throwable)); }
		return this;
	}

	/**
	 * Recovers from an error by applying a function to the throwable and returning a new success value.
	 * @param recoverFunctionSupplier The supplier for the function to apply to the throwable.
	 * @return A new Result instance with the recovered value if the original was erroneous; otherwise, the original result.
	 */
	public Result<S> recoverLazy(Supplier<Function<Throwable, S>> recoverFunctionSupplier) {
		if (isError()) { return Result.success(recoverFunctionSupplier.get().apply(throwable)); }
		return this;
	}

	/**
	 * Combines two Result instances using a combiner function if both are successful.
	 * @param first The first result to combine.
	 * @param second The second result to combine.
	 * @param combiner The function to combine the values of the two results.
	 * @param <S> The type of the first result.
	 * @param <T> The type of the second result.
	 * @param <R> The type of the combined result.
	 * @return A new Result instance representing the combination, or an error if any of the results is erroneous.
	 */
	public static <S, T, R> Result<R> combine(@NotNull Result<S> first, @NotNull Result<T> second, @NotNull BiFunction<S, T, R> combiner) {
		if (first.isError()) return Result.fail(first.getError());
		if (second.isError()) return Result.fail(second.getError());
		return Result.success(combiner.apply(first.get(), second.get()));
	}

	/**
	 * Applies a function to either the success value or the error throwable, returning a value of type T.
	 * @param successFunction The function to apply to the success value.
	 * @param errorFunction The function to apply to the error throwable.
	 * @param <T> The type of the result after applying the function.
	 * @return The result of applying the corresponding function.
	 */
	public <T> T fold(@NotNull Function<S, T> successFunction, @NotNull Function<Throwable, T> errorFunction) {
		return isSuccess() ? successFunction.apply(successValue) : errorFunction.apply(throwable);
	}

	/**
	 * Filters the success value of the Result using a predicate, potentially returning an error if the predicate is not satisfied.
	 * @param predicate The predicate to apply to the success value.
	 * @param exceptionSupplier The supplier for the exception to be thrown if the predicate is not satisfied.
	 * @return The original Result if the predicate is satisfied; otherwise, a new erroneous Result.
	 */
	public Result<S> filter(@NotNull Predicate<S> predicate, @NotNull Supplier<Throwable> exceptionSupplier) {
		if (isSuccess() && !predicate.test(successValue)) {
			return Result.fail(exceptionSupplier.get());
		}
		return this;
	}

	/**
	 * Filters the success value of the Result using a predicate, potentially returning an error if the predicate is not satisfied.
	 * @param predicate The predicate to apply to the success value.
	 * @param exceptionFunction The function to apply to the success value to generate an exception if the predicate is not satisfied.
	 * @return The original Result if the predicate is satisfied; otherwise, a new erroneous Result.
	 */
	public Result<S> filter(@NotNull Predicate<S> predicate, @NotNull Function<S, Throwable> exceptionFunction) {
		if (isSuccess() && !predicate.test(successValue)) {
			return Result.fail(exceptionFunction.apply(successValue));
		}
		return this;
	}

	/**
	 * Transforms a stream of results into a single result containing a stream of values.
	 * @param results The stream of results to transform.
	 * @param <T> The type of the value.
	 * @return A new Result instance containing a stream of values if all the results were successful; otherwise, the first erroneous result.
	 */
	public static <T> Result<Stream<T>> sequence(@NotNull Stream<Result<T>> results) {
		List<T> successValues = new ArrayList<>();
		for (Result<T> result : (Iterable<Result<T>>) results::iterator) {
			if (result.isError()) return Result.fail(result.getError());
			successValues.add(result.get());
		}
		return Result.success(successValues.stream());
	}

	/**
	 * Performs the given action if the result is successful.
	 * @param consumer The consumer to apply to the success value.
	 * @return The original Result, allowing method chaining.
	 */
	@Contract("_ -> this")
	public Result<S> ifSuccess(@NotNull Consumer<S> consumer) {
		if (isSuccess()) { consumer.accept(successValue); }
		return this;
	}

	/**
	 * Performs the given action if the result is successful and the provided condition is met.
	 * @param condition The condition to check against the success value.
	 * @param consumer The consumer to apply if the condition is met.
	 * @return The original Result, allowing method chaining.
	 */
	@Contract("_, _ -> this")
	public Result<S> ifSuccessAnd(@NotNull Predicate<S> condition, @NotNull Consumer<S> consumer) {
		if (isSuccess() && condition.test(successValue)) {
			consumer.accept(successValue);
		}
		return this;
	}

	/**
	 * Performs the given action if the result is erroneous.
	 * @param consumer The consumer to apply to the error throwable.
	 * @return The original Result, allowing method chaining.
	 */
	@Contract("_ -> this")
	public Result<S> ifError(@NotNull Consumer<Throwable> consumer) {
		if (isError()) consumer.accept(throwable);
		return this;
	}

	/**
	 * Performs the given action if the result is erroneous and the error is of the specified type.
	 * @param type The type of the error to check against.
	 * @param consumer The consumer to apply if the error is of the specified type.
	 * @param <T> The type of the exception.
	 * @return The original Result, allowing method chaining.
	 */
	@Contract("_, _ -> this")
	public <T extends Exception> Result<S> ifError(@NotNull Class<T> type, @NotNull Consumer<T> consumer) {
		if (isError() && type.isInstance(throwable)) {
			consumer.accept(type.cast(throwable));
		}
		return this;
	}

	/**
	 * Performs the given action if the result is erroneous and the provided condition is met.
	 * @param condition The condition to check against the error throwable.
	 * @param consumer The consumer to apply if the condition is met.
	 * @return The original Result, allowing method chaining.
	 */
	@Contract("_, _ -> this")
	public Result<S> ifErrorAnd(@NotNull Predicate<Throwable> condition, @NotNull Consumer<Throwable> consumer) {
		if (isError() && condition.test(throwable)) {
			consumer.accept(throwable);
		}
		return this;
	}

	/**
	 * Performs the given actions based on the state of the Result: one for success and another for error.
	 * @param success The consumer to apply if the result is successful.
	 * @param error The consumer to apply if the result is erroneous.
	 * @return The original Result, allowing method chaining.
	 */
	@Contract("_, _ -> this")
	public Result<S> ifPresent(@NotNull Consumer<S> success, @NotNull Consumer<Throwable> error) {
		if (isSuccess()) { success.accept(successValue); }
		else error.accept(throwable);
		return this;
	}

	/**
	 * Performs the given actions based on the state of the Result and a condition: one for success and another for error.
	 * @param successCondition The condition to check against the success value.
	 * @param success The consumer to apply if the result is successful and the condition is met.
	 * @param error The consumer to apply if the result is erroneous.
	 * @return The original Result, allowing method chaining.
	 */
	@Contract("_, _, _ -> this")
	public Result<S> ifPresentAnd(@NotNull Predicate<S> successCondition, @NotNull Consumer<S> success, @NotNull Consumer<Throwable> error) {
		if (isSuccess() && successCondition.test(successValue)) { success.accept(successValue); }
		else error.accept(throwable);
		return this;
	}

	/**
	 * Performs the given action if the result is empty.
	 * @param runnable The runnable to execute if the result is empty.
	 * @return The original Result, allowing method chaining.
	 */
	@Contract("_ -> this")
	public Result<S> ifEmpty(@NotNull Runnable runnable) {
		if (isEmpty()) { runnable.run(); }
		return this;
	}

	/**
	 * Performs the given action if the result is empty and the provided condition is met.
	 * @param condition The condition to check against the Result.
	 * @param runnable The runnable to execute if the condition is met.
	 * @return The original Result, allowing method chaining.
	 */
	@Contract("_, _ -> this")
	public Result<S> ifEmptyAnd(@NotNull Predicate<Result<S>> condition, @NotNull Runnable runnable) {
		if (isEmpty() && condition.test(this)) { runnable.run(); }
		return this;
	}

	/**
	 * Performs the given action if the result is not empty.
	 * @param consumer The consumer to apply to the success value.
	 * @return The original Result, allowing method chaining.
	 */
	@Contract("_ -> this")
	public Result<S> ifNotEmpty(@NotNull Consumer<S> consumer) {
		if (!isEmpty()) { consumer.accept(successValue); }
		return this;
	}

	/**
	 * Performs the given action if the result is not empty and the provided condition is met.
	 * @param condition The condition to check against the success value.
	 * @param consumer The consumer to apply if the condition is met.
	 * @return The original Result, allowing method chaining.
	 */
	@Contract("_, _ -> this")
	public Result<S> ifNotEmptyAnd(@NotNull Predicate<S> condition, @NotNull Consumer<S> consumer) {
		if (!isEmpty() && condition.test(successValue)) { consumer.accept(successValue); }
		return this;
	}

	/**
	 * Wraps an error with additional context using the provided bi-function and message.
	 * @param f The bi-function that takes a message and throwable, and returns a new throwable with additional context.
	 * @param message The message to provide context to the error.
	 * @return A new Result instance with the wrapped error if the original was erroneous; otherwise, the original result.
	 */
	public Result<S> wrapError(@NotNull BiFunction<String, Throwable, Throwable> f, @NotNull String message) {
		if (isError()) { return Result.fail(f.apply(message, throwable)); }
		return this;
	}

	/**
	 * Maps the error of this Result using the provided function, transforming it into another error.
	 * @param f The function to apply to the error.
	 * @return A new Result instance with the transformed error if the original was erroneous; otherwise, the original result.
	 */
	public Result<S> wrapError(@NotNull Function<Throwable, Throwable> f) {
		return mapError(f);
	}

	/**
	 * Converts the success value of this Result to an Optional.
	 * @return An Optional containing the success value if present; otherwise, an empty Optional.
	 */
	@Contract(pure = true)
	public Optional<S> asOptional() {
		return isSuccess() ? Optional.of(successValue) : Optional.empty();
	}

	/**
	 * Converts the success value of this Result to a Stream.
	 * @return A Stream containing the success value if present; otherwise, an empty Stream.
	 */
	public Stream<S> asStream() {
		if (isSuccess()) { return Stream.of(successValue); }
		return Stream.empty();
	}

	/**
	 * Converts the error of this Result to a Stream.
	 * @return A Stream containing the error if present; otherwise, an empty Stream.
	 */
	public Stream<Throwable> errorAsStream() {
		if (isError()) { return Stream.of(throwable); }
		return Stream.empty();
	}

	/**
	 * Validates the value of a successful result using the provided validator. If the result is erroneous, it is returned as is.
	 * @param validator The validator to use for validating the value of a successful result.
	 * @return A new Result instance, either successful or erroneous, based on the validation outcome.
	 */
	public Result<S> validate(@NotNull Validator<S> validator) {
		if (isSuccess()) { return validator.validate(successValue); }
		return this;
	}

	/**
	 * Validates the value of a successful result using the provided validator and consumes the error using the provided errorConsumer. If the result is erroneous, the errorConsumer is called with the throwable.
	 * @param validator The validator to use for validating the value of a successful result.
	 * @param errorConsumer The consumer to use if the validation fails or the result is already erroneous.
	 * @return A new Result instance, either successful or erroneous, based on the validation outcome.
	 */
	public Result<S> validate(@NotNull Validator<S> validator, @NotNull Consumer<Throwable> errorConsumer) {
		if (isSuccess()) { return validator.validate(successValue); }
		errorConsumer.accept(throwable);
		return this;
	}

	/**
	 * Validates the success value of this Result using the provided validator if the predicate is met.
	 * @param predicate The predicate to check against the success value.
	 * @param validator The validator to use for validating the success value.
	 * @return A new Result instance, either successful or erroneous, based on the validation outcome.
	 */
	public Result<S> validateIf(@NotNull Predicate<S> predicate, @NotNull Validator<S> validator) {
		if (isSuccess() && predicate.test(successValue)) { return validator.validate(successValue); }
		return this;
	}

	/**
	 * Validates the success value of this Result using the provided validator if the predicate is met and consumes the error using the provided errorConsumer.
	 * If the result is erroneous, the errorConsumer is called with the throwable.
	 * @param predicate The predicate to check against the success value.
	 * @param validator The validator to use for validating the success value.
	 * @param errorConsumer The consumer to use if the validation fails or the result is already erroneous.
	 * @return A new Result instance, either successful or erroneous, based on the validation outcome.
	 */
	public Result<S> validateIf(@NotNull Predicate<S> predicate, @NotNull Validator<S> validator, @NotNull Consumer<Throwable> errorConsumer) {
		if (isSuccess() && predicate.test(successValue)) { return validator.validate(successValue); }
		errorConsumer.accept(throwable);
		return this;
	}

	/**
	 * Checks for equality between this Result and another object. Two results are equal if they have the same success value, throwable, and empty status.
	 * @param o The object to compare with this Result.
	 * @return true if the objects are equal, false otherwise.
	 */
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

	/**
	 * Generates a hash code for this Result.
	 * @return The hash code of this Result.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(throwable, successValue, isEmpty);
	}

	/**
	 * Returns a string representation of this Result.
	 * @return A string describing the Result, including its status and value or throwable.
	 */
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