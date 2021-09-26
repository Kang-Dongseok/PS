package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 정수 삼각형
public class Baekjoon_1932 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] triangle = new int[N][N];
		int[][] D = new int[N][N]; // 위에서부터 내려오면서 합의 최댓값을 구하는 dp배열
		String[] str;
		for(int i=0; i<N; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<=i; ++j) {
				triangle[i][j] = Integer.parseInt(str[j]);
			}
		}
		if(N==1) {
			System.out.println(triangle[0][0]);
			return;
		}
		D[0][0] = triangle[0][0];
		D[1][0] = triangle[0][0]+triangle[1][0];
		D[1][1] = triangle[0][0]+triangle[1][1];
		for(int i=2; i<N; ++i) {
			D[i][0] = D[i-1][0]+triangle[i][0]; // 0열은 위에서 그대로 쭉 더해져서 내려온다.
			for(int j=1; j<=i; ++j) {
				D[i][j] = Math.max(D[i-1][j-1], D[i-1][j])+triangle[i][j]; // 양쪽에서 내려온 것 중 큰 값과 현재 값을 더한다.
			}
		}
		int max = 0;
		for(int i=0; i<N; ++i) {
			if(max<D[N-1][i]) max = D[N-1][i];
		}
		System.out.println(max);
	}
}