package io.github.akjo03.lib.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringArr2Test {
	@Test
	public void testCreation() {
		StringArr2 arr = new StringArr2("hello", "world");
		assertNotNull(arr);
		assertEquals("hello", arr.getFirst());
		assertEquals("world", arr.getSecond());
	}

	@Test
	public void testToString() {
		StringArr2 arr = new StringArr2("hello", "world");
		assertEquals("[1: hello, 2: world]", arr.toString());
	}

	@Test
	public void testEquals() {
		StringArr2 arr1 = new StringArr2("hello", "world");
		StringArr2 arr2 = new StringArr2("hello", "world");
		assertEquals(arr1, arr2);
	}

	@Test
	public void testNotEquals() {
		StringArr2 arr1 = new StringArr2("hello", "world");
		StringArr2 arr2 = new StringArr2("hi", "earth");
		assertNotEquals(arr1, arr2);
	}

	@Test
	public void testHashCode() {
		StringArr2 arr1 = new StringArr2("hello", "world");
		StringArr2 arr2 = new StringArr2("hello", "world");
		assertEquals(arr1.hashCode(), arr2.hashCode());
	}

	@Test
	public void testHashCodeNotEquals() {
		StringArr2 arr1 = new StringArr2("hello", "world");
		StringArr2 arr2 = new StringArr2("hi", "earth");
		assertNotEquals(arr1.hashCode(), arr2.hashCode());
	}
}