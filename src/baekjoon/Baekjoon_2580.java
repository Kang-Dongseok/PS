package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 스도쿠
/*
 * dfs로 순서대로 가능한 숫자 체크하면서
 * 숫자 다 채우면 dfs함수를 true로 리턴하며 더 이상 진행시키지 않고 종료한다.
 */
public class Baekjoon_2580 {

	static int N;
	static int[][] map = new int[9][9];
	static ArrayList<int[]> list = new ArrayList<int[]>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<9; ++i) {
			String[] line = br.readLine().split(" ");
			for(int j=0; j<9; ++j) {
				int n = Integer.parseInt(line[j]);
				map[i][j] = n;
				if(n==0) {
					list.add(new int[] {i,j});
					N++; // 0의 갯수 증가
				}
			}
		}
		dfs(0);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<9; ++i) {
			for(int j=0; j<9; ++j) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static boolean dfs(int n) {
		if(n==N) { // 정답 찾으면 true 리턴
			return true;
		}
		
		int[] cur = list.get(n);
		int r = cur[0];
		int c = cur[1];
		for(int i=1; i<=9; ++i) {
			if(check(r,c,i)) { // r,c에 숫자i가 가능하면
				map[r][c]=i; // i 대입
				if(dfs(n+1)) return true; // 정답 찾으면 true리턴
				map[r][c]=0; // i 취소
			}
		}
		
		return false;
	}
	
	public static boolean check(int r, int c, int num) {
		for(int i=0; i<9; ++i) {
			if(map[r][i]==num) return false;
		}
		for(int i=0; i<9; ++i) {
			if(map[i][c]==num) return false;
		}
		int R = r/3;
		int C = c/3;
		for(int i=R*3; i<R*3+3; ++i) {
			for(int j=C*3; j<C*3+3; ++j) {
				if(map[i][j]==num) return false;
			}
		}
		return true;
	}
}