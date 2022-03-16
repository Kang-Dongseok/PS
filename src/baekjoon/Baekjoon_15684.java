package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 사다리 조작
public class Baekjoon_15684 {

	static int N,M,H;
	static int totalNum;
	static int answer;
	static boolean[][] check;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		H = Integer.parseInt(line[2]);
		check = new boolean[H][N]; // 오른쪽으로 가로선이 있는 위치에 true
		for(int i=0; i<M; ++i) {
			line = br.readLine().split(" ");
			int a = Integer.parseInt(line[0]);
			int b = Integer.parseInt(line[1]);
			check[a-1][b-1]=true;
		}

		answer = 4;
		dfs(0,0,0);
		if(answer==4) answer = -1;
		System.out.println(answer);
	}
	
	public static void dfs(int r, int c, int num) {
		if(answer<=num) return;
		if(goAllLadder()) { // 모든 사다리가 제자리 찾으면
			answer = num; // 최솟값 갱신
			return; // 종료
		}
		
		int nr=r,nc=c;
		if(nc==N) {
			nr++; nc=0;
		}
		for(; nr<H; ++nr) {
			for(; nc<N-1; ++nc) {
				if(!check[nr][nc]) { // 다음칸이 사다리 없으면
					check[nr][nc]=true; // 사다리 설치
					dfs(nr, nc, num+1); // 다음좌표부터 시작
					check[nr][nc]=false; // 사다리 제거
				}
			}
			nc=0;
		}
	}
	
	public static boolean goAllLadder() {
		for(int i=0; i<N; ++i) {
			if(goStraight(i)!=i) return false;
		}
		return true;
	}

	public static int goStraight(int n) {
		int col = n;
		for(int r=0; r<H; ++r) {
			if(col>0 && check[r][col-1]) col--; // 왼쪽가로선 존재하면 왼쪽 이동
			else if(check[r][col]) col++; // 오른쪽가로선 존재하면 오른쪽 이동
		}
		return col;
	}
}