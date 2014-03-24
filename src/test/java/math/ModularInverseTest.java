package math;

import junit.framework.TestCase;

import java.math.BigInteger;

/**
 * User: xuefeng
 * Date: 14-3-23
 * Time: 下午11:20
 */
public class ModularInverseTest extends TestCase {
	public void testGetInverse() {
		int a = 540;
		int b = 1769;

		assertEquals(1674, ModularInverse.getInverse(a, b));
		assertEquals(1019, ModularInverse.getInverse(79, 3220));
	}

	public void testGetInverse4BigInteger() {
		BigInteger a = BigInteger.valueOf(540);
		BigInteger b = BigInteger.valueOf(1769);

		assertEquals(BigInteger.valueOf(1674), ModularInverse.getInverse(a, b));
		assertEquals(BigInteger.valueOf(1019), ModularInverse.getInverse(BigInteger.valueOf(79), BigInteger.valueOf(3220)));
	}
}
