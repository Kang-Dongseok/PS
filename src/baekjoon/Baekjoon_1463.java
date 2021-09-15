package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1로 만들기
public class Baekjoon_1463 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N==1) {
			System.out.println(0);
			return;
		}
		if(N==2) {
			System.out.println(1);
			return;
		}
		int[] D = new int[N+1];
		// 기본 조건만 초기화
		D[2] = 1;
		D[3] = 1;
		for(int i=4; i<=N; ++i) {
			int min = D[i-1]+1; // 일단 1뺀것의 최소비용 + 1
			if(i%2==0) {
				min = Math.min(D[i/2]+1, min); // 2로 나눈것과 최소비용 비교 + 1
			}
			if(i%3==0) {
				min = Math.min(D[i/3]+1, min); // 3으로 나눈것과 최소비용 비교 + 1
			}
			D[i] = min;
		}
		System.out.println(D[N]);
	}
}