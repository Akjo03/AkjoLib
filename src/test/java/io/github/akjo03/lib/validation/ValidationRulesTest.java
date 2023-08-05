package io.github.akjo03.lib.validation;

import io.github.akjo03.lib.math.Range;
import io.github.akjo03.lib.result.Result;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationRulesTest {
	@Test
	public void testIsNotNull() {
		String message = "Input is null!";
		Validator<String> validator = ValidatorRules.isNotNull(message);

		Result<String> result = validator.validate(null);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate("Hello");
		assertFalse(result.isError());
		assertEquals("Hello", result.get());
	}

	@Test
	public void testDoesEqual() {
		String message = "Input is not equal!";
		Validator<String> validator = ValidatorRules.doesEqual("Hello", message);

		Result<String> result = validator.validate("Hello");
		assertFalse(result.isError());
		assertEquals("Hello", result.get());

		result = validator.validate("World");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testDoesNotEqual() {
		String message = "Input is equal!";
		Validator<String> validator = ValidatorRules.doesNotEqual("Hello", message);

		Result<String> result = validator.validate("Hello");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate("World");
		assertFalse(result.isError());
		assertEquals("World", result.get());
	}

	@Test
	public void testStringIsNotEmpty() {
		String message = "Input is empty!";
		Validator<String> validator = ValidatorRules.stringIsNotEmpty(message);

		Result<String> result = validator.validate("");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate(" ");
		assertFalse(result.isError());
		assertEquals(" ", result.get());

		result = validator.validate("Hello");
		assertFalse(result.isError());
		assertEquals("Hello", result.get());
	}

	@Test
	public void testStringIsNotBlank() {
		String message = "Input is blank!";
		Validator<String> validator = ValidatorRules.stringIsNotBlank(message);

		Result<String> result = validator.validate("");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate(" ");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate("Hello");
		assertFalse(result.isError());
		assertEquals("Hello", result.get());
	}

	@Test
	public void testStringDoesMatch() {
		String message = "Input does not match pattern!";
		String pattern = "[a-z]+";
		Validator<String> validator = ValidatorRules.stringDoesMatch(pattern, message);

		Result<String> result = validator.validate("123");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate("abc");
		assertFalse(result.isError());
		assertEquals("abc", result.get());
	}

	@Test
	public void testStringLengthIsGreaterThan() {
		String message = "Input length is not greater than the specified length!";
		int length = 5;
		Validator<String> validator = ValidatorRules.stringLengthIsGreaterThan(length, message);

		Result<String> result = validator.validate("abcd");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate("abcde");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate("abcdef");
		assertFalse(result.isError());
		assertEquals("abcdef", result.get());
	}

	@Test
	public void testStringLengthIsGreaterThanOrEqualTo() {
		String message = "Input length is not greater than or equal to the specified length!";
		int length = 5;
		Validator<String> validator = ValidatorRules.stringLengthIsGreaterThanOrEqualTo(length, message);

		Result<String> result = validator.validate("abcd");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate("abcde");
		assertFalse(result.isError());
		assertEquals("abcde", result.get());

		result = validator.validate("abcdef");
		assertFalse(result.isError());
		assertEquals("abcdef", result.get());
	}

	@Test
	public void testStringLengthIsLessThan() {
		String message = "Input length is not greater than the specified length!";
		int length = 5;
		Validator<String> validator = ValidatorRules.stringLengthIsLessThan(length, message);

		Result<String> result = validator.validate("abcd");
		assertFalse(result.isError());
		assertEquals("abcd", result.get());

		result = validator.validate("abcde");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate("abcdef");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testStringLengthIsLessThanOrEqualTo() {
		String message = "Input length is not greater than or equal to the specified length!";
		int length = 5;
		Validator<String> validator = ValidatorRules.stringLengthIsLessThanOrEqualTo(length, message);

		Result<String> result = validator.validate("abcd");
		assertFalse(result.isError());
		assertEquals("abcd", result.get());

		result = validator.validate("abcde");
		assertFalse(result.isError());
		assertEquals("abcde", result.get());

		result = validator.validate("abcdef");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testStringLengthIsEqualTo() {
		String message = "Input length is not equal to the specified length!";
		int length = 5;
		Validator<String> validator = ValidatorRules.stringLengthIsEqualTo(length, message);

		Result<String> result = validator.validate("abcd");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate("abcde");
		assertFalse(result.isError());
		assertEquals("abcde", result.get());

		result = validator.validate("abcdef");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testStringLengthIsBetweenExclusive() {
		String message = "Input length is not between the specified lengths!";
		int minLength = 5;
		int maxLength = 10;

		Validator<String> validator = ValidatorRules.stringLengthIsBetweenExclusive(minLength, maxLength, message);

		Result<String> result = validator.validate("abcd");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate("abcde");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate("abcdefgh");
		assertFalse(result.isError());
		assertEquals("abcdefgh", result.get());

		result = validator.validate("abcdefghij");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate("abcdefghijk");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testStringLengthIsBetweenInclusive() {
		String message = "Input length is not between the specified lengths!";
		int minLength = 5;
		int maxLength = 10;

		Validator<String> validator = ValidatorRules.stringLengthIsBetweenInclusive(minLength, maxLength, message);

		Result<String> result = validator.validate("abcd");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate("abcde");
		assertFalse(result.isError());
		assertEquals("abcde", result.get());

		result = validator.validate("abcdefgh");
		assertFalse(result.isError());
		assertEquals("abcdefgh", result.get());

		result = validator.validate("abcdefghij");
		assertFalse(result.isError());
		assertEquals("abcdefghij", result.get());

		result = validator.validate("abcdefghijk");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testStringLengthIsOutsideExclusive() {
		String message = "Input length is not outside the specified lengths!";
		int minLength = 5;
		int maxLength = 10;

		Validator<String> validator = ValidatorRules.stringLengthIsOutsideExclusive(minLength, maxLength, message);

		Result<String> result = validator.validate("abcd");
		assertFalse(result.isError());
		assertEquals("abcd", result.get());

		result = validator.validate("abcde");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate("abcdefgh");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate("abcdefghij");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate("abcdefghijk");
		assertFalse(result.isError());
		assertEquals("abcdefghijk", result.get());
	}

	@Test
	public void testStringLengthIsOutsideInclusive() {
		String message = "Input length is not outside the specified lengths!";
		int minLength = 5;
		int maxLength = 10;

		Validator<String> validator = ValidatorRules.stringLengthIsOutsideInclusive(minLength, maxLength, message);

		Result<String> result = validator.validate("abcd");
		assertFalse(result.isError());
		assertEquals("abcd", result.get());

		result = validator.validate("abcde");
		assertFalse(result.isError());
		assertEquals("abcde", result.get());

		result = validator.validate("abcdefgh");
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate("abcdefghij");
		assertFalse(result.isError());
		assertEquals("abcdefghij", result.get());

		result = validator.validate("abcdefghijk");
		assertFalse(result.isError());
		assertEquals("abcdefghijk", result.get());
	}

	@Test
	public void testCollectionIsNotEmpty() {
		String message = "Collection is empty!";
		Validator<Collection<String>> validator = ValidatorRules.collectionIsNotEmpty(message);

		Result<Collection<String>> result = validator.validate(List.of());
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		Collection<String> list = List.of("a", "b", "c");
		result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());
	}

	@Test
	public void testCollectionIsEmpty() {
		String message = "Collection is not empty!";
		Validator<Collection<String>> validator = ValidatorRules.collectionIsEmpty(message);

		Collection<String> list = List.of("a", "b", "c");
		Result<Collection<String>> result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate(List.of());
		assertFalse(result.isError());
		assertEquals(List.of(), result.get());
	}

	@Test
	public void testCollectionSizeIsGreaterThan() {
		String message = "Collection size is not greater than the specified size!";
		int size = 5;
		Validator<Collection<String>> validator = ValidatorRules.collectionSizeIsGreaterThan(size, message);

		Collection<String> list = List.of("a", "b", "c", "d");
		Result<Collection<String>> result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		list = List.of("a", "b", "c", "d", "e");
		result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		list = List.of("a", "b", "c", "d", "e", "f");
		result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());
	}

	@Test
	public void testCollectionSizeIsGreaterThanOrEqualTo() {
		String message = "Collection size is not greater than or equal to the specified size!";
		int size = 5;
		Validator<Collection<String>> validator = ValidatorRules.collectionSizeIsGreaterThanOrEqualTo(size, message);

		Collection<String> list = List.of("a", "b", "c", "d");
		Result<Collection<String>> result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		list = List.of("a", "b", "c", "d", "e");
		result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());

		list = List.of("a", "b", "c", "d", "e", "f");
		result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());
	}

	@Test
	public void testCollectionSizeIsLessThan() {
		String message = "Collection size is not less than the specified size!";
		int size = 5;
		Validator<Collection<String>> validator = ValidatorRules.collectionSizeIsLessThan(size, message);

		Collection<String> list = List.of("a", "b", "c", "d");
		Result<Collection<String>> result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());

		list = List.of("a", "b", "c", "d", "e");
		result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		list = List.of("a", "b", "c", "d", "e", "f");
		result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testCollectionSizeIsLessThanOrEqualTo() {
		String message = "Collection size is not less than or equal to the specified size!";
		int size = 5;
		Validator<Collection<String>> validator = ValidatorRules.collectionSizeIsLessThanOrEqualTo(size, message);

		Collection<String> list = List.of("a", "b", "c", "d");
		Result<Collection<String>> result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());

		list = List.of("a", "b", "c", "d", "e");
		result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());

		list = List.of("a", "b", "c", "d", "e", "f");
		result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testCollectionSizeIsEqualTo() {
		String message = "Collection size is not equal to the specified size!";
		int size = 5;
		Validator<Collection<String>> validator = ValidatorRules.collectionSizeIsEqualTo(size, message);

		Collection<String> list = List.of("a", "b", "c", "d");
		Result<Collection<String>> result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		list = List.of("a", "b", "c", "d", "e");
		result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());

		list = List.of("a", "b", "c", "d", "e", "f");
		result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testCollectionSizeIsBetweenExclusive() {
		String message = "Collection size is not between the specified sizes!";
		int min = 5;
		int max = 10;
		Validator<Collection<String>> validator = ValidatorRules.collectionSizeIsBetweenExclusive(min, max, message);

		Collection<String> list = List.of("a", "b", "c", "d");
		Result<Collection<String>> result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		list = List.of("a", "b", "c", "d", "e");
		result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		list = List.of("a", "b", "c", "d", "e", "f", "g", "h");
		result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());

		list = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
		result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		list = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k");
		result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testCollectionSizeIsBetweenInclusive() {
		String message = "Collection size is not between the specified sizes!";
		int min = 5;
		int max = 10;
		Validator<Collection<String>> validator = ValidatorRules.collectionSizeIsBetweenInclusive(min, max, message);

		Collection<String> list = List.of("a", "b", "c", "d");
		Result<Collection<String>> result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		list = List.of("a", "b", "c", "d", "e");
		result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());

		list = List.of("a", "b", "c", "d", "e", "f", "g", "h");
		result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());

		list = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
		result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());

		list = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k");
		result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testCollectionSizeIsOutsideExclusive() {
		String message = "Collection size is not outside the specified sizes!";
		int min = 5;
		int max = 10;
		Validator<Collection<String>> validator = ValidatorRules.collectionSizeIsOutsideExclusive(min, max, message);

		Collection<String> list = List.of("a", "b", "c", "d");
		Result<Collection<String>> result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());

		list = List.of("a", "b", "c", "d", "e");
		result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		list = List.of("a", "b", "c", "d", "e", "f", "g", "h");
		result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		list = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
		result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		list = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k");
		result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());
	}

	@Test
	public void testCollectionSizeIsOutsideInclusive() {
		String message = "Collection size is not outside the specified sizes!";
		int min = 5;
		int max = 10;
		Validator<Collection<String>> validator = ValidatorRules.collectionSizeIsOutsideInclusive(min, max, message);

		Collection<String> list = List.of("a", "b", "c", "d");
		Result<Collection<String>> result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());

		list = List.of("a", "b", "c", "d", "e");
		result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());

		list = List.of("a", "b", "c", "d", "e", "f", "g", "h");
		result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		list = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
		result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());

		list = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k");
		result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());
	}

	@Test
	public void testCollectionContains() {
		String message = "Collection does not contain the specified value!";
		String value = "a";
		Validator<Collection<String>> validator = ValidatorRules.collectionContains(value, message);

		Collection<String> list = List.of("b", "c", "d");
		Result<Collection<String>> result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		list = List.of("a", "b", "c", "d");
		result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());
	}

	@Test
	public void testCollectionDoesNotContain() {
		String message = "Collection contains the specified value!";
		String value = "a";
		Validator<Collection<String>> validator = ValidatorRules.collectionDoesNotContain(value, message);

		Collection<String> list = List.of("a", "b", "c", "d");
		Result<Collection<String>> result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		list = List.of("b", "c", "d");
		result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());
	}

	@Test
	public void testCollectionContainsAll() {
		String message = "Collection does not contain all of the specified values!";
		Collection<String> values = List.of("a", "b", "c");
		Validator<Collection<String>> validator = ValidatorRules.collectionContainsAll(values, message);

		Collection<String> list = List.of("a", "b", "c", "d");
		Result<Collection<String>> result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());

		list = List.of("a", "b", "d");
		result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testCollectionContainsAny() {
		String message = "Collection does not contain any of the specified values!";
		Collection<String> values = List.of("a", "b", "c");
		Validator<Collection<String>> validator = ValidatorRules.collectionContainsAny(values, message);

		Collection<String> list = List.of("a", "b", "c", "d");
		Result<Collection<String>> result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());

		list = List.of("d", "e", "f");
		result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testCollectionContainsNone() {
		String message = "Collection contains one or more of the specified values!";
		Collection<String> values = List.of("a", "b", "c");
		Validator<Collection<String>> validator = ValidatorRules.collectionContainsNone(values, message);

		Collection<String> list = List.of("d", "e", "f");
		Result<Collection<String>> result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());

		list = List.of("a", "b", "c", "d");
		result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testCollectionAllMatch() {
		String message = "Collection does not match the specified predicate!";
		Predicate<String> predicate = s -> s.length() > 3;
		Validator<Collection<String>> validator = ValidatorRules.collectionAllMatch(predicate, message);

		Collection<String> list = List.of("aaaa", "bbbb", "cccc", "dddd");
		Result<Collection<String>> result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());

		list = List.of("aaaa", "bbbb", "cccc", "ddd");
		result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testCollectionAnyMatch() {
		String message = "Collection does not match the specified predicate!";
		Predicate<String> predicate = s -> s.length() > 3;
		Validator<Collection<String>> validator = ValidatorRules.collectionAnyMatch(predicate, message);

		Collection<String> list = List.of("aaaa", "bbbb", "cccc", "dddd");
		Result<Collection<String>> result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());

		list = List.of("aaa", "bbb", "ccc", "ddd");
		result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testCollectionNoneMatch() {
		String message = "Collection matches the specified predicate!";
		Predicate<String> predicate = s -> s.length() > 3;
		Validator<Collection<String>> validator = ValidatorRules.collectionNoneMatch(predicate, message);

		Collection<String> list = List.of("aaa", "bbb", "ccc", "ddd");
		Result<Collection<String>> result = validator.validate(list);
		assertFalse(result.isError());
		assertEquals(list, result.get());

		list = List.of("aaaa", "bbbb", "cccc", "dddd");
		result = validator.validate(list);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testIsTrue() {
		String message = "Value is not true!";
		Validator<Boolean> validator = ValidatorRules.isTrue(false, message);

		Result<Boolean> result = validator.validate(true);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		validator = ValidatorRules.isTrue(true, message);
		result = validator.validate(true);
		assertFalse(result.isError());
		assertEquals(true, result.get());
	}

	@Test
	public void testIsFalse() {
		String message = "Value is not false!";
		Validator<Boolean> validator = ValidatorRules.isFalse(true, message);

		Result<Boolean> result = validator.validate(false);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		validator = ValidatorRules.isFalse(false, message);
		result = validator.validate(false);
		assertFalse(result.isError());
		assertEquals(false, result.get());
	}

	@Test
	public void testIsGreaterThan() {
		String message = "Value is not greater than 5!";
		Validator<Integer> validator = ValidatorRules.isGreaterThan(5, message);

		Result<Integer> result = validator.validate(6);
		assertFalse(result.isError());
		assertEquals(6, result.get().intValue());

		result = validator.validate(5);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate(4);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testIsGreaterThanOrEqualTo() {
		String message = "Value is not greater than or equal to 5!";
		Validator<Integer> validator = ValidatorRules.isGreaterThanOrEqualTo(5, message);

		Result<Integer> result = validator.validate(6);
		assertFalse(result.isError());
		assertEquals(6, result.get().intValue());

		result = validator.validate(5);
		assertFalse(result.isError());
		assertEquals(5, result.get().intValue());

		result = validator.validate(4);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testIsLessThan() {
		String message = "Value is not less than 5!";
		Validator<Integer> validator = ValidatorRules.isLessThan(5, message);

		Result<Integer> result = validator.validate(4);
		assertFalse(result.isError());
		assertEquals(4, result.get().intValue());

		result = validator.validate(5);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate(6);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testIsLessThanOrEqualTo() {
		String message = "Value is not less than or equal to 5!";
		Validator<Integer> validator = ValidatorRules.isLessThanOrEqualTo(5, message);

		Result<Integer> result = validator.validate(4);
		assertFalse(result.isError());
		assertEquals(4, result.get().intValue());

		result = validator.validate(5);
		assertFalse(result.isError());
		assertEquals(5, result.get().intValue());

		result = validator.validate(6);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testIsInRange() {
		String message = "Value is not in range!";
		Range<Integer> range = new Range<>(5, 10);
		Validator<Integer> validator = ValidatorRules.isInRange(range, message);

		Result<Integer> result = validator.validate(4);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate(5);
		assertFalse(result.isError());
		assertEquals(5, result.get().intValue());

		result = validator.validate(6);
		assertFalse(result.isError());
		assertEquals(6, result.get().intValue());

		result = validator.validate(8);
		assertFalse(result.isError());
		assertEquals(8, result.get().intValue());

		result = validator.validate(10);
		assertFalse(result.isError());
		assertEquals(10, result.get().intValue());

		result = validator.validate(11);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());
	}

	@Test
	public void testIsNotInRange() {
		String message = "Value is in range!";
		Range<Integer> range = new Range<>(5, 10);
		Validator<Integer> validator = ValidatorRules.isNotInRange(range, message);

		Result<Integer> result = validator.validate(4);
		assertFalse(result.isError());
		assertEquals(4, result.get().intValue());

		result = validator.validate(5);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate(6);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate(8);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate(10);
		assertTrue(result.isError());
		assertEquals(message, result.getError().getMessage());

		result = validator.validate(11);
		assertFalse(result.isError());
		assertEquals(11, result.get().intValue());
	}
}