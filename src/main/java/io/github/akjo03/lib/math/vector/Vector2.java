package io.github.akjo03.lib.math.vector;

import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Getter
@SuppressWarnings("unused")
public final class Vector2 {
	private static final double EPSILON = 0.00001;

	public static final Vector2 ZERO = new Vector2(0, 0);
	public static final Vector2 ONE = new Vector2(1, 1);
	public static final Vector2 UP = new Vector2(0, 1);
	public static final Vector2 DOWN = new Vector2(0, -1);
	public static final Vector2 LEFT = new Vector2(-1, 0);
	public static final Vector2 RIGHT = new Vector2(1, 0);
	public static final Vector2 POSITIVE_INFINITY = new Vector2(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
	public static final Vector2 NEGATIVE_INFINITY = new Vector2(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

	private final double x;
	private final double y;

	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Vector2 v)) {
			return false;
		}
		return Math.abs(x - v.x) < EPSILON && Math.abs(y - v.y) < EPSILON;
	}

	@Override
	public int hashCode() {
		return Double.hashCode(x) ^ Double.hashCode(y);
	}

	@Override
	public @NotNull String toString() {
		String x = String.format("%,.2f", this.x);
		String y = String.format("%,.2f", this.y);
		return "Vector2(" + x + ", " + y + ")";
	}

	@Contract(value = "_ -> new", pure = true)
	public @NotNull Vector2 add(@NotNull Vector2 v) {
		return new Vector2(x + v.x, y + v.y);
	}

	@Contract(value = "_ -> new", pure = true)
	public @NotNull Vector2 subtract(@NotNull Vector2 v) {
		return new Vector2(x - v.x, y - v.y);
	}

	@Contract(value = "_ -> new", pure = true)
	public @NotNull Vector2 multiply(@NotNull Vector2 v) {
		return new Vector2(x * v.x, y * v.y);
	}

	@Contract("_ -> new")
	public @NotNull Vector2 divide(@NotNull Vector2 v) {
		if (v.x == 0 || v.y == 0) {
			throw new ArithmeticException("Cannot divide by zero!");
		}
		return new Vector2(x / v.x, y / v.y);
	}

	@Contract(value = "_ -> new", pure = true)
	public @NotNull Vector2 multiply(double scalar) {
		return new Vector2(x * scalar, y * scalar);
	}

	@Contract("_ -> new")
	public @NotNull Vector2 divide(double scalar) {
		if (scalar == 0) {
			throw new ArithmeticException("Cannot divide by zero!");
		}
		return new Vector2(x / scalar, y / scalar);
	}

	public boolean isApproximately(@NotNull Vector2 v, double epsilon) {
		return Math.abs(x - v.x) < epsilon && Math.abs(y - v.y) < epsilon;
	}

	public boolean isApproximately(@NotNull Vector2 v) {
		return isApproximately(v, EPSILON);
	}

	public double getMagnitude() {
		return Math.sqrt(x * x + y * y);
	}

	public double getMagnitudeSquared() {
		return x * x + y * y;
	}

	public Vector2 getNormalized() {
		double magnitude = getMagnitude();
		if (magnitude == 0) {
			return ZERO;
		}
		return new Vector2(x / magnitude, y / magnitude);
	}

	public static double angle(@NotNull Vector2 from, @NotNull Vector2 to) {
		double dotProduct = dot(from, to);
		double magnitudes = Math.sqrt(from.getMagnitudeSquared() * to.getMagnitudeSquared());
		return Math.acos(dotProduct / magnitudes);
	}

	public static @NotNull Vector2 clampMagnitude(@NotNull Vector2 vector, double maxLength) {
		double magnitude = vector.getMagnitude();
		if (magnitude > maxLength) {
			return vector.getNormalized().multiply(maxLength);
		}
		return vector;
	}

	public static double distance(@NotNull Vector2 a, @NotNull Vector2 b) {
		return a.subtract(b).getMagnitude();
	}

	public static double dot(@NotNull Vector2 a, @NotNull Vector2 b) {
		return a.x * b.x + a.y * b.y;
	}

	public static @NotNull Vector2 lerp(@NotNull Vector2 a, @NotNull Vector2 b, double t) {
		if (t < 0) t = 0;
		if (t > 1) t = 1;
		return lerpUnclamped(a, b, t);
	}

	@Contract("_, _, _ -> new")
	public static @NotNull Vector2 lerpUnclamped(@NotNull Vector2 a, @NotNull Vector2 b, double t) {
		return a.add(b.subtract(a).multiply(t));
	}

	@Contract("_, _ -> new")
	public static @NotNull Vector2 max(@NotNull Vector2 a, @NotNull Vector2 b) {
		return new Vector2(
				Math.max(a.x, b.x),
				Math.max(a.y, b.y)
		);
	}

	@Contract("_, _ -> new")
	public static @NotNull Vector2 min(@NotNull Vector2 a, @NotNull Vector2 b) {
		return new Vector2(
				Math.min(a.x, b.x),
				Math.min(a.y, b.y)
		);
	}

	@Contract("_, _ -> new")
	public static @NotNull Vector2 scale(@NotNull Vector2 a, @NotNull Vector2 b) {
		return new Vector2(
				a.x * b.x,
				a.y * b.y
		);
	}
}
