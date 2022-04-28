package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 스타트 택시
/*
 * BFS이용한 단순 시뮬문제지만 예외케이스를 잘 처리해야한다.
 * 1. 손님을 태우는 길이 막힌 경우
 * 2. 손님을 태웠지만 목적지에 가는 길이 막힌 경우
 * 3. 손님이 내리자마자 다른 손님이 타는 경우
 */
public class Baekjoon_19238 {
	
	static int N,M,fuel;
	static int carR,carC;
	static int[][] map;
	static int[][] dest; // 목적지
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 상우하좌
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		fuel = Integer.parseInt(str[2]);
		map = new int[N+2][N+2]; // 바깥 패딩
		for(int r=0; r<N+2; ++r) { // 좌우 패딩
			map[r][0]=map[r][N+1]=1;
		}
		for(int c=0; c<N+2; ++c) { // 위아래 패딩
			map[0][c]=map[N+1][c]=1;
		}
		for(int r=1; r<=N; ++r) {
			str = br.readLine().split(" ");
			for(int c=1; c<=N; ++c) {
				map[r][c] = Integer.parseInt(str[c-1]);
			}
		}
		str = br.readLine().split(" ");
		carR = Integer.parseInt(str[0]);
		carC = Integer.parseInt(str[1]);
		dest = new int[M+2][2]; // 1~M+1인덱스 사용
		for(int i=0; i<M; ++i) {
			str = br.readLine().split(" ");
			int r = Integer.parseInt(str[0]);
			int c = Integer.parseInt(str[1]);
			map[r][c]=i+2; // map에 사람의 번호(2~M+1번)를 저장
			r = Integer.parseInt(str[2]);
			c = Integer.parseInt(str[3]);
			dest[i+2][0] = r; // 목적지의 행
			dest[i+2][1] = c; // 목적지의 열
		}
		for(int i=0; i<M; ++i) { // M번 실행
			int personNum = goToPerson(); // 최근사람에게 가기 & 연료 소모
			if(fuel<=0 || personNum==-1) { // 남은 연료가 0이하이거나 사람을 못 찾으면
				System.out.println(-1);
				return;
			}
			int add = goToDest(personNum); // 목적지에 가기 & 연료 소모
			if(fuel<0 || add==-1) { // 연료가 음수이거나 목적지까지 이동 불가하면
				System.out.println(-1);
				return;
			}
			fuel += add*2; // 거리의 2배 연료 충전
		}
		System.out.println(fuel);
	}
	
	public static int goToPerson() {
		int personNum = -1; // 사람 번호, -1을 반환하면 가능한 승객 없음
		boolean[][] visited = new boolean[N+2][N+2];
		int personR = 100; // 손님의 행 초기화(20을 넘기지 않으므로)
		int personC = 100; // 손님의 열
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {carR,carC});
		int dist = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-->0) {
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];
				if(visited[r][c]) continue;
				visited[r][c] = true;
				if(map[r][c]>1) { // 해당칸에 사람이 있으면
					if(r<personR) {
						personNum = map[r][c]; // 사람번호 저장
						personR=r; // 사람의 위치 저장
						personC=c;
					}else if(r==personR) {
						if(c<personC) {
							personNum = map[r][c]; // 사람번호 저장
							personC=c; // 사람의 위치 저장
						}
					}
				}
				for(int d=0; d<4; ++d) {
					int nr = r+dir[d][0];
					int nc = c+dir[d][1];
					if(!visited[nr][nc] && map[nr][nc]!=1) { // 방문 체크,벽체크
						q.offer(new int[] {nr,nc});
					}
				}
			} // end while
			if(personR!=100) { // 한명이라도 사람을 발견하면 그만 탐색
				carR = personR; // 차 위치 이동
				carC = personC;
				fuel -= dist; // 연료 소모
				map[personR][personC] = 0; // 사람은 빈칸으로 변경
				return personNum;
			}
			dist++; // 거리 증가
		} // end while
		return personNum;
	}
	
	public static int goToDest(int personNum) {
		boolean[][] visited = new boolean[N+2][N+2];
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {carR,carC});
		int dist = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-->0) {
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];
				if(visited[r][c]) continue;
				visited[r][c] = true;
				if(r==dest[personNum][0] && c==dest[personNum][1]) { // 목적지에 도착했으면
					carR = r; // 차 위치 이동
					carC = c;
					fuel -= dist; // 연료 소모
					return dist; 
				}
				for(int d=0; d<4; ++d) {
					int nr = r+dir[d][0];
					int nc = c+dir[d][1];
					if(!visited[nr][nc] && map[nr][nc]!=1) { // 방문 체크,벽체크
						q.offer(new int[] {nr,nc});
					}
				}
			} // end while
			dist++; // 거리 증가
		} // end while
		return -1; // -1을 반환하면 목적지까지 이동 불가
	}
}