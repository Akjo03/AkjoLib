package io.github.akjo03.lib.math.vector;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector3Test {
	@Test
	public void testConstruction() {
		Vector3 vector = new Vector3(3, 4, 5);
		assertEquals(3, vector.getX());
		assertEquals(4, vector.getY());
		assertEquals(5, vector.getZ());

		Vector3 vector2 = new Vector3(3, 4);
		assertEquals(3, vector2.getX());
		assertEquals(4, vector2.getY());
		assertEquals(0, vector2.getZ());
	}

	@Test
	public void testPredefinedVectors() {
		assertEquals(new Vector3(0, 0, 0), Vector3.ZERO);
		assertEquals(new Vector3(1, 1, 1), Vector3.ONE);
		assertEquals(new Vector3(0, 1, 0), Vector3.UP);
		assertEquals(new Vector3(0, -1, 0), Vector3.DOWN);
		assertEquals(new Vector3(-1, 0, 0), Vector3.LEFT);
		assertEquals(new Vector3(1, 0, 0), Vector3.RIGHT);
		assertEquals(new Vector3(0, 0, 1), Vector3.FORWARD);
		assertEquals(new Vector3(0, 0, -1), Vector3.BACKWARD);
		assertEquals(new Vector3(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY), Vector3.POSITIVE_INFINITY);
		assertEquals(new Vector3(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY), Vector3.NEGATIVE_INFINITY);
	}

	@Test
	public void testEqualsAndHashCode() {
		Vector3 v1 = new Vector3(3, 4, 5);
		Vector3 v2 = new Vector3(3, 4, 5);
		Vector3 v3 = new Vector3(4, 3, 5);

		assertEquals(v1, v2);
		assertNotEquals(v1, v3);
		assertEquals(v1.hashCode(), v2.hashCode());
		assertNotEquals(v1.hashCode(), v3.hashCode());
	}

	@Test
	public void testToString() {
		Vector3 v = new Vector3(3.14, 4.15, 5.16);
		assertEquals("Vector3(3.14, 4.15, 5.16)", v.toString());
	}

	@Test
	public void testAdd() {
		Vector3 v1 = new Vector3(3, 4, 5);
		Vector3 v2 = new Vector3(4, 3, 5);
		Vector3 v3 = new Vector3(7, 7, 10);
		assertEquals(v3, v1.add(v2));
	}

	@Test
	public void testSubtract() {
		Vector3 v1 = new Vector3(3, 4, 5);
		Vector3 v2 = new Vector3(4, 3, 5);
		Vector3 v3 = new Vector3(-1, 1, 0);
		assertEquals(v3, v1.subtract(v2));
	}

	@Test
	public void testMultiply() {
		Vector3 v1 = new Vector3(3, 4, 5);
		Vector3 v2 = new Vector3(4, 3, 5);
		Vector3 v3 = new Vector3(12, 12, 25);
		assertEquals(v3, v1.multiply(v2));
	}

	@Test
	public void testDivide() {
		Vector3 v1 = new Vector3(3, 4, 5);
		Vector3 v2 = new Vector3(4, 3, 5);
		Vector3 v3 = new Vector3(0.75, 1.3333333333333333, 1);
		assertEquals(v3, v1.divide(v2));
		assertThrows(ArithmeticException.class, () -> v1.divide(Vector3.ZERO));
	}

	@Test
	public void testMultiplyScalar() {
		Vector3 v1 = new Vector3(3, 4, 5);
		Vector3 v2 = new Vector3(6, 8, 10);
		assertEquals(v2, v1.multiply(2));
	}

	@Test
	public void testDivideScalar() {
		Vector3 v1 = new Vector3(3, 4, 5);
		Vector3 v2 = new Vector3(1.5, 2, 2.5);
		assertEquals(v2, v1.divide(2));
		assertThrows(ArithmeticException.class, () -> v1.divide(0));
	}

	@Test
	public void testIsApproximately() {
		Vector3 v1 = new Vector3(3, 4, 5);
		Vector3 v2 = new Vector3(3.000001, 4.000001, 5.000001);
		Vector3 v3 = new Vector3(3.1, 4.1, 5.1);
		assertTrue(v1.isApproximately(v2));
		assertFalse(v1.isApproximately(v3));
	}

	@Test
	public void testGetMagnitude() {
		Vector3 v1 = new Vector3(3, 4, 5);
		assertEquals(7.0710678118654755, v1.getMagnitude(), 0.0000000000000001);
	}

	@Test
	public void testGetMagnitudeSquared() {
		Vector3 v1 = new Vector3(3, 4, 5);
		assertEquals(50, v1.getMagnitudeSquared(), 0.0000000000000001);
	}

	@Test
	public void testGetNormalized() {
		Vector3 v1 = new Vector3(3, 4, 5);
		Vector3 v2 = new Vector3(0.4242640687119285, 0.565685424949238, 0.7071067811865475);
		assertEquals(v2, v1.getNormalized());
		assertEquals(Vector3.ZERO, Vector3.ZERO.getNormalized());
	}

	@Test
	public void testAngle() {
		Vector3 v1 = new Vector3(3, 4, 5);
		Vector3 v2 = new Vector3(4, 3, 5);
		assertEquals(0.20033484232311968, Vector3.angle(v1, v2), 0.0000000000000001);
		assertTrue(Double.isNaN(Vector3.angle(v1, Vector3.ZERO)));
	}

	@Test
	public void testClampMagnitude() {
		Vector3 v = new Vector3(3, 0, 4);
		Vector3 clamped = Vector3.clampMagnitude(v, 2.5);
		assertEquals(new Vector3(1.5, 0, 2), clamped);
	}

	@Test
	public void testCross() {
		Vector3 a = new Vector3(1, 0, 0);
		Vector3 b = new Vector3(0, 1, 0);
		Vector3 c = Vector3.cross(a, b);
		assertEquals(new Vector3(0, 0, 1), c);

		Vector3 d = new Vector3(2, 3, 4);
		Vector3 e = new Vector3(5, 6, 7);
		Vector3 f = Vector3.cross(d, e);
		assertEquals(new Vector3(-3, 6, -3), f);
	}

	@Test
	public void testDistance() {
		Vector3 v1 = new Vector3(3, 4, 5);
		Vector3 v2 = new Vector3(4, 3, 5);
		assertEquals(1.4142135623730951, Vector3.distance(v1, v2), 0.0000000000000001);
	}

	@Test
	public void testDot() {
		Vector3 v1 = new Vector3(3, 4, 5);
		Vector3 v2 = new Vector3(4, 3, 5);
		assertEquals(49, Vector3.dot(v1, v2), 0.0000000000000001);
	}

	@Test
	public void testLerp() {
		Vector3 v1 = new Vector3(3, 4, 5);
		Vector3 v2 = new Vector3(4, 3, 5);
		Vector3 v3 = new Vector3(3.5, 3.5, 5);
		assertEquals(v3, Vector3.lerp(v1, v2, 0.5));
		assertEquals(v1, Vector3.lerp(v1, v2, 0));
		assertEquals(v2, Vector3.lerp(v1, v2, 1));
		assertEquals(v1, Vector3.lerp(v1, v2, -0.1));
		assertEquals(v2, Vector3.lerp(v1, v2, 1.1));
	}

	@Test
	public void testLerpUnclamped() {
		Vector3 a = new Vector3(3, 4, 5);
		Vector3 b = new Vector3(4, 3, 5);
		Vector3 v1 = new Vector3(3.5, 3.5, 5);
		Vector3 v2 = new Vector3(4.5, 2.5, 5);

		assertEquals(v1, Vector3.lerpUnclamped(a, b, 0.5));
		assertEquals(v2, Vector3.lerpUnclamped(a, b, 1.5));
	}

	@Test
	public void testMax() {
		Vector3 a = new Vector3(1, 4, 5);
		Vector3 b = new Vector3(3, 2, 3);
		assertEquals(new Vector3(3, 4, 5), Vector3.max(a, b));
	}

	@Test
	public void testMin() {
		Vector3 a = new Vector3(1, 4, 5);
		Vector3 b = new Vector3(3, 2, 3);
		assertEquals(new Vector3(1, 2, 3), Vector3.min(a, b));
	}

	@Test
	public void testScale() {
		Vector3 a = new Vector3(1, 4, 5);
		Vector3 b = new Vector3(3, 2, 3);
		assertEquals(new Vector3(3, 8, 15), Vector3.scale(a, b));
	}
}