package string;

/**
 * KMP字符串匹配算法
 * @author xuefeng
 *
 */
public class KmpTest {

	/**
	 * 在字符串中寻找第一个子串匹配
	 * @param target 在此字符串中查找
	 * @param pattern 查找的子串
	 * @return 第一个匹配的index
	 */
	public static int kmp(String target, String pattern) {
		int tLen = target.length();
		int pLen = pattern.length();
		
		// 求overlay：pattern中每个位置前缀和后缀相同的最大前缀P[0~O[i]]
		int[] overlay = new int[pLen];
		overlay[0] = -1;
		int index = 0;
		for (int i = 1; i < pLen; i++) {
			index = overlay[i - 1];
			while (index >= 0 && pattern.charAt(index + 1) != pattern.charAt(i)) {
				index = overlay[index];
			}
			if (pattern.charAt(index + 1) == pattern.charAt(i)) {
				overlay[i] = index + 1;
			} else {
				overlay[i] = -1;
			}
		}

		int pIndex = 0;
		int tIndex = 0;
		while (pIndex < pLen && tIndex < tLen) {
			if (target.charAt(tIndex) == pattern.charAt(pIndex)) {
				tIndex++;
				pIndex++;
			} else if (pIndex == 0) {
				tIndex++;
			} else {
				pIndex = overlay[pIndex - 1] + 1;
			}
		}
		if (pIndex == pLen) {
			return tIndex - pIndex;
		} else {
			return -1;
		}
	}

	public static void main(String[] args) {
		String target = " annbcabcdabddanacadsannannabnna_abcdabd";
		String pattern = "abcdabd";
		System.out.println(kmp(target, pattern));
	}

}
