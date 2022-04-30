package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 새
public class Baekjoon_1568 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int num = 1;
		int t = 0;
		while(N>0) {
			if(N<num) num = 1;
			N -= num++;
			t++;
		}
		System.out.println(t);
	}
}