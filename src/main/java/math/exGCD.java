package math;

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

		System.out.println(xy.x + ", " + xy.y);

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

	// 记录计算过程中x、y的变化
	public static class XY {
		public int x, y;
	}
}
