package math;

import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: xuefeng
 * Date: 14-3-23
 * Time: 下午11:20
 * To change this template use File | Settings | File Templates.
 */
public class ModularInverseTest extends TestCase {
	public void testGetInverse() {
		int a = 540;
		int b = 1769;

		assertEquals(1674, ModularInverse.getInverse(a, b));
		assertEquals(1019, ModularInverse.getInverse(79, 3220));
	}
}
