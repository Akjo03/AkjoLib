package io.github.akjo03.lib.math.vector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@AllArgsConstructor
@SuppressWarnings("unused")
public class Vector3 {
	private double x;
	private double y;
	private double z;

	public Vector3() {
		this(0, 0, 0);
	}

	public Vector3(@NotNull Vector3 other) {
		this(other.getX(), other.getY(), other.getZ());
	}

	public void add(@NotNull Vector3 other) {
		x += other.getX();
		y += other.getY();
		z += other.getZ();
	}

	public void subtract(@NotNull Vector3 other) {
		x -= other.getX();
		y -= other.getY();
		z -= other.getZ();
	}

	public void multiply(double scalar) {
		x *= scalar;
		y *= scalar;
		z *= scalar;
	}

	public void divide(double scalar) {
		if (scalar == 0) throw new IllegalArgumentException("Cannot divide by zero");
		x /= scalar;
		y /= scalar;
		z /= scalar;
	}

	public double magnitude() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	public void normalize() {
		double mag = magnitude();
		if (mag == 0) throw new IllegalStateException("Cannot normalize zero vector");
		x /= mag;
		y /= mag;
		z /= mag;
	}

	public double dot(@NotNull Vector3 other) {
		return x * other.getX() + y * other.getY() + z * other.getZ();
	}

	public @NotNull Vector3 cross(@NotNull Vector3 other) {
		double cx = y * other.getZ() - z * other.getY();
		double cy = z * other.getX() - x * other.getZ();
		double cz = x * other.getY() - y * other.getX();
		return new Vector3(cx, cy, cz);
	}

	public double angle(@NotNull Vector3 other) {
		double dot = dot(other);
		double mag = magnitude() * other.magnitude();
		if (mag == 0) throw new IllegalStateException("Cannot compute angle between zero vectors");
		return Math.acos(dot / mag);
	}

	public void set(@NotNull Vector3 other) {
		setX(other.getX());
		setY(other.getY());
		setZ(other.getZ());
	}

	public void set(double x, double y, double z) {
		setX(x);
		setY(y);
		setZ(z);
	}

	public boolean equals(Object other) {
		if (other == this) return true;
		if (!(other instanceof Vector3 v)) return false;
		return Double.compare(x, v.x) == 0 && Double.compare(y, v.y) == 0 && Double.compare(z, v.z) == 0;
	}

	public int hashCode() {
		int result = 17;
		result = 31 * result + Double.hashCode(x);
		result = 31 * result + Double.hashCode(y);
		result = 31 * result + Double.hashCode(z);
		return result;
	}

	public String toString() {
		return String.format("Vector3(%f, %f, %f)", x, y, z);
	}
}