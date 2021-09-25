package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 01타일
/*
 * 규칙을 잘 찾다보면 수열이 보인다.
 */
public class Baekjoon_1904 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] D = new int[N];
		if(N<=2) {
			System.out.println(N);
			return;
		}
		D[0] = 1;
		D[1] = 2;
		for(int i=2; i<N; ++i) { // 피보나치 수열이다.
			// 나머지연산의 성질에 의해 더해서 나누나, 나눠서 나머지를 각각 더하나 똑같으므로
			// int 범위를 overflow가 발생하지 않게 먼저 나눈다.
			D[i] = D[i-1]%15746 + D[i-2]%15746;
		}
		System.out.println(D[N-1]%15746);
	}
}