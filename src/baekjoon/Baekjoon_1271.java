package baekjoon;

import java.math.BigInteger;
import java.util.Scanner;

// 엄청난 부자2
/*
 * 범위가 	0~10^1000 이므로 long으로도 커버가 불가능하다.
 * 따라서 BigInteger타입으로 선언하여 Scanner에서 nextBigInteger 메서드로 입력을 받은 후
 * 내장함수인 divide와 remainder로 계산하다.
 */
public class Baekjoon_1271 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BigInteger n = sc.nextBigInteger();
		BigInteger m = sc.nextBigInteger();
		System.out.println(n.divide(m));
		System.out.println(n.remainder(m));
	}
	
}
