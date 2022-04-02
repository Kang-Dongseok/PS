package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 마법사 상어와 파이어볼
public class Baekjoon_20056 {
	
	static class FireBall {
		int r,c,m,s,d;

		public FireBall(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
		
	}
	
	static int N,M,K;
	static FireBall[][] map;
	static int[][] count;
	static ArrayList<FireBall> list;
	static int[][] dir = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		K = Integer.parseInt(str[2]);
		map = new FireBall[N][N];
		count = new int[N][N];
		list = new ArrayList<FireBall>();
		for(int i=0; i<M; ++i) {
			str = br.readLine().split(" ");
			int r = Integer.parseInt(str[0])-1;
			int c = Integer.parseInt(str[1])-1;
			int m = Integer.parseInt(str[2]);
			int s = Integer.parseInt(str[3]);
			int d = Integer.parseInt(str[4]);
			FireBall fb = new FireBall(r, c, m, s, d);
			list.add(fb);
		}
		for(int i=0; i<K; ++i) {
			move(); // 파이어볼 이동
			split(); // 파이어볼 4개로 나누고 list에 파이어볼 저장
		}
		int answer = 0;
		for(FireBall fb : list) {
			answer += fb.m;
		}
		System.out.println(answer);
	}
	
	public static void move() {
		for(FireBall fb : list) {
			int r = fb.r;
			int c = fb.c;
			int nr = r+dir[fb.d][0]*(fb.s%N); // 다음 좌표
			int nc = c+dir[fb.d][1]*(fb.s%N);
			nr += N; // 양수화
			nr %= N; // 모듈로
			nc += N; // 양수화
			nc %= N; // 모듈로
			fb.r = nr;
			fb.c = nc;
			
			if(map[nr][nc]==null) { // 빈칸이면
				map[nr][nc] = fb; // 파이어볼 저장
			}else { // 이미 다른 파이어볼이 있으면
				FireBall cur = map[nr][nc]; // 기존의 파이어볼
				cur.m += fb.m; // 질량 합치기
				cur.s += fb.s; // 속도 합치기
				if(cur.d>=0) { // 방향이 통일되어 있으면
					if((cur.d + fb.d)%2 != 0) { // 홀이나 짝이 다르면
						cur.d = -1; // 방향을 음수로 처리
					}	
				}
			}
			count[nr][nc] += 1;
		}
	}

	public static void split() {
		list.clear();
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				int cnt = count[i][j];
				if(cnt==1) { // 파이어볼 1개면
					FireBall fb = map[i][j];
					list.add(fb); // 리스트에 추가
				}else if(cnt>1) { // 파이어볼 2개 이상겹치면
					FireBall fb = map[i][j];
					int r = fb.r;
					int c = fb.c;
					int m = fb.m/5;
					if(m>0) { // 질량이 0이 아니면
						int s = fb.s/cnt;
						int d = fb.d;
						if(d>=0) { // 방향이 모두 같으면 0,2,4,6
							list.add(new FireBall(r, c, m, s, 0));
							list.add(new FireBall(r, c, m, s, 2));
							list.add(new FireBall(r, c, m, s, 4));
							list.add(new FireBall(r, c, m, s, 6));
						}else { // 방향이 다르면 1,3,5,7
							list.add(new FireBall(r, c, m, s, 1));
							list.add(new FireBall(r, c, m, s, 3));
							list.add(new FireBall(r, c, m, s, 5));
							list.add(new FireBall(r, c, m, s, 7));
						}
					}
				}
				 // 빈칸으로 초기화
				map[i][j] = null;
				count[i][j]=0;
			}
		}
	}
}