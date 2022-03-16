package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 색종이 만들기
public class Baekjoon_2630 {

	static int white,blue;
	static int[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		String[] str;
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		func(0,0,N);
		System.out.println(white);
		System.out.println(blue);
		
	}
	
	public static void func(int r, int c, int size) { // (r,c)에서 size만큼의 사각형
		int color = arr[r][c];
		int rowEnd = r+size;
		int colEnd = c+size;
		for(int i=r; i<rowEnd; ++i) {
			for(int j=c; j<colEnd; ++j) {
				if(arr[i][j] != color) { // 다른 색깔이 발견되면
					func(r,c+size/2,size/2); // 1사분면
					func(r,c,size/2); // 2사분면
					func(r+size/2,c,size/2); // 3사분면
					func(r+size/2,c+size/2,size/2); // 4사분면
					return;
				}
			}
		}
		// 사각형 안에 전부 다 같은 색이면
		if(color==0) ++white;
		else ++blue;
	}
}
