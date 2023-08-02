package io.github.akjo03.lib.math.vector;

import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Getter
@SuppressWarnings("unused")
public final class Vector3 {
	private static final double EPSILON = 0.00001;

	public static final Vector3 ZERO = new Vector3(0, 0, 0);
	public static final Vector3 ONE = new Vector3(1, 1, 1);
	public static final Vector3 UP = new Vector3(0, 1, 0);
	public static final Vector3 DOWN = new Vector3(0, -1, 0);
	public static final Vector3 LEFT = new Vector3(-1, 0, 0);
	public static final Vector3 RIGHT = new Vector3(1, 0, 0);
	public static final Vector3 FORWARD = new Vector3(0, 0, 1);
	public static final Vector3 BACK = new Vector3(0, 0, -1);
	public static final Vector3 POSITIVE_INFINITY = new Vector3(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
	public static final Vector3 NEGATIVE_INFINITY = new Vector3(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

	private final double x;
	private final double y;
	private final double z;

	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3(double x, double y) {
		this(x, y, 0);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Vector3 v)) {
			return false;
		}
		return Math.abs(x - v.x) < EPSILON && Math.abs(y - v.y) < EPSILON && Math.abs(z - v.z) < EPSILON;
	}

	@Override
	public int hashCode() {
		return Double.hashCode(x) ^ Double.hashCode(y) ^ Double.hashCode(z);
	}

	@Override
	public @NotNull String toString() {
		String x = String.format("%,.2f", this.x);
		String y = String.format("%,.2f", this.y);
		String z = String.format("%,.2f", this.z);
		return "Vector3(" + x + ", " + y + ", " + z + ")";
	}

	@Contract(value = "_ -> new", pure = true)
	public @NotNull Vector3 add(@NotNull Vector3 v) {
		return new Vector3(x + v.x, y + v.y, z + v.z);
	}

	@Contract(value = "_ -> new", pure = true)
	public @NotNull Vector3 subtract(@NotNull Vector3 v) {
		return new Vector3(x - v.x, y - v.y, z - v.z);
	}

	@Contract(value = "_ -> new", pure = true)
	public @NotNull Vector3 multiply(@NotNull Vector3 v) {
		return new Vector3(x * v.x, y * v.y, z * v.z);
	}

	@Contract("_ -> new")
	public @NotNull Vector3 divide(@NotNull Vector3 v) {
		if (v.x == 0 || v.y == 0 || v.z == 0) {
			throw new ArithmeticException("Cannot divide by zero!");
		}
		return new Vector3(x / v.x, y / v.y, z / v.z);
	}

	@Contract(value = "_ -> new", pure = true)
	public @NotNull Vector3 multiply(double scalar) {
		return new Vector3(x * scalar, y * scalar, z * scalar);
	}

	@Contract("_ -> new")
	public @NotNull Vector3 divide(double scalar) {
		if (scalar == 0) {
			throw new ArithmeticException("Cannot divide by zero!");
		}
		return new Vector3(x / scalar, y / scalar, z / scalar);
	}

	public boolean isApproximately(@NotNull Vector3 v, double epsilon) {
		return Math.abs(x - v.x) < epsilon && Math.abs(y - v.y) < epsilon && Math.abs(z - v.z) < epsilon;
	}

	public boolean isApproximately(@NotNull Vector3 v) {
		return isApproximately(v, EPSILON);
	}

	public double getMagnitude() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	public double getMagnitudeSquared() {
		return x * x + y * y + z * z;
	}

	public Vector3 getNormalized() {
		double magnitude = getMagnitude();
		if (magnitude == 0) {
			return ZERO;
		}
		return new Vector3(x / magnitude, y / magnitude, z / magnitude);
	}

	public static double angle(@NotNull Vector3 from, @NotNull Vector3 to) {
		double dotProduct = dot(from, to);
		double magnitudes = Math.sqrt(from.getMagnitudeSquared() * to.getMagnitudeSquared());
		return Math.acos(dotProduct / magnitudes);
	}

	public static @NotNull Vector3 clampMagnitude(@NotNull Vector3 vector, double maxLength) {
		double magnitude = vector.getMagnitude();
		if (magnitude > maxLength) {
			return vector.getNormalized().multiply(new Vector3(maxLength, maxLength, maxLength));
		}
		return vector;
	}

	@Contract(value = "_, _ -> new", pure = true)
	public static @NotNull Vector3 cross(@NotNull Vector3 a, @NotNull Vector3 b) {
		return new Vector3(
				a.y * b.z - a.z * b.y,
				a.z * b.x - a.x * b.z,
				a.x * b.y - a.y * b.x
		);
	}

	public static double distance(@NotNull Vector3 a, @NotNull Vector3 b) {
		return a.subtract(b).getMagnitude();
	}

	public static double dot(@NotNull Vector3 a, @NotNull Vector3 b) {
		return a.x * b.x + a.y * b.y + a.z * b.z;
	}

	public static @NotNull Vector3 lerp(@NotNull Vector3 a, @NotNull Vector3 b, double t) {
		if (t < 0) t = 0;
		if (t > 1) t = 1;
		return lerpUnclamped(a, b, t);
	}

	@Contract("_, _, _ -> new")
	public static @NotNull Vector3 lerpUnclamped(@NotNull Vector3 a, @NotNull Vector3 b, double t) {
		return a.add(b.subtract(a).multiply(new Vector3(t, t, t)));
	}

	@Contract("_, _ -> new")
	public static @NotNull Vector3 max(@NotNull Vector3 a, @NotNull Vector3 b) {
		return new Vector3(
				Math.max(a.x, b.x),
				Math.max(a.y, b.y),
				Math.max(a.z, b.z)
		);
	}

	@Contract("_, _ -> new")
	public static @NotNull Vector3 min(@NotNull Vector3 a, @NotNull Vector3 b) {
		return new Vector3(
				Math.min(a.x, b.x),
				Math.min(a.y, b.y),
				Math.min(a.z, b.z)
		);
	}

	@Contract("_, _ -> new")
	public static @NotNull Vector3 scale(@NotNull Vector3 a, @NotNull Vector3 b) {
		return new Vector3(
				a.x * b.x,
				a.y * b.y,
				a.z * b.z
		);
	}
}