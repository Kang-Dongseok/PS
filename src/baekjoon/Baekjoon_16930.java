package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
 
// 달리기
/*
 * visited를 boolean배열 대신 int배열로 선언하였다.
 * 여기서는 이름을 visited 대신 count로 설정
 * 해당칸에 이동횟수를 저장하고, 다른칸에서 새로 움직이는 칸에 이동횟수를 비교하여
 * 1. 기존의 횟수보다 크면 그 방향은 무시
 * 2. 기존의 횟수와 같으면 그 방향으로 계속가며 초기화가 안된 칸만 이동횟수 저장
 * 3. 기존의 횟수보다 새로 움직이는 이동횟수가 작은 경우는 발생하지 않는다.
 * 1번의 케이스를 적용하지 않으면 시간초과 발생
 */
public class Baekjoon_16930 {

	static int startR,startC,endR,endC;
	static char[][] map;
	static int[][] count;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 상,우,하,좌 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int K = Integer.parseInt(str[2]);
		map = new char[N][M];
		for(int i=0; i<N; ++i) {
			map[i] = br.readLine().toCharArray();
		}
		count = new int[N][M];
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				count[i][j] = Integer.MAX_VALUE;
			}
		}
		str = br.readLine().split(" ");
		startR = Integer.parseInt(str[0])-1;
		startC = Integer.parseInt(str[1])-1;
		endR = Integer.parseInt(str[2])-1;
		endC = Integer.parseInt(str[3])-1;
		
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {startR,startC});
		count[startR][startC] = 0; // 시작점은 이동횟수 0
		
		int answer = -1; // 답이 없을 경우 -1
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			if(r==endR && c==endC) { // 마지막칸에 도착했으면
				break; // 이미 이동횟수가 저장되어 있으므로 종료
			}
				
			for(int d=0; d<4; ++d) {
				for(int i=1; i<=K; ++i) {
					int nr = r+i*dir[d][0];
					int nc = c+i*dir[d][1];
					int dist = count[r][c]+1; // 다음칸까지 이동횟수
					if(nr<0 || nr>=N || nc<0 || nc>=M || map[nr][nc]=='#') break; // 범위밖 또는 벽이면 종료
					if(count[nr][nc]<dist) break; // 기존보다 이동횟수 많으면 종료
					if(count[nr][nc]==Integer.MAX_VALUE && map[nr][nc]=='.') { // 초기화 안 된 빈칸이면
						count[nr][nc]=dist; // 다음칸 이동횟수 저장
						q.offer(new int[] {nr,nc}); // 다음칸 큐에 삽입
					}
				}
			}
		}
		if(count[endR][endC]!=Integer.MAX_VALUE) answer = count[endR][endC];
		System.out.println(answer);
	}
}