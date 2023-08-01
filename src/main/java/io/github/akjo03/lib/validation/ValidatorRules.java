package io.github.akjo03.lib.validation;

import io.github.akjo03.lib.result.Result;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

@SuppressWarnings("unused")
public final class ValidatorRules {
	private ValidatorRules() {}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isNotNull() {
		return t -> t == null ? Result.fail(new ValidationException("Value cannot be null")) : Result.success(t);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> doesEqual(T t) {
		return t2 -> t2 == null || !t2.equals(t) ? Result.fail(new ValidationException("Value must be equal to " + t)) : Result.success(t2);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> doesNotEqual(T t) {
		return t2 -> t2 == null || t2.equals(t) ? Result.fail(new ValidationException("Value must not be equal to " + t)) : Result.success(t2);
	}

	@Contract(pure = true)
	public static @NotNull Validator<String> stringIsNotEmpty() {
		return s -> s == null || s.isEmpty() ? Result.fail(new ValidationException("String cannot be empty")) : Result.success(s);
	}

	@Contract(pure = true)
	public static @NotNull Validator<String> stringIsNotBlank() {
		return s -> s == null || s.isBlank() ? Result.fail(new ValidationException("String cannot be blank")) : Result.success(s);
	}
	
	@Contract(pure = true)
	public static @NotNull Validator<String> stringDoesMatch(String pattern) {
		return s -> s == null || !s.matches(pattern) ? Result.fail(new ValidationException("String must match pattern " + pattern)) : Result.success(s);
	}
	
	@Contract(pure = true)
	public static @NotNull Validator<String> stringLengthIsGreaterThan(int length) {
		return s -> s == null || s.length() <= length ? Result.fail(new ValidationException("String length must be greater than " + length)) : Result.success(s);
	}

	@Contract(pure = true)
	public static @NotNull Validator<String> stringLengthIsGreaterThanOrEqualTo(int length) {
		return s -> s == null || s.length() < length ? Result.fail(new ValidationException("String length must be greater than or equal to " + length)) : Result.success(s);
	}
	
	@Contract(pure = true)
	public static @NotNull Validator<String> stringLengthIsLessThan(int length) {
		return s -> s == null || s.length() >= length ? Result.fail(new ValidationException("String length must be less than " + length)) : Result.success(s);
	}
	
	@Contract(pure = true)
	public static @NotNull Validator<String> stringLengthIsLessThanOrEqualTo(int length) {
		return s -> s == null || s.length() > length ? Result.fail(new ValidationException("String length must be less than or equal to " + length)) : Result.success(s);
	}
	
	@Contract(pure = true)
	public static @NotNull Validator<String> stringLengthIsEqualTo(int length) {
		return s -> s == null || s.length() != length ? Result.fail(new ValidationException("String length must be equal to " + length)) : Result.success(s);
	}
	
	@Contract(pure = true)
	public static @NotNull Validator<String> stringLengthIsBetween(int min, int max) {
		return s -> s == null || s.length() < min || s.length() > max ? Result.fail(new ValidationException("String length must be between " + min + " and " + max)) : Result.success(s);
	}
	
	@Contract(pure = true)
	public static @NotNull Validator<String> stringLengthIsBetweenInclusive(int min, int max) {
		return s -> s == null || s.length() <= min || s.length() >= max ? Result.fail(new ValidationException("String length must be between " + min + " and " + max)) : Result.success(s);
	}
	
	@Contract(pure = true)
	public static @NotNull Validator<String> stringLengthIsOutside(int min, int max) {
		return s -> s == null || s.length() >= min && s.length() <= max ? Result.fail(new ValidationException("String length must be outside " + min + " and " + max)) : Result.success(s);
	}
	
	@Contract(pure = true)
	public static @NotNull Validator<String> stringLengthIsOutsideInclusive(int min, int max) {
		return s -> s == null || s.length() > min && s.length() < max ? Result.fail(new ValidationException("String length must be outside " + min + " and " + max)) : Result.success(s);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionIsNotEmpty() {
		return l -> l == null || l.isEmpty() ? Result.fail(new ValidationException("Collection cannot be empty")) : Result.success(l);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionIsEmpty() {
		return l -> l == null || !l.isEmpty() ? Result.fail(new ValidationException("Collection must be empty")) : Result.success(l);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionSizeIsGreaterThan(int size) {
		return l -> l == null || l.size() <= size ? Result.fail(new ValidationException("Collection size must be greater than " + size)) : Result.success(l);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionSizeIsGreaterThanOrEqualTo(int size) {
		return l -> l == null || l.size() < size ? Result.fail(new ValidationException("Collection size must be greater than or equal to " + size)) : Result.success(l);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionSizeIsLessThan(int size) {
		return l -> l == null || l.size() >= size ? Result.fail(new ValidationException("Collection size must be less than " + size)) : Result.success(l);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionSizeIsLessThanOrEqualTo(int size) {
		return l -> l == null || l.size() > size ? Result.fail(new ValidationException("Collection size must be less than or equal to " + size)) : Result.success(l);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionSizeIsEqualTo(int size) {
		return l -> l == null || l.size() != size ? Result.fail(new ValidationException("Collection size must be equal to " + size)) : Result.success(l);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionSizeIsBetween(int min, int max) {
		return l -> l == null || l.size() < min || l.size() > max ? Result.fail(new ValidationException("Collection size must be between " + min + " and " + max)) : Result.success(l);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionSizeIsBetweenInclusive(int min, int max) {
		return l -> l == null || l.size() <= min || l.size() >= max ? Result.fail(new ValidationException("Collection size must be between " + min + " and " + max)) : Result.success(l);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionSizeIsOutside(int min, int max) {
		return l -> l == null || l.size() >= min && l.size() <= max ? Result.fail(new ValidationException("Collection size must be outside " + min + " and " + max)) : Result.success(l);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionSizeIsOutsideInclusive(int min, int max) {
		return l -> l == null || l.size() > min && l.size() < max ? Result.fail(new ValidationException("Collection size must be outside " + min + " and " + max)) : Result.success(l);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionContains(T t) {
		return l -> l == null || !l.contains(t) ? Result.fail(new ValidationException("Collection must contain " + t)) : Result.success(l);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionDoesNotContain(T t) {
		return l -> l == null || l.contains(t) ? Result.fail(new ValidationException("Collection must not contain " + t)) : Result.success(l);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionContainsAll(Collection<T> c) {
		return l -> l == null || !l.containsAll(c) ? Result.fail(new ValidationException("Collection must contain all of " + c)) : Result.success(l);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionContainsNone(Collection<T> c) {
		return l -> l == null || !Collections.disjoint(l, c) ? Result.fail(new ValidationException("Collection must not contain any of " + c)) : Result.success(l);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<Collection<T>> collectionContainsAny(Collection<T> c) {
		return l -> l == null || Collections.disjoint(l, c) ? Result.fail(new ValidationException("Collection must contain any of " + c)) : Result.success(l);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isTrue(boolean b) {
		return t -> !b ? Result.fail(new ValidationException("Value must be true")) : Result.success(t);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isFalse(boolean b) {
		return t -> b ? Result.fail(new ValidationException("Value must be false")) : Result.success(t);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isGreaterThan(Comparable<T> comparable) {
		return t -> t == null || comparable.compareTo(t) >= 0 ? Result.fail(new ValidationException("Value must be greater than " + comparable)) : Result.success(t);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isGreaterThanOrEqualTo(Comparable<T> comparable) {
		return t -> t == null || comparable.compareTo(t) > 0 ? Result.fail(new ValidationException("Value must be greater than or equal to " + comparable)) : Result.success(t);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isLessThan(Comparable<T> comparable) {
		return t -> t == null || comparable.compareTo(t) <= 0 ? Result.fail(new ValidationException("Value must be less than " + comparable)) : Result.success(t);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isLessThanOrEqualTo(Comparable<T> comparable) {
		return t -> t == null || comparable.compareTo(t) < 0 ? Result.fail(new ValidationException("Value must be less than or equal to " + comparable)) : Result.success(t);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isBetween(Comparable<T> comparable1, Comparable<T> comparable2) {
		return t -> t == null || comparable1.compareTo(t) > 0 || comparable2.compareTo(t) < 0 ? Result.fail(new ValidationException("Value must be between " + comparable1 + " and " + comparable2)) : Result.success(t);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isBetweenInclusive(Comparable<T> comparable1, Comparable<T> comparable2) {
		return t -> t == null || comparable1.compareTo(t) >= 0 || comparable2.compareTo(t) <= 0 ? Result.fail(new ValidationException("Value must be between " + comparable1 + " and " + comparable2)) : Result.success(t);
	}

	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isOutside(Comparable<T> comparable1, Comparable<T> comparable2) {
		return t -> t == null || comparable1.compareTo(t) <= 0 || comparable2.compareTo(t) >= 0 ? Result.fail(new ValidationException("Value must not be between " + comparable1 + " and " + comparable2)) : Result.success(t);
	}
	
	@Contract(pure = true)
	public static <T> @NotNull Validator<T> isOutsideInclusive(Comparable<T> comparable1, Comparable<T> comparable2) {
		return t -> t == null || comparable1.compareTo(t) < 0 || comparable2.compareTo(t) > 0 ? Result.fail(new ValidationException("Value must not be between " + comparable1 + " and " + comparable2)) : Result.success(t);
	}
}