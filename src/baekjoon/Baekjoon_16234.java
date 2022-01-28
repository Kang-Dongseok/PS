package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 인구 이동
/*
 * bfs를 이용하여 조건에 맞느 연합을 찾은 후 인구 이동을 시키고 visited배열을 이용하여 방문체크를 한다.
 * N*N을 한번 모두 체크한 후 특정 값을을 초기화하여 이동이 일어나지 않을때까지 반복한다.
 * 시간복잡도: O(N^2) 더 이상 인구 이동이 없을 떄 까지 매번 N*N배열을 전부 확인한다.
 */
public class Baekjoon_16234 {

	static int N,L,R,result;
	static int[][] map;
	static boolean isMoved; // 인구이동 여부
	static boolean[][] visited;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 상,우,하,좌
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		L = Integer.parseInt(line[1]);
		R = Integer.parseInt(line[2]);
		map = new int[N][N];
		for(int i=0; i<N; ++i) {
			line = br.readLine().split(" ");
			for(int j=0; j<N; ++j) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		result = 0;
		
		while(true) {
			// 한 번의 전체 이동마다 값 초기화
			isMoved = false;
			visited = new boolean[N][N];
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					if(!visited[i][j]) bfs(i,j);
				}
			}
			if(!isMoved) break; // 인구 인동이 없으면 그만
			result++; // 하루 증가
		}
		System.out.println(result);
	}
	
	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {x,y});
		
		ArrayList<int[]> union = new ArrayList<int[]>(); // 연합의 좌표를 담을 리스트
		int sumOfUnion = 0; // 연합의 총 인구수
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int curNum = map[r][c]; // 현재 위치의 인구수
			
			if(visited[r][c]) continue; // 이미 방문했으면 무시
			visited[r][c] = true; // 방문표시
			union.add(new int[] {r,c}); // 연합에 좌표 저장
			sumOfUnion+=curNum; // 연합의 총 인구수에 더하기
			
			for(int d=0; d<4; ++d) {
				int nr = r+dir[d][0]; // 다음 행
				int nc = c+dir[d][1]; // 다음 열
				if(nr<0 || nc<0 || nr>=N || nc>=N) continue; // 경계 밖이면 무시
				if(visited[nr][nc]) continue; // 이미 방문했으면 무시
				int nextNum = map[nr][nc]; // 다음 위치의 인구수
				int gap = Math.abs(nextNum-curNum);
				if(gap>=L && gap<=R) q.offer(new int[] {nr,nc}); // 인구 차이가 L이상 R이하이면 큐에 저장
			}
		}
		if(union.size()==1) return; // 연합을 이루는 나라가 1개밖에 없으면 종료
		isMoved=true; // 연합을 이루는 나라가 2개 이상이면 인구이동
		int averageNum = sumOfUnion/union.size(); // 이동 후 인구수 = (연합의 인구수) / (연합을 이루고 있는 칸의 개수)
		for(int[] pos : union) {
			map[pos[0]][pos[1]] = averageNum; // 인구 이동 후 평균 인구수로 조정
		}
	}
}