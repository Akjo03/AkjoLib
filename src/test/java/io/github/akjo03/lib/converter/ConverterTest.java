package io.github.akjo03.lib.converter;

import io.github.akjo03.lib.result.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest {
	private Converter<String, Integer> stringIntegerConverter;

	@BeforeEach
	void setUp() {
		Function<String, Integer> forwardFunction = Integer::parseInt;
		Function<Integer, String> backwardFunction = Object::toString;
		stringIntegerConverter = Converter.of(forwardFunction, backwardFunction);
	}

	@Test
	void convertForward() {
		String str = "123";
		Integer expected = 123;
		Result<Integer> result = stringIntegerConverter.convertForward(str);

		assertEquals(expected, result.getOrElse(-1));
	}

	@Test
	void convertBackward() {
		Integer num = 123;
		String expected = "123";
		Result<String> result = stringIntegerConverter.convertBackward(num);

		assertEquals(expected, result.getOrElse(null));
	}

	@Test
	void convertForwardList() {
		List<String> strList = List.of("123", "456", "789");
		List<Integer> expected = List.of(123, 456, 789);
		List<Integer> result = stringIntegerConverter.convertForward(strList).aggregateAll(Integer.class).get();

		assertEquals(expected, result);
	}

	@Test
	void convertBackwardList() {
		List<Integer> numList = List.of(123, 456, 789);
		List<String> expected = List.of("123", "456", "789");
		List<String> result = stringIntegerConverter.convertBackward(numList).aggregateAll(String.class).get();

		assertEquals(expected, result);
	}

	@Test
	void testThenChaining() {
		Converter<Integer, Double> intToDoubleConverter = Converter.of(Integer::doubleValue, Double::intValue);
		Converter<String, Double> combinedConverter = stringIntegerConverter.then(intToDoubleConverter);

		String str = "123";
		Double expected = 123.0;
		Result<Double> result = combinedConverter.convertForward(str);

		assertEquals(expected, result.getOrElse(-1.0));
	}

	@Test
	void testComposeChaining() {
		Converter<Integer, Double> intToDoubleConverter = Converter.of(Integer::doubleValue, Double::intValue);
		Converter<String, Double> combinedConverter = intToDoubleConverter.compose(stringIntegerConverter);

		String str = "123";
		Double expected = 123.0;
		Result<Double> result = combinedConverter.convertForward(str);

		assertEquals(expected, result.getOrElse(-1.0));
	}
}