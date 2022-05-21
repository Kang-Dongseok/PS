package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 사탕 게임
public class Baekjoon_3085 {

	static int N,ans;
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for(int i=0; i<N; ++i) {
			map[i] = br.readLine().toCharArray();
		}
		// 좌우 변경
		for(int r=0; r<N; ++r) {
			for(int c=0; c<N-1; ++c) {
				swap(r, c, r, c+1); // 바꾸기
				check(r,c);
				check(r,c+1);
				swap(r, c, r, c+1); // 돌려놓기
			}
		}
		// 상하 변경
		for(int r=0; r<N-1; ++r) {
			for(int c=0; c<N; ++c) {
				swap(r, c, r+1, c); // 바꾸기
				check(r,c);
				check(r+1,c);
				swap(r, c, r+1, c); // 돌려놓기
			}
		}
		System.out.println(ans);
	}
	
	public static void check(int r, int c) {
		char color = map[r][c];
		int row = 1; // 가로 길이
		for(int i=c-1; i>=0; i--) { // 왼쪽
			if(map[r][i]==color) row++;
			else break;
		}
		for(int i=c+1; i<N; i++) { // 오른쪽
			if(map[r][i]==color) row++;
			else break;
		}
		int col = 1; // 세로 길이
		for(int i=r-1; i>=0; i--) { // 위쪽
			if(map[i][c]==color) col++;
			else break;
		}
		for(int i=r+1; i<N; i++) { // 아래쪽
			if(map[i][c]==color) col++;
			else break;
		}
		if(ans<row) ans=row;
		if(ans<col) ans=col;
	}
	
	public static void swap(int r1, int c1, int r2, int c2) {
		char tmp = map[r1][c1];
		map[r1][c1] = map[r2][c2];
		map[r2][c2] = tmp;
	}
}