package math;

import junit.framework.TestCase;

/**
 * User: xuefeng
 * Date: 14-3-23
 * Time: 下午6:52
 */
public class exGCDTest extends TestCase {
	public void testExGCD() {
		int a = 20;
		int b = 15;
		assertEquals(5, exGCD.exgcd(a, b));
		assertEquals(5, exGCD.exgcd(b, a));
		assertEquals(5, exGCD.exgcd(-a, b));
		assertEquals(5, exGCD.exgcd(a, -b));
		assertEquals(5, exGCD.exgcd(-a, -b));

		assertEquals(5, exGCD.exgcd(5, 0));
		assertEquals(5, exGCD.exgcd(0, 5));
		assertEquals(1, exGCD.exgcd(1, 1));
		assertEquals(0, exGCD.exgcd(0, 0)); //??

		int max = Integer.MAX_VALUE;
		int min = Integer.MIN_VALUE;
		assertEquals(max, exGCD.exgcd(max, max));
		assertEquals(-min, exGCD.exgcd(min, min));
	}

}
