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


		p = new BigInteger("10992769329753832550147108143682375077554847211725856998938419048111674080611893847566425832326894384287618620463612064896718049680103438110410730940808189");
		q = new BigInteger("9852969747387399308143348029768221675115620303568234650619078019736040400280194991382658180907061041084614695924441634065856665568082931046274664576187979");
		rsa = RSAMechanism.getInstance(p, q);
		input = "1234567890";
		encrypted = rsa.encrypt(input);
		decrypted = rsa.decrypt(encrypted);
		assertEquals(input, decrypted);
	}
}
