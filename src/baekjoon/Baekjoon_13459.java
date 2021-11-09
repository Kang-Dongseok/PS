package baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 구슬 탈출
/*
 * 4방향이 10번이므로 4^10이므로 시간초과는 걱정하지 않고,
 * dfs를 이용하여 완탐으로 풀었다.
 */
public class Baekjoon_13459 {

	static int N,M;
	static int[] posR=new int[2],posB=new int[2]; // R과B의 초기 좌표
	static char[][] map;
	static boolean answer;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 상,우,하,좌
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
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
		dfs(0,posR,posB);
		if(answer) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
	}
	
	public static void dfs(int n, int[] posR, int[] posB) {
		if(n==10) return; // 10회 다 이동하면 그만
		
		for(int d=0; d<4; ++d) {
			int[][] balls = moveBoth(posR, posB, d);
			int[] nextR = balls[0];
			int[] nextB = balls[1];
			if(nextB[0]==-1) continue; // B가 구멍에 빠지면
			if(nextR[0]==-1) { // B가 구멍에 빠지지 않고, R가 구멍에 빠지면
				answer = true; // 정답
				return;
			}
			dfs(n+1, nextR, nextB); // R과B가 이동한 위치에서 다음 단계 실행
		}
		
	}
	public static int[][] moveBoth(int[] posR, int[] posB, int d) {
		int[] nextR = new int[2]; // 이동 후 R의 좌표
		int[] nextB = new int[2]; // 이동 후 B의 좌표
		
		if(d==0) { // 상
			if(posR[0]<posB[0]) { // R이 더 위에 있으면
				nextR = moveBall(posR, posB, d); // R이동
				nextB = moveBall(posB, nextR, d); // B이동
			}else { // 아니면 B먼저 이동
				nextB = moveBall(posB, posR, d); // B이동
				nextR = moveBall(posR, nextB, d); // R이동
			}
		}else if(d==1) { // 우
			if(posR[1]>posB[1]) { // R이 더 오른쪽에 있으면
				nextR = moveBall(posR, posB, d); // R이동
				nextB = moveBall(posB, nextR, d); // B이동
			}else { // 아니면 B먼저 이동
				nextB = moveBall(posB, posR, d); // B이동
				nextR = moveBall(posR, nextB, d); // R이동
			}
		}else if(d==2) { // 하
			if(posR[0]>posB[0]) { // R이 더 아래에 있으면
				nextR = moveBall(posR, posB, d); // R이동
				nextB = moveBall(posB, nextR, d); // B이동
			}else { // 아니면 B먼저 이동
				nextB = moveBall(posB, posR, d); // B이동
				nextR = moveBall(posR, nextB, d); // R이동
			}
		}else { // 좌
			if(posR[1]<posB[1]) { // R이 더 왼쪽에 있으면
				nextR = moveBall(posR, posB, d); // R이동
				nextB = moveBall(posB, nextR, d); // B이동
			}else { // 아니면 B먼저 이동
				nextB = moveBall(posB, posR, d); // B이동
				nextR = moveBall(posR, nextB, d); // R이동
			}
		}
		return new int[][] {nextR,nextB};
	}
	public static int[] moveBall(int[] mainBall, int[] subBall, int d) {
		int r = mainBall[0];
		int c = mainBall[1];
		int nr = r+dir[d][0];
		int nc = c+dir[d][1];
		while(map[nr][nc]!='#' && !(nr==subBall[0] && nc==subBall[1])) { // 벽이 아니고 다른구슬과 겹치지 않으면
			if(map[nr][nc]=='O') { // 구멍에 들어가면
				return new int[] {-1,-1};
			}
			// 그냥 빈칸이면
			r = nr; c = nc;
			nr += dir[d][0];
			nc += dir[d][1];
		}
		return new int[] {r,c};
	}
}