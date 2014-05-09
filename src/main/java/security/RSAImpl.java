package security;

import sun.security.jca.JCAUtil;
import sun.security.rsa.RSACore;
import sun.security.rsa.RSAPadding;

import javax.crypto.BadPaddingException;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * RSA实现，非JDK默认实现。
 * User: xuefeng
 * Date: 14-3-27
 * Time: 下午8:38
 */
public class RSAImpl {

	public static final BigInteger DEFAULT_E = BigInteger.valueOf(65537);

	/**
	 * 公钥
	 */
	private BigInteger n, e;

	/**
	 * 私钥
	 */
	private BigInteger d;

	private RSAImpl(){}

	public static RSAImpl getInstance(BigInteger p, BigInteger q) {
		RSAImpl impl = new RSAImpl();
		BigInteger n = p.multiply(q);
		BigInteger euler = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		BigInteger e = DEFAULT_E;
		BigInteger d = e.modInverse(euler);

		impl.n = n;
		impl.e = e;
		impl.d = d;

		impl.paddedSize = RSACore.getByteLength(n);
		impl.maxDataSize = impl.paddedSize - 11;
		impl.type = RSAPadding.PAD_BLOCKTYPE_2;

		return impl;
	}

	public byte[] encrypt(byte[] data) throws BadPaddingException {
		data = padV15(data);
		BigInteger num = new BigInteger(1, data);
		BigInteger encryptedNum = num.modPow(e, n);

		return encryptedNum.toByteArray();
	}

	public byte[] decrypt(byte[] encrypted) {
		BigInteger num = new BigInteger(1, encrypted);
		BigInteger decryptedNum = num.modPow(d, n);

		return decryptedNum.toByteArray();
	}

	// PRNG used to generate padding bytes (PAD_BLOCKTYPE_2, PAD_OAEP_MGF1)
	private SecureRandom random;

	private int paddedSize;

	private int maxDataSize;

	private int type;
	/**
	 * PKCS#1 v1.5 padding (blocktype 1 and 2).
	 */
	private byte[] padV15(byte[] data) throws BadPaddingException {
		byte[] padded = new byte[paddedSize];
		System.arraycopy(data, 0, padded, paddedSize - data.length, data.length);
		int psSize = paddedSize - 3 - data.length;
		int k = 0;
		padded[k++] = 0;
		padded[k++] = (byte)type;
		if (type == RSAPadding.PAD_BLOCKTYPE_1) {
			// blocktype 1: all padding bytes are 0xff
			while (psSize-- > 0) {
				padded[k++] = (byte)0xff;
			}
		} else {
			// blocktype 2: padding bytes are random non-zero bytes
			if (random == null) {
				random = JCAUtil.getSecureRandom();
			}
			// generate non-zero padding bytes
			// use a buffer to reduce calls to SecureRandom
			byte[] r = new byte[64];
			int i = -1;
			while (psSize-- > 0) {
				int b;
				do {
					if (i < 0) {
						random.nextBytes(r);
						i = r.length - 1;
					}
					b = r[i--] & 0xff;
				} while (b == 0);
				padded[k++] = (byte)b;
			}
		}
		return padded;
	}

	/**
	 * PKCS#1 v1.5 unpadding (blocktype 1 and 2).
	 */
	private byte[] unpadV15(byte[] padded) throws BadPaddingException {

		int k = 0;
		if (padded[k++] != 0) {
			throw new BadPaddingException("Data must start with zero");
		}
		if (padded[k++] != type) {
			throw new BadPaddingException("Blocktype mismatch: " + padded[1]);
		}
		while (true) {
			int b = padded[k++] & 0xff;
			if (b == 0) {
				break;
			}
			if (k == padded.length) {
				throw new BadPaddingException("Padding string not terminated");
			}
			if ((type == RSAPadding.PAD_BLOCKTYPE_1) && (b != 0xff)) {
				throw new BadPaddingException("Padding byte not 0xff: " + b);
			}
		}
		int n = padded.length - k;
		if (n > maxDataSize) {
			throw new BadPaddingException("Padding string too short");
		}
		byte[] data = new byte[n];
		System.arraycopy(padded, padded.length - n, data, 0, n);
		return data;
	}
}
