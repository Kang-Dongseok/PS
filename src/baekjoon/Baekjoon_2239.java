package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 스도쿠
public class Baekjoon_2239 {

	static boolean finished;
	static int[][] map;
	static List<int[]> pos; // 0인 좌표들을 저장
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		map = new int[9][9];
		pos = new ArrayList<int[]>();
		for(int i=0; i<9; ++i) {
			str = br.readLine().split("");
			for(int j=0; j<9; ++j) {
				int num = Integer.parseInt(str[j]);
				map[i][j] = num;
				if(num == 0) pos.add(new int[] {i,j}); // 0인 좌표 저장
			}
		}
		dfs(0);
		
	}
	public static void dfs(int n) { // n번째 인덱스의 좌표를 꺼내서 확인
		if(finished) return; // 이미 완성되었으면 더 확인할 필요 없이 종료
		if(n==pos.size()) { // 마지막 0까지 다 숫자를 채웠으면
			finished=true; // 완성
			for(int i=0; i<9; ++i) {
				for(int j=0; j<9; ++j) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			return;
		}
		int r=pos.get(n)[0];
		int c=pos.get(n)[1];
		for(int i=1; i<=9; ++i) {
			if(check(r,c,i)) { // (r,c)에 숫자 i를 적을 수 있으면
				map[r][c] = i; // i 적고
				dfs(n+1); // 다음 빈칸으로 넘어가기
			}
		}
		map[r][c]=0;
	}
	public static boolean check(int r, int c, int num) { // 행,열,사각형 모두 체크
		for(int i=0; i<9; ++i) { // 행 체크
			if(map[i][c]==num) return false;
		}
		for(int i=0; i<9; ++i) { // 열 체크
			if(map[r][i]==num) return false;
		}
		int startR = (r/3)*3; // 사각형의 시작 행
		int startC = (c/3)*3; // 사각형의 시작 열
		for(int i=startR; i<startR+3; ++i) {
			for(int j=startC; j<startC+3; ++j) {
				if(map[i][j]==num) return false;
			}
		}
		return true;
	}
}