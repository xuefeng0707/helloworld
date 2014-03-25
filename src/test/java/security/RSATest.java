package security;

import junit.framework.TestCase;

/**
 * User: xuefeng
 * Date: 14-3-25
 * Time: 下午11:28
 */
public class RSATest extends TestCase {
	public void testEncrypt() throws Exception {
		String msg = "hello world!";
		RSA rsa = RSA.getInstance();
		byte[] input = msg.getBytes();

		// 公钥加密 -> 私钥解密
		byte[] encrypted = rsa.encryptByPublicKey(input);
		byte[] decrypted = rsa.decryptByPrivateKey(encrypted);
		assertEquals(msg, new String(decrypted));

		// 私钥加密 -> 公钥解密
		encrypted = rsa.encryptByPrivateKey(input);
		decrypted = rsa.decryptByPublicKey(encrypted);
		assertEquals(msg, new String(decrypted));
	}
}
