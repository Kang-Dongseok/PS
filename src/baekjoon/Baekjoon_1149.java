package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// RGB거리
public class Baekjoon_1149 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] costs = new int[N+1][3]; //  1~N번집의 RGB 비용 저장
		
		String[] str;
		for(int i=1; i<=N; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<3; j++) {
				costs[i][j] = Integer.parseInt(str[j]);
			}
		}
		// N번쨰 집을 선택시 최적은 N-1번째 집에서 R,G,B를 각각 선택한 최적의 경우 3가지를 다 고려해야한다.
		int[][] D = new int[N+1][3]; // dp를 위한 배열 생성, 각각 N번째집에 R,G,B 색을 칠할경우의 최적의 값
		// 첫 번쨰 집만 초기화
		D[1][0] = costs[1][0];
		D[1][1] = costs[1][1];
		D[1][2] = costs[1][2];
		for(int i=2; i<=N; ++i) {
			D[i][0] = costs[i][0] + Math.min(D[i-1][1], D[i-1][2]); // i-1번째가 G,B 인 경우중 최소 + i번째 R 비용
			D[i][1] = costs[i][1] + Math.min(D[i-1][0], D[i-1][2]); // i-1번째가 R,B 인 경우중 최소 + i번째 G 비용
			D[i][2] = costs[i][2] + Math.min(D[i-1][0], D[i-1][1]); // i-1번째가 R,G 인 경우중 최소 + i번째 B 비용
		}
		System.out.println(Math.min(Math.min(D[N][0], D[N][1]), D[N][2]));
	}
}
