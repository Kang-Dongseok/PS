package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 색종이
public class Baekjoon_2563 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 색종이 수
		int[][] arr = new int[100][100];
		String[] str;
		for(int i=0; i<n; i++) {
			str = br.readLine().split(" ");
			int x = Integer.parseInt(str[0]);
			int y = Integer.parseInt(str[1]);
			for(int j=x-1;j<x+9;j++) {
				for(int k=y-1;k<y+9;k++) {
					arr[j][k] = 1;
				}
			}
		}
		int sum = 0;
		for(int[] ar : arr) {
			for(int a : ar) {
				sum += a;
			}
		}
		
		System.out.println(sum);
		
	}

}
