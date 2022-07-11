package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 수영장 만들기
public class Baekjoon_1113 {
	
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		map = new int[N][M];
		int top = 1;
		for(int i=0; i<N; ++i) {
			str = br.readLine().split("");
			for(int j=0; j<M; ++j) {
				map[i][j] = Integer.parseInt(str[j]);
				if(top<map[i][j]) top = map[i][j];
			}
		}
		
		for(int i=1; i<top; ++i) {
			visited = new boolean[N][M];
			for(int r=1; r<N; ++r) { // 테두리는 어차피 물을 채울 수 없으므로 무시
				for(int c=1; c<M; ++c) {
					if(!visited[r][c]) {
						func(r,c);
					}
				}
			}
		}
		
		
	}
	
	public static void func(int r, int c) {
		
	}
}
