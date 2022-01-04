package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 주사위 굴리기
/*
 * 윗면, 북쪽면, 오른쪽면을 항상 기억하고, 맞은편의 두 수의 합이 7인것을 이용하여 반대편을 구할 수 있다.
 * 주사위를 움직일 때 마다 바뀌는 면을 새로 갱신하고, 규칙에 따라서 지도 혹은 주사위의 숫자를 변경한다.
 */
public class Baekjoon_14499 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dice = new int[7];
		int N,M,x,y,k;
		int up=1,north=2,east=3;
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		x = Integer.parseInt(line[2]);
		y = Integer.parseInt(line[3]);
		k = Integer.parseInt(line[4]);
		
		int[][] map = new int[N][M];
		for(int i=0; i<N; ++i) {
			line = br.readLine().split(" ");
			for(int j=0; j<M; ++j) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		line = br.readLine().split(" ");
		for(int i=0; i<k; ++i) {
			int d = Integer.parseInt(line[i]); // 동(1), 서(2), 남(4), 북(3) 중 하나
			if(d==1) { // 동
				if(y>=M-1) continue;
				y++;
				int west = 7-east; // 동쪽의 반대편인 서쪽
				east = up;
				up = west;
			}else if(d==2) { // 서
				if(y<=0) continue;
				y--;
				int down = 7-up; // 위쪽의 반대편인 아래쪽
				up = east;
				east = down;
			}else if(d==3) { // 북
				if(x<=0) continue;
				x--;
				int south = 7-north; // 북쪽의 반대편인 남쪽
				north = up;
				up = south;
			}else { // 남
				if(x>=N-1) continue;
				x++;
				int down = 7-up; // 위쪽의 반대편인 아래쪽
				up = north;
				north = down;
			}
			if(map[x][y]==0) map[x][y]=dice[7-up]; // 지도에 0이면 아랫면의 숫자를 바닥에 저장
			else { // 지도에 0이 아니면 그 숫자를 주사위 아랫면에 저장 후 지도는 0으로 표시
				dice[7-up]=map[x][y];
				map[x][y]=0;
			}
			System.out.println(dice[up]); // 위쪽면에 저장된 숫자를 출력
		}
	}
}