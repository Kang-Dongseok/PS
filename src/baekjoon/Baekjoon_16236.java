package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 아기 상어
/*
 * 1. bfs를 이용하여 가장 가까운 물고기를 찾는다.
 * 2. 상어를 이동시키고 총 이동거리를 더한다.
 * 3. 상어의 크기 증가를 확인한다.
 * 4. 1~3을 1번이 가능할 떄 까지 계속 반복한다.
 * 시간복잡도: O(N^4), 2차원 배열 전체를 한 번 bfs로 탐색할 때 마다 최악의 경우 N^2이고, 최대 N^2번 만큼 하므로 N^4 인 것 같다.
 * 하지만 N이 최대 20 이므로 20^4 = 160000 이므로 시간초과는 걱정하지 않았다.
 * 주의할점: 처음에 상어의 위치를 9로 계속 표시했다가 사이즈가 10이 되는순간부터는 문제가 발생한다는 것을 눈치채지 못해서 시간이 좀 걸렸다.
 */
public class Baekjoon_16236 {

	static int N,result;
	static int sharkR,sharkC,sharkLevel; // 상어위치의 행,열, 상어의 크기
	static int eatenFishCnt; // 먹은 물고기 수(상어 사이즈 증가하면 초기화)
	static int[][] map;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 상,우,하,좌
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		N = Integer.parseInt(br.readLine());
		sharkLevel = 2; // 초기의 상어 크기
		map = new int[N][N];
		visited = new boolean[N][N];
		for(int i=0; i<N; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<N; ++j) {
				int val = Integer.parseInt(str[j]);
				map[i][j] = val;
				if(val==9) { // 상어를 찾아서 위치 저장
					sharkR = i;
					sharkC = j;
				}
			}
		}

		while(true) {
			int target[] = findNearestFish(); // 가장 가까운 물고기 찾기
			if(target==null) break; // 물고기 몾찾으면 그만
			eatFish(target); // 먹이를 잡으러 이동
			levelUpCheck();
			visitedReset(); // visited 배열을 false로 초기화
		}
		System.out.println(result);
	}
	public static int[] findNearestFish() {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {sharkR,sharkC}); // 현재 상어의 위치
		visited[sharkR][sharkC] = true; // 현재 상어위치 방문처리
		
		int targetR = Integer.MAX_VALUE; // 목표 물고기의 행
		int targetC = Integer.MAX_VALUE; // 목표 물고기의 열
		
		int depth = 0;
		int distance = 0; // 이동거리
		while(!q.isEmpty()) {
			int size = q.size();
			while(--size>=0) {
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];
				if(map[r][c] != 0 && map[r][c]<sharkLevel) { // 상어가 먹을 수 있는 물고기면
					distance=depth;
					if((r==targetR && c<targetC) || r<targetR) { // 같은 행이고 왼쪽에 있거나 혹은 더 위애 있으면
							targetR = r;
							targetC = c;
					}
					continue; // 더 먼거리는 탐색 안함
				}
				for(int i=0; i<4; ++i) {
					int nr = r+dir[i][0];
					int nc = c+dir[i][1];
					if(isValid(nr,nc)) { // 상어보다 사이즈가 이하인 곳이면
						q.offer(new int[] {nr,nc});
						visited[nr][nc] = true;
					}
				}
			}
			depth++;
			if(distance>0) { // 먹을 수 있는 물고기를 발견하면
				result += distance; // 최종 이동거리 증가
				return new int[] {targetR,targetC}; // 탐색 종료
			}
		}
		return null; // 물고기 발견 못하면 null 리턴
	}
	public static void eatFish(int[] target) {
		map[sharkR][sharkC]=0; // 기존의 상어 위치를 0으로 변경
		// 상어를 물고기 위치로 이동
		sharkR = target[0];
		sharkC = target[1];
		map[sharkR][sharkC]=0; // 상어의 위치를 0으로 표시
		eatenFishCnt++; // 먹은 물고기수 증가
		
	}
	public static void levelUpCheck() { // 상어 크기 증가 확인
		if(eatenFishCnt==sharkLevel) {
			sharkLevel++; // 상어 크기 증가
			eatenFishCnt = 0; // 0으로 초기화
		}
	}
	public static void visitedReset() { // false로 초기화
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				visited[i][j]=false;
			}
		}
	}
	public static boolean isValid(int r, int c) { // 경계 및 방문 및 물고기와 상어의 크기 비교
		return (r>=0 && r<N && c>=0 && c<N && !visited[r][c] && map[r][c]<=sharkLevel);
	}
}