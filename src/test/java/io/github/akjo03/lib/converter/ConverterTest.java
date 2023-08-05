package io.github.akjo03.lib.converter;

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
		Integer result = stringIntegerConverter.convertForward(str);

		assertEquals(expected, result);
	}

	@Test
	void convertBackward() {
		Integer num = 123;
		String expected = "123";
		String result = stringIntegerConverter.convertBackward(num);

		assertEquals(expected, result);
	}

	@Test
	void convertForwardList() {
		List<String> strList = List.of("123", "456", "789");
		List<Integer> expected = List.of(123, 456, 789);
		List<Integer> result = stringIntegerConverter.convertForward(strList);

		assertEquals(expected, result);
	}

	@Test
	void convertBackwardList() {
		List<Integer> numList = List.of(123, 456, 789);
		List<String> expected = List.of("123", "456", "789");
		List<String> result = stringIntegerConverter.convertBackward(numList);

		assertEquals(expected, result);
	}
}