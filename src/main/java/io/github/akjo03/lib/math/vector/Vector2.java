package io.github.akjo03.lib.math.vector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@AllArgsConstructor
@SuppressWarnings("unused")
public class Vector2 {
	private double x;
	private double y;

	public Vector2() {
		this(0, 0);
	}

	public Vector2(@NotNull Vector2 other) {
		this(other.getX(), other.getY());
	}

	public void add(@NotNull Vector2 other) {
		x += other.getX();
		y += other.getY();
	}

	public void subtract(@NotNull Vector2 other) {
		x -= other.getX();
		y -= other.getY();
	}

	public void multiply(double scalar) {
		x *= scalar;
		y *= scalar;
	}

	public void divide(double scalar) {
		if (scalar == 0) throw new IllegalArgumentException("Cannot divide by zero");
		x /= scalar;
		y /= scalar;
	}

	public double magnitude() {
		return Math.sqrt(x * x + y * y);
	}

	public void normalize() {
		double mag = magnitude();
		if (mag == 0) throw new IllegalStateException("Cannot normalize zero vector");
		x /= mag;
		y /= mag;
	}

	public double dot(@NotNull Vector2 other) {
		return x * other.getX() + y * other.getY();
	}

	public double angle(@NotNull Vector2 other) {
		double dot = dot(other);
		double mag = magnitude() * other.magnitude();
		if (mag == 0) throw new IllegalStateException("Cannot compute angle between zero vectors");
		return Math.acos(dot / mag);
	}

	public void set(@NotNull Vector2 other) {
		setX(other.getX());
		setY(other.getY());
	}

	public void set(double x, double y) {
		setX(x);
		setY(y);
	}

	public boolean equals(Object other) {
		if (other == this) return true;
		if (!(other instanceof Vector2 v)) return false;
		return Double.compare(x, v.x) == 0 && Double.compare(y, v.y) == 0;
	}

	public int hashCode() {
		int result = 17;
		result = 31 * result + Double.hashCode(x);
		result = 31 * result + Double.hashCode(y);
		return result;
	}

	public String toString() {
		return String.format("Vector2(%f, %f)", x, y);
	}
}