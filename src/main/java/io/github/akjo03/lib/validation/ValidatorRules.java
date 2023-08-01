package io.github.akjo03.lib.validation;

import io.github.akjo03.lib.result.Result;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public final class ValidatorRules {
	private ValidatorRules() {}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isNotNull(String message) {
		return t -> t == null ? Result.fail(new ValidationException(message)) : Result.success(t);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> doesEqual(T t, String message) {
		return t2 -> t2 == null || !t2.equals(t) ? Result.fail(new ValidationException(message)) : Result.success(t2);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> doesNotEqual(T t, String message) {
		return t2 -> t2 == null || t2.equals(t) ? Result.fail(new ValidationException(message)) : Result.success(t2);
	}

	@Contract(pure = true)
	public static @NotNull Validator<String> stringIsNotEmpty(String message) {
		return s -> s == null || s.isEmpty() ? Result.fail(new ValidationException(message)) : Result.success(s);
	}

	@Contract(pure = true)
	public static @NotNull Validator<String> stringIsNotBlank(String message) {
		return s -> s == null || s.isBlank() ? Result.fail(new ValidationException(message)) : Result.success(s);
	}
	
	@Contract(pure = true)
	public static @NotNull Validator<String> stringDoesMatch(String pattern, String message) {
		return s -> s == null || !s.matches(pattern) ? Result.fail(new ValidationException(message)) : Result.success(s);
	}
	
	@Contract(pure = true)
	public static @NotNull Validator<String> stringLengthIsGreaterThan(int length, String message) {
		return s -> s == null || s.length() <= length ? Result.fail(new ValidationException(message)) : Result.success(s);
	}

	@Contract(pure = true)
	public static @NotNull Validator<String> stringLengthIsGreaterThanOrEqualTo(int length, String message) {
		return s -> s == null || s.length() < length ? Result.fail(new ValidationException(message)) : Result.success(s);
	}
	
	@Contract(pure = true)
	public static @NotNull Validator<String> stringLengthIsLessThan(int length, String message) {
		return s -> s == null || s.length() >= length ? Result.fail(new ValidationException(message)) : Result.success(s);
	}
	
	@Contract(pure = true)
	public static @NotNull Validator<String> stringLengthIsLessThanOrEqualTo(int length, String message) {
		return s -> s == null || s.length() > length ? Result.fail(new ValidationException(message)) : Result.success(s);
	}
	
	@Contract(pure = true)
	public static @NotNull Validator<String> stringLengthIsEqualTo(int length, String message) {
		return s -> s == null || s.length() != length ? Result.fail(new ValidationException(message)) : Result.success(s);
	}
	
	@Contract(pure = true)
	public static @NotNull Validator<String> stringLengthIsBetween(int min, int max, String message) {
		return s -> s == null || s.length() < min || s.length() > max ? Result.fail(new ValidationException(message)) : Result.success(s);
	}
	
	@Contract(pure = true)
	public static @NotNull Validator<String> stringLengthIsBetweenInclusive(int min, int max, String message) {
		return s -> s == null || s.length() <= min || s.length() >= max ? Result.fail(new ValidationException(message)) : Result.success(s);
	}
	
	@Contract(pure = true)
	public static @NotNull Validator<String> stringLengthIsOutside(int min, int max, String message) {
		return s -> s == null || s.length() >= min && s.length() <= max ? Result.fail(new ValidationException(message)) : Result.success(s);
	}
	
	@Contract(pure = true)
	public static @NotNull Validator<String> stringLengthIsOutsideInclusive(int min, int max, String message) {
		return s -> s == null || s.length() > min && s.length() < max ? Result.fail(new ValidationException(message)) : Result.success(s);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionIsNotEmpty(String message) {
		return l -> l == null || l.isEmpty() ? Result.fail(new ValidationException(message)) : Result.success(l);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionIsEmpty(String message) {
		return l -> l == null || !l.isEmpty() ? Result.fail(new ValidationException(message)) : Result.success(l);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionSizeIsGreaterThan(int size, String message) {
		return l -> l == null || l.size() <= size ? Result.fail(new ValidationException(message)) : Result.success(l);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionSizeIsGreaterThanOrEqualTo(int size, String message) {
		return l -> l == null || l.size() < size ? Result.fail(new ValidationException(message)) : Result.success(l);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionSizeIsLessThan(int size, String message) {
		return l -> l == null || l.size() >= size ? Result.fail(new ValidationException(message)) : Result.success(l);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionSizeIsLessThanOrEqualTo(int size, String message) {
		return l -> l == null || l.size() > size ? Result.fail(new ValidationException(message)) : Result.success(l);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionSizeIsEqualTo(int size, String message) {
		return l -> l == null || l.size() != size ? Result.fail(new ValidationException(message)) : Result.success(l);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionSizeIsBetween(int min, int max, String message) {
		return l -> l == null || l.size() < min || l.size() > max ? Result.fail(new ValidationException(message)) : Result.success(l);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionSizeIsBetweenInclusive(int min, int max, String message) {
		return l -> l == null || l.size() <= min || l.size() >= max ? Result.fail(new ValidationException(message)) : Result.success(l);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionSizeIsOutside(int min, int max, String message) {
		return l -> l == null || l.size() >= min && l.size() <= max ? Result.fail(new ValidationException(message)) : Result.success(l);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionSizeIsOutsideInclusive(int min, int max, String message) {
		return l -> l == null || l.size() > min && l.size() < max ? Result.fail(new ValidationException(message)) : Result.success(l);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionContains(T t, String message) {
		return l -> l == null || !l.contains(t) ? Result.fail(new ValidationException(message)) : Result.success(l);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionDoesNotContain(T t, String message) {
		return l -> l == null || l.contains(t) ? Result.fail(new ValidationException(message)) : Result.success(l);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionContainsAll(Collection<T> c, String message) {
		return l -> l == null || !l.containsAll(c) ? Result.fail(new ValidationException(message)) : Result.success(l);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionContainsNone(Collection<T> c, String message) {
		return l -> l == null || !Collections.disjoint(l, c) ? Result.fail(new ValidationException(message)) : Result.success(l);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionContainsAny(Collection<T> c, String message) {
		return l -> l == null || Collections.disjoint(l, c) ? Result.fail(new ValidationException(message)) : Result.success(l);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionAllMatch(Predicate<T> p, String message) {
		return l -> l == null || !l.stream().allMatch(p) ? Result.fail(new ValidationException(message)) : Result.success(l);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionAnyMatch(Predicate<T> p, String message) {
		return l -> l == null || l.stream().noneMatch(p) ? Result.fail(new ValidationException(message)) : Result.success(l);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionNoneMatch(Predicate<T> p, String message) {
		return l -> l == null || l.stream().anyMatch(p) ? Result.fail(new ValidationException(message)) : Result.success(l);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isTrue(boolean b, String message) {
		return t -> !b ? Result.fail(new ValidationException(message)) : Result.success(t);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isFalse(boolean b, String message) {
		return t -> b ? Result.fail(new ValidationException(message)) : Result.success(t);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isGreaterThan(Comparable<T> c, String message) {
		return t -> t == null || c.compareTo(t) >= 0 ? Result.fail(new ValidationException(message)) : Result.success(t);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isGreaterThanOrEqualTo(Comparable<T> c, String message) {
		return t -> t == null || c.compareTo(t) > 0 ? Result.fail(new ValidationException(message)) : Result.success(t);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isLessThan(Comparable<T> c, String message) {
		return t -> t == null || c.compareTo(t) <= 0 ? Result.fail(new ValidationException(message)) : Result.success(t);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isLessThanOrEqualTo(Comparable<T> c, String message) {
		return t -> t == null || c.compareTo(t) < 0 ? Result.fail(new ValidationException(message)) : Result.success(t);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isBetween(Comparable<T> c1, Comparable<T> c2, String message) {
		return t -> t == null || c1.compareTo(t) > 0 || c2.compareTo(t) < 0 ? Result.fail(new ValidationException(message)) : Result.success(t);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isBetweenInclusive(Comparable<T> c1, Comparable<T> c2, String message) {
		return t -> t == null || c1.compareTo(t) >= 0 || c2.compareTo(t) <= 0 ? Result.fail(new ValidationException(message)) : Result.success(t);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isOutside(Comparable<T> c1, Comparable<T> c2, String message) {
		return t -> t == null || c1.compareTo(t) <= 0 || c2.compareTo(t) >= 0 ? Result.fail(new ValidationException(message)) : Result.success(t);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isOutsideInclusive(Comparable<T> c1, Comparable<T> c2, String message) {
		return t -> t == null || c1.compareTo(t) < 0 || c2.compareTo(t) > 0 ? Result.fail(new ValidationException(message)) : Result.success(t);
	}
}