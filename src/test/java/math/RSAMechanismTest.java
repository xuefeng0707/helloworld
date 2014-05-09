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


		p = new BigInteger("11641043165523098214624136354728164704988164479837375664840511837917304154009391691297836366925221711891198191856542516232070913747687502322916241010576459");
		q = new BigInteger("11205168226436227709125279938734493592181813758812561505922048032607897022241567616298898109539729940426474001351661125506005232762862399150289150168344863");
		rsa = RSAMechanism.getInstance(p, q);
		input = "hello world!";
		encrypted = rsa.encrypt(input);
		decrypted = rsa.decrypt(encrypted);
		assertEquals(input, decrypted);
	}
}
