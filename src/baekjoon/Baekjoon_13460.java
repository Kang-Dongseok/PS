package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 구슬 탈출2
/*
 * 4방향이 10번이므로 4^10=1,048,576 시간초과는 걱정하지 않고,
 * dfs를 이용하였으며, 백트래킹을 살짝 적용하였다.
 * 
 * 백트랙킹: 둘다 안움직이면 무시, 직전의 방향과 반대방향으로 움직임은 무시
 */
public class Baekjoon_13460 {

	static int N,M,answer;
	static int[] posR=new int[2],posB=new int[2]; // R과B의 초기 좌표
	static char[][] map;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 상,우,하,좌
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		map = new char[N][M];
		for(int i=0; i<N; ++i) {
			map[i] = br.readLine().toCharArray();
		}
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(map[i][j]=='R') {
					posR[0]=i;
					posR[1]=j;
					map[i][j] = '.'; // R의 좌표만 알아내고 빈칸으로 바꾸기
				}else if(map[i][j]=='B') {
					posB[0]=i;
					posB[1]=j;
					map[i][j] = '.'; // B의 좌표만 알아내고 빈칸으로 바꾸기
				}
			}
		}

		answer=11; // 11번 이동은 불가능하므로 11로 초기화
		dfs(0,posR,posB,-3); // 0번 이동했고, posR과 posB위치에 존재, 시작시 이전의 방향은 존재안하므로 로직에 오류가 없는 적당한 값으로 시작
		if(answer==11) System.out.println(-1); // 10번이내에 답이 없으면
		else System.out.println(answer);
	}
	
	public static void dfs(int n, int[] posR, int[] posB, int prevDir) { // n번 이동 후 posR과 posB의 위치
		if(n==10) return; // 이미 10번 이동했으면 종료
		
		for(int d=0; d<4; ++d) {
			if(Math.abs(prevDir-d)==2) continue; // 직전의 방향과 반대이면 무시, 백트랙킹
			int[] nextPos = move(posR,posB,d);
			int[] nextR = new int[] {nextPos[0],nextPos[1]}; // 이동 후 R의 좌표
			int[] nextB = new int[] {nextPos[2],nextPos[3]}; // 이동 후 B의 좌표
			
			if(nextB[0]==-1 && nextB[1]==-1) continue; // B가 구멍에 빠지면 무시
			if(nextR[0]==-1 && nextR[1]==-1) { // R가 구멍에 빠지면
				if(answer>n+1) answer = n+1; // 최솟값 갱신
				return; // 더 이상 진행할 필요 없으므로 종료
			}
			
			// 둘 다 구멍에 안빠지면
			
			// R과B 모두 이동을 하지 않으면 무시, 백트랙킹
			if(posR[0]==nextR[0] && posR[1]==nextR[1] && posB[0]==nextB[0] && posB[1]==nextB[1]) continue;
			// R과 B중 하나라도 이동을 했으면 다음단계로 진행
			dfs(n+1, nextR, nextB, d); // 횟수 1증가시킨 후 이동한 방향과 좌표를 dfs에 적용
		}
	}
	
	public static int[] move(int[] posR, int[] posB, int d) {
		int[] nextR,nextB; // 빨간구슬과 파란구슬의 다음위치 저장
		if(d==0) { // 상
			if(posR[0]<=posB[0]) {
				nextR = moveOneDir(posR, posB, d);
				nextB = moveOneDir(posB, nextR, d);
			}else {
				nextB = moveOneDir(posB, posR, d);
				nextR = moveOneDir(posR, nextB, d);
			}
		}else if(d==1) { // 우
			if(posR[1]>=posB[1]) {
				nextR = moveOneDir(posR, posB, d);
				nextB = moveOneDir(posB, nextR, d);
			}else {
				nextB = moveOneDir(posB, posR, d);
				nextR = moveOneDir(posR, nextB, d);
			}
		}else if(d==2) { // 하
			if(posR[0]>=posB[0]) {
				nextR = moveOneDir(posR, posB, d);
				nextB = moveOneDir(posB, nextR, d);
			}else {
				nextB = moveOneDir(posB, posR, d);
				nextR = moveOneDir(posR, nextB, d);
			}
		}else { // 좌
			if(posR[1]<=posB[1]) {
				nextR = moveOneDir(posR, posB, d);
				nextB = moveOneDir(posB, nextR, d);
			}else {
				nextB = moveOneDir(posB, posR, d);
				nextR = moveOneDir(posR, nextB, d);
			}
		}
		return new int[] {nextR[0],nextR[1],nextB[0],nextB[1]}; // 앞에 2개는 R, 뒤에 2개는 B의 좌표
	}
	
	/**
	 * @param movePos : 움직이는 구슬 좌표
	 * @param stopPos : 정지해있는 나머지 한 구슬의 좌표
	 * @param d : 이동방향
	 * @return : 구멍에 빠지면 {-1,-1}, 아니면 이동한 좌표를 리턴
	 */
	public static int[] moveOneDir(int[] movePos, int[] stopPos, int d) {
		int r = movePos[0]; // 현재 행
		int c = movePos[1]; // 현재 열
		int nr = r+dir[d][0]; // 다음 행
		int nc = c+dir[d][1]; // 다음 열
		while(true) {
			if(map[nr][nc]=='O') return new int[] {-1,-1}; // 구멍에 빠지면 {-1,-1} 리턴
			if(map[nr][nc]!='.' || (nr==stopPos[0] && nc==stopPos[1])) break; // 빈칸이 아니거나 다른 구슬과 위치가 겹치면 종료 종료
			r = nr; c = nc; // 한칸이동
			nr += dir[d][0]; // 다음 행
			nc += dir[d][1]; // 다음 열
		}
		return new int[] {r,c};
	}
}