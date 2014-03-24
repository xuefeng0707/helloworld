package math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

/**
 * 只是RSA算法机制，并不是RSA算法实现
 * User: xuefeng
 * Date: 14-3-24
 * Time: 下午9:00
 */
public class RSAMechanism {

	public static final String PLACEHOLDER = " ";
	/**
	 * 公钥
 	 */
	private BigInteger n, e;

	/**
	 * 私钥
	 */
	private BigInteger d;

	private RSAMechanism() {

	}

	/**
	 * 选定两个大素数，生成RSA算法的公钥和私钥。
	 * @param p
	 * @param q
	 * @return
	 */
	public static RSAMechanism getInstance(BigInteger p, BigInteger q) {
		RSAMechanism rsa = new RSAMechanism();
		BigInteger n = p.multiply(q);
		BigInteger euler = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
//		BigInteger e = new BigDecimal(euler).multiply(BigDecimal.valueOf(new Random().nextDouble())).toBigInteger();
//		BigInteger e = BigInteger.valueOf(79);
		BigInteger e = getRandomCoprime(euler, euler);
		BigInteger d = ModularInverse.getInverse(e, euler);

		rsa.n = n;
		rsa.e = e;
		rsa.d = d;

		return rsa;
	}

	public String encrypt(String msg) {

		// 暂时以数字形式表示
		String number = msg;

		int len = number.length(),
			pieceCount = len / 3,
			extra = len % 3,
			begin;
		BigInteger piece;
		StringBuffer result = new StringBuffer();
		for(int i=0;i<pieceCount;i++) {
			begin = i * 3;
			piece = BigInteger.valueOf(Long.parseLong(number.substring(begin, begin + 3)));
			result.append("" + piece.pow(e.intValue()).mod(n)); // ?? invValue()
			result.append(PLACEHOLDER);
		}
		if(extra > 0) {
			piece = BigInteger.valueOf(Long.parseLong(number.substring(len - extra)));
			result.append("" + piece.pow(e.intValue()).mod(n)); // ?? invValue()
		} else {
			result.deleteCharAt(result.length());
		}

		return result.toString();
	}

	public String decrypt(String msg) {
		String[] pieces = msg.split(PLACEHOLDER);
		StringBuffer result = new StringBuffer();
		BigInteger p;
		for(String piece : pieces) {
			p = BigInteger.valueOf(Long.parseLong(piece));
			result.append("" + p.pow(d.intValue()).mod(n)); // ?? invValue()
		}

		return result.toString();
	}

	// 返回[0,max)内的随机整数，且与coprime互素
	private static BigInteger getRandomCoprime(BigInteger max, BigInteger coprime) {
		BigInteger random;
		while(true) {
			random = new BigDecimal(max).multiply(BigDecimal.valueOf(new Random().nextDouble())).toBigInteger();
			if(GCD.gcd(random, coprime).equals(BigInteger.ONE)) {
				return random;
			}
		}
//		return BigInteger.ONE.negate();
	}
}
