package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 스타트와 링크
/*
 * 1번 선수는 팀 멤버로 true로 고정, 나머지 애들중에 1번과 같은 팀원을 순열로 뽑아서 팀 기량차이 계산
 */

public class Baekjoon_14889 {

	static int N,half,answer;
	static boolean[] isTeam1;
	static int[][] point;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		half = N/2; // 전체 인원의 절반
		isTeam1 = new boolean[N];
		isTeam1[0] = true; // 0번은 고정 (0 ~ N-1번)
		point = new int[N][N];
		for(int i=0; i<N; ++i) {
			String[] str = br.readLine().split(" ");
			for(int j=0; j<N; ++j) {
				int p = Integer.parseInt(str[j]);
				point[i][j] = p;
			}
		}
		answer = Integer.MAX_VALUE;
		combination(1,1);
		System.out.println(answer);
		
	}
	
	public static void combination(int count, int num) {
		if(count==half) { // 다 뽑았으면
			calcGap();
			return;
		}
		if(half-count>N-num) { // 뽑아야 하는 수 > 남은 선수 수
			return;
		}
		
		for(int i=num; i<N; ++i) {
			isTeam1[i] = true; // 체크
			combination(count+1,i+1);
			isTeam1[i] = false; // 체크 취소
		}
	}
	
	public static void calcGap() {
		int sumA = getSum(true);
		int sumB = getSum(false);
		answer = Math.min(answer, Math.abs(sumA-sumB));
	}
	
	public static int getSum(boolean flag) {
		int sum = 0;
		for(int i=0; i<N-1; ++i) {
			for(int j=i+1; j<N; ++j) {
				if(isTeam1[i]==flag && isTeam1[j]==flag) {
					sum += point[i][j]+point[j][i];
				}
			}
		}
		return sum;
	}
}