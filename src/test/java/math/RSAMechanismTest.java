package math;

import junit.framework.TestCase;

import java.math.BigInteger;

/**
 * User: xuefeng
 * Date: 14-3-24
 * Time: 下午11:42
 */
public class RSAMechanismTest extends TestCase {

	public void testRSA() {
		BigInteger p = BigInteger.valueOf(47);
		BigInteger q = BigInteger.valueOf(71);
		RSAMechanism rsa = RSAMechanism.getInstance(p, q);
		String input = "6882326879666683";
		String encrypted = rsa.encrypt(input);
		String decrypted = rsa.decrypt(encrypted);
		String expectedOutput = "1570275627142423158";

		//assertEquals(expectedOutput, encrypted);
		assertEquals(input, decrypted);
	}
}
