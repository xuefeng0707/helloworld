package security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA加密、解密实现
 * User: xuefeng
 * Date: 14-3-25
 * Time: 下午11:26
 */
public class RSA {

	private static final String ALGORITHM = "RSA";

	/** 公钥 */
	private RSAPublicKey publicKey;

	/** 私钥 */
	private RSAPrivateKey privateKey;

	private RSA() {

	}

	public static RSA getInstance() throws NoSuchAlgorithmException {
		RSA rsa = new RSA();
		rsa.initKey();

		return rsa;
	}

	private void initKey() throws NoSuchAlgorithmException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
		generator.initialize(1024);
		KeyPair keypair = generator.generateKeyPair();

		publicKey = (RSAPublicKey) keypair.getPublic();
		privateKey = (RSAPrivateKey) keypair.getPrivate();
	}

	/**
	 * 使用公钥加密
	 * @param input 需要使用公钥加密的信息
	 * @return 公钥加密的结果
	 */
	public byte[] encryptByPublicKey(byte[] input)
		throws NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		return byPublicKey(input, true);
	}

	/**
	 * 使用公钥解密
	 * @param encrypted 经过私钥加密过的数据
	 * @return 私钥加密前的数据
	 */
	public byte[] decryptByPublicKey(byte[] encrypted)
		throws NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException{
		return byPublicKey(encrypted, false);
	}

	// 公钥加密、解密
	private byte[] byPublicKey(byte[] data, boolean encrypt)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey.getEncoded());
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		Key realPublicKey = keyFactory.generatePublic(keySpec);

		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, realPublicKey);
		return cipher.doFinal(data);
	}

	/**
	 * 使用私钥解密，常用于签名
	 * @param input 需要使用私钥加密的数据
	 * @return 私钥加密的结果
	 */
	public byte[] encryptByPrivateKey(byte[] input)
		throws NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException{
		return byPrivateKey(input, true);
	}

	/**
	 * 使用私钥解密
	 * @param encrypted 经过私钥加密过的数据
	 * @return 私钥加密前的数据
	 */
	public byte[] decryptByPrivateKey(byte[] encrypted)
		throws NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		return byPrivateKey(encrypted, false);
	}

	// 私钥加密、解密
	private byte[] byPrivateKey(byte[] data, boolean encrypt)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		Key realPrivateKey = keyFactory.generatePrivate(keySpec);

		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, realPrivateKey);
		return cipher.doFinal(data);
	}
}
