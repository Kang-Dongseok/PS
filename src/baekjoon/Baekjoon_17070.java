package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 파이프 옮기기1
/*
 * 
 */
public class Baekjoon_17070 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N+1][N+1]; // 번호가 1번부터 시작이므로 N+1
		String[] str;
		for(int i=0; i<N; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<N; ++j) {
				map[i+1][j+1] = Integer.parseInt(str[j]);
			}
		}
		int[][][] D = new int[N+1][N+1][3]; // 3차원 인덱스 3개에는 2차원 해당좌표의 위(↓),왼쪽위(↘),왼쪽(→)에서 오는 파이프 경우의 수를 각각 저장
		D[1][2][2] = 1; //  초기화 작업 : (1,2)의 왼쪽에서 오는 파이프의 경우의 수가 1
		for(int i=1; i<=N; ++i) {
			for(int j=3; j<=N; ++j) { // 1,2열은 절대 파이프가 갈 수 없으므로 3열부터 시작
				if(map[i][j]==1) continue; // 벽이면 pass
				D[i][j][0] = D[i-1][j][0]+D[i-1][j][1]; // 위에서 오는 파이프수 = 윗칸의 위에서 오는 파이프수+윗칸의 왼쪽위에서 오는 파이프수
				D[i][j][1] = D[i-1][j-1][0]+D[i-1][j-1][1]+D[i-1][j-1][2]; // 왼쪽위에서 오는 파이프수 = 왼쪽윗칸의 모든 경우의 수
				D[i][j][2] = D[i][j-1][1]+D[i][j-1][2]; // 왼쪽에서 오는 파이프수 = 왼칸의 왼쪽위에서 오는 파이프수+왼칸의 왼쪽에서 오는 파이프수
				if(map[i-1][j]==1) { // 윗칸이 벽이면
					D[i][j][0] = D[i][j][1] = 0; // 윗칸, 왼쪽윗칸 에서 온거 취소
				}
				if(map[i-1][j-1]==1) { // 왼쪽윗칸이 벽이면
					D[i][j][1] = 0; // 왼쪽윗칸 에서 온거 취소
				}
				if(map[i][j-1]==1) { // 왼칸이 벽이면
					D[i][j][2] = D[i][j][1] = 0;  // 왼칸, 왼쪽윗칸 에서 온거 취소
				}
			}
		}
		int result = D[N][N][0]+D[N][N][1]+D[N][N][2]; // 마지막칸의 각각의 방향에서 온 경우의 수 다 더해줌
		System.out.println(result);
		
	}
}
