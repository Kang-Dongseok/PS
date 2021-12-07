package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 농장 관리
public class Baekjoon_1245 {

	static int N,M;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dir = {{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}}; // 북서쪽부터 시계방향 8방 탐색
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; ++i) {
			line = br.readLine().split(" ");
			for(int j=0; j<M; ++j) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		int result = 0;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(map[i][j]!=0 && !visited[i][j]) { // 방문 안한곳만 체크
					if(isHillTop(i,j)) result++; // 산봉우리이면 갯수 증가
				}
			}
		}
		System.out.println(result);
	}
	public static boolean isHillTop(int r, int c) { // 좌표 r,c가 산봉우리이면 true, 아니면 false 반환
		
		visited[r][c] = true; // 방문 체크
		
		boolean curFlag = true; // 현재 위치가 산봉우리인지 체크

		for(int i=0; i<8; ++i) { // 8방 탐색
			int nr = r+dir[i][0];
			int nc = c+dir[i][1];
			if(isValid(nr,nc)) { // 경계 체크
				if(map[r][c]<map[nr][nc]) curFlag = false; // 인접한 칸이 높으면 현재 위치는 산봉우리가 아니다
				else if(map[r][c]==map[nr][nc]) { // 인접한 칸이 같은 높이이면
					if(!visited[nr][nc]) { // 아직 방문하지 않은 곳이면
						boolean nextFlag = isHillTop(nr,nc); // 다음 위치 방문 및 산봉우리인지 체크
						if(curFlag) { // 현재 위치가 산봉우리이면 다음 위치의 산봉우리 여부에 따라 결정
							curFlag = nextFlag;
						}
					}
				}
			}
		}
		return curFlag;
	}
	public static boolean isValid(int r, int c) { // 경계 체크
		return r>=0 && c>=0 && r<N && c<M;
	}
}
