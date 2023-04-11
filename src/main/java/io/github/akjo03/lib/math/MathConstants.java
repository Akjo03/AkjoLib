package io.github.akjo03.lib.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@SuppressWarnings("unused")
public final class MathConstants {
	private MathConstants() {}

	public static final MathContext DEFAULT_MATH_CONTEXT = new MathContext(34, RoundingMode.HALF_UP);

	public static final BigDecimal ZERO = BigDecimal.ZERO;
	public static final BigDecimal ONE = BigDecimal.ONE;
	public static final BigDecimal TWO = BigDecimal.valueOf(2);
	public static final BigDecimal NEGATIVE_ONE = BigDecimal.valueOf(-1);

	public static final BigDecimal PI = new BigDecimal("3.14159265358979323846264338327950", DEFAULT_MATH_CONTEXT);
	public static final BigDecimal E = new BigDecimal("2.71828182845904523536028747135266", DEFAULT_MATH_CONTEXT);

	public static final BigDecimal SQRT_TWO = new BigDecimal("1.41421356237309504880168872420970", DEFAULT_MATH_CONTEXT);
	public static final BigDecimal SQRT_THREE = new BigDecimal("1.73205080756887729352744634150587", DEFAULT_MATH_CONTEXT);
	public static final BigDecimal SQRT_FIVE = new BigDecimal("2.23606797749978969640917366873128", DEFAULT_MATH_CONTEXT);

	public static final BigDecimal TAU = PI.multiply(TWO);
	public static final BigDecimal PHI = ONE.add(SQRT_FIVE, DEFAULT_MATH_CONTEXT).divide(TWO, DEFAULT_MATH_CONTEXT);
}