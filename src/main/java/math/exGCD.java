package math;

import java.math.BigInteger;

/**
 * 扩展欧几里德算法
 * a*x+b*y=gcd(a,b)
 * User: xuefeng
 * Date: 14-3-23
 * Time: 下午6:42
 */
public class exGCD {
	public static int exgcd(int a, int b) {
		XY xy = new XY();

		int gcd = exgcd(a, b, xy);

		// System.out.println(xy.x + ", " + xy.y);

		return gcd;
	}

	public static int exgcd(int a, int b, XY xy) {
		if(b == 0) {
			//此时gcd(a,b)=a，a*1+b*0=a
			xy.x = 1;
			xy.y = 0;
			return a;
		}
		int g = exgcd(b, a%b, xy);

		int temp = xy.x;
		xy.x = xy.y;
		xy.y = temp - (a/b)*xy.y;

		return g > 0 ? g : -g;
	}

	public static BigInteger exgcd(BigInteger a, BigInteger b) {
		BigXY xy = new BigXY();

		BigInteger gcd = exgcd(a, b, xy);

		// System.out.println(xy.x + ", " + xy.y);

		return gcd;
	}

	public static BigInteger exgcd(BigInteger a, BigInteger b, BigXY xy) {
		if(b.equals(BigInteger.ZERO)) {
			//此时gcd(a,b)=a，a*1+b*0=a
			xy.x = BigInteger.ONE;
			xy.y = BigInteger.ZERO;
			return a;
		}
		BigInteger g = exgcd(b, a.mod(b), xy);

		BigInteger temp = xy.x;
		xy.x = xy.y;
		xy.y = temp.subtract(a.divide(b).multiply(xy.y));

		return g.abs();
	}

	// 记录计算过程中x、y的变化
	public static class XY {
		public int x, y;
	}

	public static class BigXY {
		public BigInteger x, y;
	}
}
