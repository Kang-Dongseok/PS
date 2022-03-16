package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 연구소
public class Baekjoon_14502 {

	static int N,M,result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		int[][] arr = new int[N][M];
		for(int i=0;i<N;i++) {
			str = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
	
		for(int i=0; i<N*M; ++i) {
			for(int j=i+1; j<N*M; ++j) {
				for(int k=j+1; k<N*M; ++k) {
					if(arr[i/M][i%M]==0 && arr[j/M][j%M]==0 && arr[k/M][k%M]==0) { // 3칸 모두 벽 세울 수 있으면
						chooseWall(i,j,k,arr); // 벽을 3칸 선택해서 진행하기
					}
				}
			}
		}
		System.out.println(result);
	}
	
	private static void chooseWall(int i, int j, int k, int[][] arr) {
		int[][] copiedArr = myCopy(arr);
		copiedArr[i/M][i%M] = 1; copiedArr[j/M][j%M] = 1; copiedArr[k/M][k%M] = 1; // 벽 세우기
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(copiedArr[r][c]==2) { // 바이러스를 찾아서 확산
					spreadVirus(r,c,copiedArr);
				}
			}
		}
		
		int cnt = 0; // 0의 갯수
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(copiedArr[r][c]==0) cnt++; // 0의 갯수 증가
			}
		}
		if(result<cnt) result = cnt;
	}
	private static void spreadVirus(int r, int c, int[][] arr) {
		arr[r][c]=2; // 빈칸을 바이러스로 확산
		if(isValid(r-1,c,arr)) spreadVirus(r-1,c,arr);
		if(isValid(r,c+1,arr)) spreadVirus(r,c+1,arr);
		if(isValid(r+1,c,arr)) spreadVirus(r+1,c,arr);
		if(isValid(r,c-1,arr)) spreadVirus(r,c-1,arr);
		
	}
	private static boolean isValid(int r, int c, int[][] arr) {
		if(r>=0 && c>=0 && r<N && c<M && arr[r][c]==0) return true;
		return false;
	}
	private static int[][] myCopy(int[][] arr) { // 2차원 배열 깊은 복사
		int[][] copiedArr = new int[N][];
		for(int i=0; i<N; ++i) {
			copiedArr[i] = arr[i].clone();
		}
		return copiedArr;
	}
}