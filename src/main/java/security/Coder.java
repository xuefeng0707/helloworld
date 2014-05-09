package security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 编码基础类。
 * 提供BASE64算法的编码、解码实现，MD5、SHA算法的编码实现。
 * User: xuefeng
 * Date: 14-3-25
 * Time: 下午11:31
 */
public class Coder {
	public static String encodeBASE64(byte[] input) {
		return new BASE64Encoder().encode(input);
	}

	public static byte[] decodeBASE64(String base64) throws IOException {
		return new BASE64Decoder().decodeBuffer(base64);
	}

	public static byte[] encodeMD5(byte[] input) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(input);
		return digest.digest();
	}

	public static byte[] encodeSHA(byte[] input) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA");
		digest.update(input);
		return digest.digest();
	}
}
