package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 계단 오르기
/*
 * 규칙
 * 1. 한번에 1or2개만 올라감
 * 2. 연속 3개 계단 불가
 * 3. 마지막 계단 무조건 밟기
 */
public class Baekjoon_2579 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 색종이 수
		int[] stairs = new int[N];
		for(int i=0; i<N; ++i) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		if(N==1) { // 계단이 1개이면
			System.out.println(stairs[0]);
			return;
		}
		int[][] D = new int[N][2]; // D[i][0]: 이전칸을 밟고 i번째 밟음, D[i][1]: 이전칸을 밟지 않고 i번째 밟음
		D[0][0] = D[0][1] = stairs[0]; // 첫 계단 밟은 점수는 초기화
		D[1][0] = stairs[0]+stairs[1];
		D[1][1] = stairs[1];
		for(int i=2; i<N; ++i) {
			D[i][0] = D[i-1][1]+stairs[i]; // 이전칸을 밟고 현재칸을 밟으려면 무조건 이전칸은 한 칸 더 이전칸을 안 밟은 상태여야 한다.
			D[i][1] = Math.max(D[i-2][0], D[i-2][1])+stairs[i]; // 이전칸을 안밟고 현재칸을 밟으려면 연속3계단 걱정은 안해도 되니, 2칸전에 밟은 계단 중 많은 점수를 택하면 된다.
		}
//		for(int i=0; i<N; i++) {
//			System.out.println(D[i][0]+" "+D[i][1]);
//		}
		System.out.println(Math.max(D[N-1][0], D[N-1][1]));
	}
}