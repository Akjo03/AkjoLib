package io.github.akjo03.lib.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class FilteringSetTest {
	private Set<Integer> original;
	private FilteringSet<Integer> filteringSet;

	@BeforeEach
	public void setUp() {
		original = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
		Set<Integer> filters = new HashSet<>(Arrays.asList(2, 4));
		filteringSet = FilteringSet.of(original, filters);
	}

	@Test
	public void testOf_withFiltersNotInOriginal_shouldThrowException() {
		Set<Integer> faultyFilters = new HashSet<>(Arrays.asList(7, 8));
		assertThrows(IllegalArgumentException.class, () -> FilteringSet.of(original, faultyFilters));
	}

	@Test
	public void testSize() {
		assertEquals(3, filteringSet.size());
	}

	@Test
	@SuppressWarnings({"UseBulkOperation", "WhileLoopReplaceableByForEach"})
	public void testIterator_hasNextAndNext() {
		Set<Integer> expectedElements = new HashSet<>(Arrays.asList(1, 3, 5));
		Set<Integer> actualElements = new HashSet<>();
		var iterator = filteringSet.iterator();
		while (iterator.hasNext()) {
			actualElements.add(iterator.next());
		}
		assertEquals(expectedElements, actualElements);
	}

	@Test
	public void testIterator_nextWithoutHasNext_shouldThrowException() {
		var iterator = filteringSet.iterator();
		while (iterator.hasNext()) {
			iterator.next();
		}
		assertThrows(NoSuchElementException.class, iterator::next);
	}

	@Test
	@SuppressWarnings("UseBulkOperation")
	public void testForEach() {
		Set<Integer> expectedElements = new HashSet<>(Arrays.asList(1, 3, 5));
		Set<Integer> actualElements = new HashSet<>();
		filteringSet.forEach(actualElements::add);
		assertEquals(expectedElements, actualElements);
	}

	@Test
	public void testContains() {
		assertTrue(filteringSet.contains(1));
		assertFalse(filteringSet.contains(2));
	}

	@Test
	public void testToArray() {
		Object[] expectedArray = {1, 3, 5};
		assertArrayEquals(expectedArray, filteringSet.toArray());
	}

	@Test
	public void testToArray_withGivenArray() {
		Integer[] array = new Integer[5];
		Integer[] expectedArray = {1, 3, 5, null, null};
		assertArrayEquals(expectedArray, filteringSet.toArray(array));
	}

	@Test
	public void testEquals() {
		Set<Integer> otherSet = new HashSet<>(Arrays.asList(1, 3, 5));
		assertEquals(filteringSet, otherSet);
	}

	@Test
	public void testHashCode() {
		Set<Integer> otherSetElements = new HashSet<>(Arrays.asList(1, 3, 5));
		Set<Integer> otherSetFilters = new HashSet<>();
		FilteringSet<Integer> otherSet = FilteringSet.of(otherSetElements, otherSetFilters);
		assertEquals(otherSet.hashCode(), filteringSet.hashCode());
	}

	@Test
	public void testToString() {
		String expectedString = "{1, 3, 5}";
		assertEquals(expectedString, filteringSet.toString());
	}

	@Test
	@SuppressWarnings("SimplifyStreamApiCallChains")
	public void testStream() {
		Set<Integer> expectedSet = new HashSet<>(Arrays.asList(1, 3, 5));
		Set<Integer> actualSet = filteringSet.stream().collect(Collectors.toSet());
		assertEquals(expectedSet, actualSet);
	}

	@Test
	public void testParallelStream() {
		Set<Integer> expectedSet = new HashSet<>(Arrays.asList(1, 3, 5));
		Set<Integer> actualSet = filteringSet.parallelStream().collect(Collectors.toSet());
		assertEquals(expectedSet, actualSet);
	}
}