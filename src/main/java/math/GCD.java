package math;

import java.math.BigInteger;

/**
 * 求两个整数的最大公约数
 * 算法：辗转相除法，欧几里德算法
 * User: xuefeng
 * Date: 14-3-23
 * Time: 下午6:09
 */
public class GCD {
	/**
	 * 不妨设a>b
	 * 设G=gcd(a,b)，则a=m*G，b=n*G，m>n，则a-b=(m-n)*G，则gcd(b,a-b)=G，同理gcd(b,a-2b)=G,
	 * ...以至gcd(b,a-qb)=G，显然a-qb=a%b。由此得出：gcd(a,b)=gcd(b,a%b)。如此，不停地交换a%b和b的位置，
	 * 是的两个数逐渐减小，直到a%b == 0。
	 * @param a 整数
	 * @param b 整数
	 * @return a和b的最大公约数
	 */
	public static int gcd(int a, int b) {
		int temp;
		while(b != 0) {
			temp = a;
			a = b;
			b = temp % b;
		}

		// 总是返回正整数或0
		return a > 0 ? a : -a;
	}

	public static BigInteger gcd(BigInteger a, BigInteger b) {
		// 总是返回正整数或0
		a = a.abs();
		// BigInteger作mod运算时，除数不能为负
		b = b.abs();
		BigInteger temp;
		while(!b.equals(BigInteger.ZERO)) {
			temp = a;
			a = b;
			b = temp.mod(b);
		}

		return a;
	}
}
