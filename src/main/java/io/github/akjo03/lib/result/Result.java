package io.github.akjo03.lib.result;

import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Credit goes to <a href="https://github.com/samwho/result">samwho/result</a>.
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public final class Result<S> implements Supplier<S> {
	@Contract("_ -> new")
	public static <S> @NotNull Result<S> success(S s) {
		return new Result<>(null, Objects.requireNonNull(s), false);
	}

	@Contract("_ -> new")
	public static <S> @NotNull Result<S> fail(Exception e) {
		return new Result<>(Objects.requireNonNull(e), null, false);
	}

	@Contract(value = " -> new", pure = true)
	public static @NotNull Result<?> empty() {
		return new Result<>(null, null, true);
	}

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

	@Getter private final boolean empty;

	private Result(Exception e, S s, boolean empty) {
		this.e = e;
		this.s = s;
		this.empty = empty;
	}

	public boolean isError() {
		return e != null;
	}

	public boolean isSuccess() {
		return empty || s != null;
	}

	public Exception getError() {
		if (!isError())
			throw new NoSuchElementException("Attempted to retrieve error on non-erroneous result");
		return e;
	}

	@Override
	public S get() {
		if (isError())
			throw new NoSuchElementException("Attempted to retrieve value on erroneous result");
		return s;
	}

	public S getOrElse(S def) {
		if (isError()) return def;
		return s;
	}

	public S getOrElse(Function<Exception, S> f) {
		if (isError()) return f.apply(e);
		return s;
	}

	@Contract(pure = true)
	public @Nullable S getOrNull() {
		if (isError()) return null;
		return s;
	}

	public S getOrThrow() throws Exception {
		if (isError()) throw e;
		return s;
	}

	@SuppressWarnings("unchecked")
	public <N> Result<N> map(Function<S, N> f) {
		if (isError()) return (Result<N>)this;
		return Result.from(() -> f.apply(s));
	}

	public Result<S> mapError(Function<Exception, Exception> f) {
		if (isError()) return Result.fail(f.apply(e));
		return this;
	}

	public Result<S> ifError(Consumer<Exception> consumer) {
		if (isError()) consumer.accept(e);
		return this;
	}

	public <T extends Exception> Result<S> ifError(Class<T> type, Consumer<T> consumer) {
		if (isError() && type.isInstance(e)) consumer.accept(type.cast(e));
		return this;
	}

	public Result<S> ifSuccess(Consumer<S> consumer) {
		if (isSuccess()) consumer.accept(s);
		return this;
	}

	public Result<S> ifPresent(Consumer<S> success, Consumer<Exception> error) {
		if (isSuccess()) success.accept(s);
		else error.accept(e);
		return this;
	}

	public Result<S> wrapError(BiFunction<String, Exception, Exception> f, String message) {
		if (isError()) return Result.fail(f.apply(message, e));
		return this;
	}

	public Result<S> wrapError(Function<Exception, Exception> f) {
		return mapError(f);
	}

	public Optional<S> asOptional() {
		return Optional.ofNullable(s);
	}
}