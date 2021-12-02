package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 어른 상어
public class Baekjoon_19237 {

	static class Shark {
		int r,c,num,dir;
		boolean isAlive = true;
		int[][] dirPriority = new int[5][4];
	}
	static int N,M,K,aliveSharkNum;
	static int[][] map;
	static int[][] dir = {{0,0},{-1,0},{1,0},{0,-1},{0,1}}; // 상,하,좌,우
	static Shark[] sharkList;
	static int[][][] spreadMap;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		K = Integer.parseInt(str[2]);
		aliveSharkNum = M; // 현재 생존한 상어의 수
		map = new int[N][N];
		spreadMap = new int[N][N][2];
		sharkList = new Shark[M+1];
		for(int i=1; i<=M; ++i) { // 상어리스트 초기화
			sharkList[i] = new Shark();
		}
		for(int i=0; i<N; ++i) { // 초기 상태의 map
			str = br.readLine().split(" ");
			for(int j=0; j<N; ++j) {
				int num = Integer.parseInt(str[j]);
				map[i][j] = num;
				if(num!=0) { // 해당 칸이 상어이면 상어정보 저장
					sharkList[num].r = i;
					sharkList[num].c = j;
					sharkList[num].num = num;
				}
			}
		}
		
		// 시작 시 상어들의 방향 초기화
		str = br.readLine().split(" ");
		for(int i=0; i<M; ++i) {
			sharkList[i+1].dir = Integer.parseInt(str[i]);
		}
		
		// 상어들의 이동방향 우선순위 저장
		for(int i=1; i<=M; ++i) {
			for(int j=1; j<=4; ++j) {
				str = br.readLine().split(" ");
				for(int k=0; k<4; ++k) {
					sharkList[i].dirPriority[j][k] = Integer.parseInt(str[k]);
				}
			}
		}
		
		for(int t=1; t<=1000; t++) { // 1000번 실행
			spread(); // 냄새 뿌리기
			moveAll(); // 모든 상어 이동하기
			countDown(); // 냄새 시간 줄이기
			if(aliveSharkNum==1) { // 상어가 1마리 남으면 시간 출력 후 종료
				System.out.println(t);
				return;
			}
		}
		System.out.println(-1);
	}
	public static void spread() { // 격자안에 남은 상어들의 위치에 냄새 뿌리기
		for(int i=1; i<=M; ++i) {
			if(!sharkList[i].isAlive) continue; // 상어가 격자안에 없으면 pass
			int r = sharkList[i].r;
			int c = sharkList[i].c;
			spreadMap[r][c][0] = i; // 상어번호 저장
			spreadMap[r][c][1] = K; // 사라지기까지 남은 시간 저장
		}
	}
	public static void moveAll() { // 모든 상어 이동
		for(int i=1; i<=M; ++i) {
			if(!sharkList[i].isAlive) continue; // 상어가 격자안에 없으면 pass
			Shark shark = sharkList[i];
			moveOne(shark);
		}
		
		// 중복된 상어 격자밖으로 보내기
		for(int i=1; i<=M; ++i) { // 이동한 상어들의 번호를 해당 위치에 저장
			if(!sharkList[i].isAlive) continue; // 상어가 격자안에 없으면 pass
			if(map[sharkList[i].r][sharkList[i].c] == 0) { // 격자에 빈칸이면 젤 작은 상어번호를 저장
				map[sharkList[i].r][sharkList[i].c] = i;
			}else { // 이미 해당 위치에 번호가 작은 상어 존재하므로 해당 상어는 격자 밖으로 out
				sharkList[i].isAlive = false;
				aliveSharkNum--; // 생존한 상어 감소
			}
		}
	}
	public static void moveOne(Shark shark) { // 상어 한마리 이동
		int r = shark.r;
		int c = shark.c;
		map[r][c] = 0; // 원래 위치는 0으로 표시
		for(int d=0; d<4; ++d) {
			int nextDir = shark.dirPriority[shark.dir][d];
			int nr = r+dir[nextDir][0]; // 다음 행
			int nc = c+dir[nextDir][1]; // 다음 열
			if(isEmpty(nr,nc)) { // 경계 및 냄새 빈칸 체크
				shark.r = nr; // 다음칸으로 이동
				shark.c = nc;
				shark.dir = nextDir; // 바라보는 방향 수정
				return;
			}
		}
		// 인접한 칸이 모두 냄새가 있으면
		for(int d=0; d<4; ++d) {
			int nextDir = shark.dirPriority[shark.dir][d];
			int nr = r+dir[nextDir][0]; // 다음 행
			int nc = c+dir[nextDir][1]; // 다음 열
			if(isValid(nr,nc) && spreadMap[nr][nc][0]==shark.num) { // 경계 체크 및 본인 냄새 체크
				shark.r = nr; // 다음칸으로 이동
				shark.c = nc;
				shark.dir = nextDir; // 바라보는 방향 수정
				return;
			}
		}
	}
	public static void countDown() { // 냄새들 1초씩 감소
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(spreadMap[i][j][0]!=0) { // 냄새가 존재하면
					if(--spreadMap[i][j][1]==0) { // 1초 감소
						spreadMap[i][j][0]=0; // 남은 시간이 0이면 빈칸으로 바꾸기
					}
				}
			}
		}
	}
	public static boolean isValid(int r, int c) { // 경계 체크
		return r>=0 && c>=0 && r<N && c<N;
	}
	public static boolean isEmpty(int r, int c) { // 냄새 빈칸 체크
		return isValid(r, c) && (spreadMap[r][c][0] == 0);
	}
}
