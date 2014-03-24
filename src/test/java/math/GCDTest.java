package math;

import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigInteger;

/**
 * 测试最大公约数
 * User: xuefeng
 * Date: 14-3-23
 * Time: 下午6:18
 */
public class GCDTest extends TestCase {
	@Test
	public void testGcd() {
		int a = 20;
		int b = 15;
		assertEquals(5, GCD.gcd(a, b));
		assertEquals(5, GCD.gcd(b, a));
		assertEquals(5, GCD.gcd(-a, b));
		assertEquals(5, GCD.gcd(a, -b));
		assertEquals(5, GCD.gcd(-a, -b));

		assertEquals(5, GCD.gcd(5, 0));
		assertEquals(5, GCD.gcd(0, 5));
		assertEquals(1, GCD.gcd(1, 1));
		assertEquals(0, GCD.gcd(0, 0)); //??

		int max = Integer.MAX_VALUE;
		int min = Integer.MIN_VALUE;
		assertEquals(max, GCD.gcd(max, max));
		assertEquals(-min, GCD.gcd(min, min));
	}

	@Test
	public void testGcd4BigInteger() {
		int a = 20;
		int b = 15;
		assertEquals(5, gcd4BigInteger(a, b));
		assertEquals(5, gcd4BigInteger(b, a));
		assertEquals(5, gcd4BigInteger(-a, b));
		assertEquals(5, gcd4BigInteger(a, -b));
		assertEquals(5, gcd4BigInteger(-a, -b));

		assertEquals(5, gcd4BigInteger(5, 0));
		assertEquals(5, gcd4BigInteger(0, 5));
		assertEquals(1, gcd4BigInteger(1, 1));
		assertEquals(0, gcd4BigInteger(0, 0)); //??

		int max = Integer.MAX_VALUE;
		int min = Integer.MIN_VALUE;
		assertEquals(max, gcd4BigInteger(max, max));
		assertEquals(-min, gcd4BigInteger(min, min));
	}

	public static int gcd4BigInteger(int a, int b) {
		return GCD.gcd(BigInteger.valueOf(a), BigInteger.valueOf(b)).intValue();
	}
}
