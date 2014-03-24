package math;

import math.exGCD.XY;
import math.exGCD.BigXY;

import java.math.BigInteger;

/**
 * 模逆元素
 * 整数a对于模b的模反元素为满足a*x+b*y=1的x
 * a*m%b=1中的m
 * User: xuefeng
 * Date: 14-3-23
 * Time: 下午10:41
 */
public class ModularInverse {
	public static int getInverse(int a, int b) {
		XY xy = new XY();
		int gcd = exGCD.exgcd(a,b,xy);

		int inverse;
		// 如果a和b的最大公约数不是1，即a和b不互质，则模反元素不存在
		if(gcd != 1) {
			inverse = -1;
		} else {
			inverse = xy.x > 0 ? xy.x :  b+xy.x; // 因为x可能为负
		}

		return inverse;
	}

	public static BigInteger getInverse(BigInteger a, BigInteger b) {
		BigXY xy = new BigXY();
		BigInteger gcd = exGCD.exgcd(a,b,xy);

		BigInteger inverse;
		// 如果a和b的最大公约数不是1，即a和b不互质，则模反元素不存在
		if(!gcd.equals(BigInteger.ONE)) {
			inverse = BigInteger.valueOf(-1);
		} else {
			inverse = xy.x.signum() == 1 ? xy.x :  xy.x.add(b); // 因为x可能为负
		}

		return inverse;
	}
}
