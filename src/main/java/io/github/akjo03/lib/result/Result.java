package io.github.akjo03.lib.result;

import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A type for storing either a successful or failed result of some operation.
 * <br />
 * Credit goes to <a href="https://github.com/samwho/result">samwho/result</a>.
 */
@SuppressWarnings("unused")
public final class Result<S> implements Supplier<S> {
	/**
	 * Create a successful Result.
	 */
	@Contract("_ -> new")
	public static <S> @NotNull Result<S> success(S s) {
		return new Result<>(null, Objects.requireNonNull(s), false);
	}

	/**
	 * Create a failed Result.
	 */
	@Contract("_ -> new")
	public static <S> @NotNull Result<S> fail(Exception e) {
		return new Result<>(Objects.requireNonNull(e), null, false);
	}

	/**
	 * Create an empty Result.
	 */
	@Contract(value = " -> new", pure = true)
	public static @NotNull Result<Void> empty() {
		return new Result<>(null, null, true);
	}

	/**
	 * Creates a Result based on what happens when the given
	 * supplier is called. If a value is returned, the Result
	 * will be successful. If an exception is thrown, the
	 * Result will be a failure.
	 */
	public static <S> Result<S> from(ThrowingSupplier<S> s) {
		Objects.requireNonNull(s);
		try {
			return success(s.get());
		} catch (Exception t) {
			return fail(t);
		}
	}

	private final Exception e;
	private final S s;
	/**
	 * -- GETTER --
	 *  Returns
	 *  if the Result is empty,
	 *  otherwise.
	 */
	@Getter
	private final boolean empty;

	private Result(Exception e, S s, boolean empty) {
		this.e = e;
		this.s = s;
		this.empty = empty;
	}

	/**
	 * Returns {@code true} if the Result is erroneous, {@code false}
	 * otherwise.
	 */
	public boolean isError() {
		return e != null;
	}

	/**
	 * Returns {@code true} if the Result is successful, {@code false}
	 * otherwise.
	 */
	public boolean isSuccess() {
		return s != null;
	}

	/**
	 * Returns the erroneous result, which is always some type of
	 * {@code Exception}. If the Result is not erroneous, a
	 * {@code NoSuchElementException} is thrown.
	 * <br />
	 * It is expected that you always check if the result is
	 * erroneous before trying to get the error value.
	 *
	 * @see Result#isError()
	 */
	public Exception getError() {
		if (!isError())
			throw new NoSuchElementException("Attempted to retrieve error on non-erroneous result");
		return e;
	}

	/**
	 * Returns the result value. If the Result is erroneous, a
	 * {@code NoSuchElementException} is thrown.
	 * <br />
	 * It is expected that you always check if the result is
	 * erroneous before trying to get the value.
	 *
	 * @see Result#isError()
	 */
	@Override
	public S get() {
		if (isError())
			throw new NoSuchElementException("Attempted to retrieve value on erroneous result");
		return s;
	}

	/**
	 * Returns the result value unless the Result is erroneous,
	 * in which case a supplied default is returned.
	 */
	public S getOrElse(S def) {
		if (isError()) return def;
		return s;
	}

	/**
	 * Returns the result value unless the Result is erroneous,
	 * in which case a function is called and the result of that
	 * function is used.
	 */
	public S getOrElse(Function<Exception, S> f) {
		if (isError()) return f.apply(e);
		return s;
	}

	/**
	 * Returns the result value unless the Result is erroneous,
	 * in which case null is returned.
	 */
	public S getOrNull() {
		if (isError()) return null;
		return s;
	}

	/**
	 * Returns the result value unless the Result is erroneous,
	 * in which case the error value is thrown.
	 */
	public S getOrThrow() throws Exception {
		if (isError()) throw e;
		return s;
	}

	/**
	 * Maps the result value to some other value.
	 * <br />
	 * If the Result is erroneous, this method returns the original
	 * Result without calling the mapping function. The returned
	 * value is cast, but this cast is safe because the value
	 * being cast is guaranteed to be {@code null}.
	 */
	@SuppressWarnings("unchecked")
	public <N> Result<N> map(Function<S, N> f) {
		if (isError()) return (Result<N>)this;
		return Result.from(() -> f.apply(s));
	}

	/**
	 * Maps the error value to some other error value, which
	 * must be of type {@code Exception}.
	 * <br />
	 * If the Result is successful, this method returns the
	 * original Result without doing anything.
	 */
	public Result<S> mapError(Function<Exception, Exception> f) {
		if (isError()) return Result.fail(f.apply(e));
		return this;
	}

	/**
	 * If the Result is erroneous, calls the given {@code Consumer} on
	 * the error result.
	 *
	 * @return this
	 */
	public Result<S> ifError(Consumer<Exception> consumer) {
		if (isError()) consumer.accept(e);
		return this;
	}

	/**
	 * If the Result is erroneous, calls the given {@code Consumer} on
	 * the specified exception type.
	 * <br />
	 * If the Result is successful, this method returns the original
	 * Result without doing anything.
	 *
	 * @param <T> the type of exception to handle
	 *           (must be a subtype of {@code Exception})
	 */
	public <T extends Exception> Result<S> ifError(Class<T> type, Consumer<T> consumer) {
		if (isError() && type.isInstance(e)) consumer.accept(type.cast(e));
		return this;
	}

	/**
	 * If the Result is successful, calls the given {@code Consumer} on
	 * the result.
	 *
	 * @return this
	 */
	public Result<S> ifSuccess(Consumer<S> consumer) {
		if (isSuccess()) consumer.accept(s);
		return this;
	}

	/**
	 * If the Result is successful, calls the given {@code Consumer} on
	 * the result. Otherwise, calls the given {@code Consumer} on the
	 * error result.
	 *
	 * @return this
	 */
	public Result<S> ifPresent(Consumer<S> success, Consumer<Exception> error) {
		if (isSuccess()) success.accept(s);
		else error.accept(e);
		return this;
	}

	/**
	 * A convenience method for wrapping an erroneous result object
	 * with more information.
	 *
	 * <pre>
	 *   Result.fail(new IOException()).wrapError(IllegalArgumentException::new, "invalid param");
	 * </pre>
	 *
	 * If the Result is successful, this method returns the original
	 * Result without doing anything.
	 */
	public Result<S> wrapError(BiFunction<String, Exception, Exception> f, String message) {
		if (isError()) return Result.fail(f.apply(message, e));
		return this;
	}

	/**
	 * Alias for {@code Result#mapError(Function)}.
	 *
	 * @see Result#mapError(Function)
	 */
	public Result<S> wrapError(Function<Exception, Exception> f) {
		return mapError(f);
	}

	/**
	 * Converts this Result<S> to an Optional<S>, discarding
	 * the error value in the process. If the Result is
	 * erroneous, {@code Optional.empty()} is returned.
	 */
	public Optional<S> asOptional() {
		return Optional.ofNullable(s);
	}
}