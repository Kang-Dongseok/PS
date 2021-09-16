package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 평범한 배낭
/*
 * 0/1 knapsack 문제이다.
 * 기본적으로 D[N+1][K+1] 사이즈의 2차원 배열을 사용하지만,
 * 수업시간대 배운대로 1차원 배열을 이용하여 해결해보았다.
 */
public class Baekjoon_12865 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" "); 
		int N = Integer.parseInt(str[0]);
		int K = Integer.parseInt(str[1]);
		int[] weights = new int[N+1];
		int[] values = new int[N+1];
		for(int i=1; i<=N; ++i) {
			str = br.readLine().split(" ");
			weights[i] = Integer.parseInt(str[0]);
			values[i] = Integer.parseInt(str[1]);
		}
		int[] D = new int[K+1]; 
		
		for(int i=1; i<=N; ++i) {
			for(int w=K; w>=weights[i]; --w) {
				D[w] = Math.max(D[w], D[w-weights[i]]+values[i]);
			}
		}
		System.out.println(D[K]);
	}
}