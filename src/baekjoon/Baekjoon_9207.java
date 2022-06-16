package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 페그 솔리테어
/*
 * dfs형식으로 풀기때문에 map을 static으로 선언 후
 * map을 직접 변경하고 다음 depth로 들어간 후 dfs를 나오면 다시 맵을 복구시키는 작업에 주의한다.
 */
public class Baekjoon_9207 {

	static int move;
	static char[][] map;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[5][9];
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<N; ++t) {
			move = 0; // 최소갯수 초기화
			int pinCnt = 0; // 처음 핀의 갯수
			for(int i=0; i<5; ++i) {
				String str = br.readLine();
				for(int j=0; j<9; ++j) {
					map[i][j] = str.charAt(j);
					if(map[i][j]=='o') pinCnt++;
				}
			}
			dfs(0);
			sb.append(pinCnt-move).append(" ").append(move).append("\n");
			if(t!=N-1) br.readLine(); // 마지막 테케 전까지 공백 읽기
		}
		System.out.print(sb.toString());
	}
	
	public static void dfs(int n) {
		if(n>move) {
			move = n;
		}
		
		for(int r=0; r<5; ++r) {
			for(int c=0; c<9; ++c) {
				if(map[r][c]=='o') {
					for(int d=0; d<4; ++d) {
						int nr = r+dir[d][0];
						int nc = c+dir[d][1];
						if(!isValid(nr, nc) || map[nr][nc]!='o') continue; // 경계 및 핀 체크
						
						int nnr = nr+dir[d][0];
						int nnc = nc+dir[d][1];
						if(!isValid(nnr, nnc) || map[nnr][nnc]!='.') continue; // 경계 및 빈칸 체크
						
						map[r][c]=map[nr][nc]='.'; // 변경
						map[nnr][nnc]='o'; // 변경
						dfs(n+1);
						map[r][c]=map[nr][nc]='o'; // 복구
						map[nnr][nnc]='.'; // 복구
					}
				}
			}
		}
		
	}
	
	public static boolean isValid(int r, int c) {
		return r>=0 && r<5 && c>=0 && c<9;
	}
}