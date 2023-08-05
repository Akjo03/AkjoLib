package io.github.akjo03.lib.math;

import io.github.akjo03.lib.result.Result;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class RangeTest {
	@Test
	public void testCalculateMinAndMax() {
		Range<Integer> range = new Range<>(5, 3);
		assertEquals(3, range.getMin());
		assertEquals(5, range.getMax());
	}

	@Test
	public void testContains() {
		Range<Integer> range = new Range<>(3, 5);
		assertTrue(range.contains(4));
		assertFalse(range.contains(2));
		assertFalse(range.contains(6));
	}

	@Test
	public void testContainsRange() {
		Range<Integer> range = new Range<>(3, 5);
		assertTrue(range.contains(new Range<>(4, 5)));
		assertFalse(range.contains(new Range<>(2, 3)));
		assertFalse(range.contains(new Range<>(5, 6)));
	}

	@Test
	void testCheckRange() {
		Range<Integer> range = new Range<>(3, 5);
		Supplier<Result<String>> below = () -> Result.success("Below range");
		Supplier<Result<String>> above = () -> Result.success("Above range");
		Result<String> defaultValue = Result.success("In range");

		assertEquals("Below range", range.checkRange(2, below, above, defaultValue).get());
		assertEquals("Above range", range.checkRange(6, below, above, defaultValue).get());
		assertEquals("In range", range.checkRange(4, below, above, defaultValue).get());
	}
}