package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 달이 차오른다, 가자.
/*
 * 이 문제는 visited배열을 3차원으로 생성하여,
 * r,c칸을 어떤 열쇠를 들고 있는 상태로 지나갔는지를 체크하는 것이 핵심인 것 같다.
 * 그리고 문제를 제일 끝까지 잘 읽자! 하나의 열쇠로 같은 종류의 문 여러개를 다 열 수 있다.
 */
public class Baekjoon_1194 {
	
	static int N,M,result;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 상,우,하,좌
	static char[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new char[N][M];
		for(int i=0; i<N; ++i) {
			map[i] = br.readLine().toCharArray();
		}
		int startR=0;
		int startC=0;
	loop:for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(map[i][j]=='0') { // 시작 위치를 찾기
					startR=i;
					startC=j;
					break loop;
				}
			}
		}
		
		result = Integer.MAX_VALUE;
		bfs(startR,startC);
		if(result==Integer.MAX_VALUE) result = -1;
		System.out.println(result);
	}
	public static void bfs(int startR, int startC) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {startR,startC,0,0}); // 행,열,이동횟수,key보유 비트마스킹
		
		// 열쇠를 몇개 들고 그 위치를 방문했는지를 체크하는 visited 배열 생성
		boolean[][][] visited = new boolean[N][M][1<<6]; // 알파벳 6개 이므로 비트마스킹을 위해 1<<(0~5)를 다 담기 위해 1<<6 만큼 생성
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r=cur[0]; // 행
			int c=cur[1]; // 열
			int cnt=cur[2]; // 이동횟수
			int key=cur[3]; // key보유상태
			
			if(cnt>=result || visited[r][c][key]) continue; // 현재 이동거리가 기존 최솟값 이상이거나 이미 방문한 곳이면 가지치기
			visited[r][c][key] = true; // 해당 좌표를 해당키 보유상태로 방문 표시
			
			// 4방탐색
			for(int i=0; i<4; i++) {
				int nr=r+dir[i][0];
				int nc=c+dir[i][1];
				if(isValid(nr,nc)) { // 갈 수 있는 칸이면
					char ch = map[nr][nc];
					if(ch>='a' && ch<='f') { // 해당칸이 열쇠이면
						int newKey = key | 1<<(ch-'a');
						q.offer(new int[] {nr,nc,cnt+1,newKey}); // 다음칸의 정보를 큐에 추가
					}else if(ch>='A' && ch<='F') { // 해당칸이 문이면
						if(canOpen(key,ch)) { // 문을 열 수 있으면
							q.offer(new int[] {nr,nc,cnt+1,key});
						}
					}else if(ch=='1'){ // 도착했으면 이동횟수 최솟값 갱신
						if(result>cnt+1) result = cnt+1;
					}else { // 해당칸이 일반 칸이면
						q.offer(new int[] {nr,nc,cnt+1,key});
					}
				}
			}
		}
	}
	public static boolean isValid(int r, int c) { // 경계 및 벽 체크
		if(r>=0 && r<N && c>=0 && c<M && map[r][c]!='#') return true;
		return false;
	}
	public static boolean canOpen(int key, char ch) { // 해당 문열 열 수 있는 키를 보유하고 있는지 체크
		if((key & (1<<(ch-'A'))) > 0) return true; // 비트연산이 하나라도 일치하면 1이상이므로
		return false;
	}
}