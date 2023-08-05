package io.github.akjo03.lib.math.vector;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2Test {
	@Test
	public void testConstruction() {
		Vector2 vector = new Vector2(3, 4);
		assertEquals(3, vector.getX());
		assertEquals(4, vector.getY());
	}

	@Test
	public void testPredefinedVectors() {
		assertEquals(new Vector2(0, 0), Vector2.ZERO);
		assertEquals(new Vector2(1, 1), Vector2.ONE);
		assertEquals(new Vector2(0, 1), Vector2.UP);
		assertEquals(new Vector2(0, -1), Vector2.DOWN);
		assertEquals(new Vector2(-1, 0), Vector2.LEFT);
		assertEquals(new Vector2(1, 0), Vector2.RIGHT);
		assertEquals(new Vector2(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY), Vector2.POSITIVE_INFINITY);
		assertEquals(new Vector2(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY), Vector2.NEGATIVE_INFINITY);
	}

	@Test
	public void testEqualsAndHashCode() {
		Vector2 v1 = new Vector2(3, 4);
		Vector2 v2 = new Vector2(3, 4);
		Vector2 v3 = new Vector2(4, 3);

		assertEquals(v1, v2);
		assertNotEquals(v1, v3);
		assertEquals(v1.hashCode(), v2.hashCode());
		assertNotEquals(v1.hashCode(), v3.hashCode());
	}

	@Test
	public void testToString() {
		Vector2 v = new Vector2(3.14, 4.15);
		assertEquals("Vector2(3.14, 4.15)", v.toString());
	}

	@Test
	public void testAdd() {
		Vector2 v1 = new Vector2(3, 4);
		Vector2 v2 = new Vector2(4, 3);
		Vector2 v3 = new Vector2(7, 7);
		assertEquals(v3, v1.add(v2));
	}

	@Test
	public void testSubtract() {
		Vector2 v1 = new Vector2(3, 4);
		Vector2 v2 = new Vector2(4, 3);
		Vector2 v3 = new Vector2(-1, 1);
		assertEquals(v3, v1.subtract(v2));
	}

	@Test
	public void testMultiply() {
		Vector2 v1 = new Vector2(3, 4);
		Vector2 v2 = new Vector2(4, 3);
		Vector2 v3 = new Vector2(12, 12);
		assertEquals(v3, v1.multiply(v2));
	}

	@Test
	public void testDivide() {
		Vector2 v1 = new Vector2(3, 4);
		Vector2 v2 = new Vector2(4, 3);
		Vector2 v3 = new Vector2(0.75, 1.3333333333333333);
		assertEquals(v3, v1.divide(v2));
		assertThrows(ArithmeticException.class, () -> v1.divide(Vector2.ZERO));
	}

	@Test
	public void testMultiplyScalar() {
		Vector2 v1 = new Vector2(3, 4);
		Vector2 v2 = new Vector2(6, 8);
		assertEquals(v2, v1.multiply(2));
	}

	@Test
	public void testDivideScalar() {
		Vector2 v1 = new Vector2(3, 4);
		Vector2 v2 = new Vector2(1.5, 2);
		assertEquals(v2, v1.divide(2));
		assertThrows(ArithmeticException.class, () -> v1.divide(0));
	}

	@Test
	public void testIsApproximately() {
		Vector2 v1 = new Vector2(3, 4);
		Vector2 v2 = new Vector2(3.000001, 4.000001);
		Vector2 v3 = new Vector2(3.1, 4.1);
		assertTrue(v1.isApproximately(v2));
		assertFalse(v1.isApproximately(v3));
	}

	@Test
	public void testGetMagnitude() {
		Vector2 v1 = new Vector2(3, 4);
		assertEquals(5, v1.getMagnitude());
	}

	@Test
	public void testGetMagnitudeSquared() {
		Vector2 v1 = new Vector2(3, 4);
		assertEquals(25, v1.getMagnitudeSquared());
	}

	@Test
	public void testGetNormalized() {
		Vector2 v1 = new Vector2(3, 4);
		Vector2 v2 = new Vector2(0.6, 0.8);
		assertEquals(v2, v1.getNormalized());
		assertEquals(Vector2.ZERO, Vector2.ZERO.getNormalized());
	}

	@Test
	public void testToVector3() {
		Vector2 v1 = new Vector2(3, 4);
		Vector3 v2 = new Vector3(3, 4, 0);
		assertEquals(v2, v1.toVector3());
	}

	@Test
	public void testAngle() {
		Vector2 v1 = new Vector2(3, 4);
		Vector2 v2 = new Vector2(1, 0);
		Vector2 v3 = new Vector2(0, 1);
		assertEquals(0, Vector2.angle(v1, v1));
		assertEquals(Math.PI / 2, Vector2.angle(v2, v3));
		assertTrue(Double.isNaN(Vector2.angle(v1, Vector2.ZERO)));
	}

	@Test
	public void testClampMagnitude() {
		Vector2 v = new Vector2(3, 4);
		Vector2 clamped = Vector2.clampMagnitude(v, 4);
		assertEquals(new Vector2(2.4, 3.2), clamped);
	}

	@Test
	public void testDistance() {
		Vector2 a = new Vector2(1, 2);
		Vector2 b = new Vector2(4, 6);
		assertEquals(5, Vector2.distance(a, b), 0.0001);
	}

	@Test
	public void testDot() {
		Vector2 a = new Vector2(2, 3);
		Vector2 b = new Vector2(4, 5);
		assertEquals(23, Vector2.dot(a, b), 0.0001);
	}

	@Test
	public void testLerp() {
		Vector2 a = new Vector2(1, 2);
		Vector2 b = new Vector2(4, 6);
		Vector2 lerp = Vector2.lerp(a, b, 0.5);
		assertEquals(new Vector2(2.5, 4), lerp);
		assertEquals(a, Vector2.lerp(a, b, -0.1));
		assertEquals(b, Vector2.lerp(a, b, 1.1));
	}

	@Test
	public void testLerpUnclamped() {
		Vector2 a = new Vector2(1, 2);
		Vector2 b = new Vector2(4, 6);
		Vector2 v1 = new Vector2(-0.5, 0);
		Vector2 v2 = new Vector2(5.5, 8);

		assertEquals(v1, Vector2.lerpUnclamped(a, b,  -0.5));
		assertEquals(v2, Vector2.lerpUnclamped(a, b, 1.5));
	}

	@Test
	public void testMax() {
		Vector2 a = new Vector2(1, 4);
		Vector2 b = new Vector2(3, 2);
		assertEquals(new Vector2(3, 4), Vector2.max(a, b));
	}

	@Test
	public void testMin() {
		Vector2 a = new Vector2(1, 4);
		Vector2 b = new Vector2(3, 2);
		assertEquals(new Vector2(1, 2), Vector2.min(a, b));
	}

	@Test
	public void testScale() {
		Vector2 a = new Vector2(2, 3);
		Vector2 b = new Vector2(4, 5);
		assertEquals(new Vector2(8, 15), Vector2.scale(a, b));
	}
}