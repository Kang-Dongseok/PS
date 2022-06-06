package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 과제 안 내신 분..?
public class Baekjoon_5597 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] arr = new boolean[31];
		for(int i=1; i<=28; ++i) {
			int n = Integer.parseInt(br.readLine());
			arr[n] = true;
		}
		for(int i=1; i<=30; ++i) {
			if(!arr[i]) {
				System.out.println(i);
			}
		}
	}
}