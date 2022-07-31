package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Kuber
public class Baekjoon_20833 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int sum = 0;
		for(int i=1; i<=n; ++i) {
			sum += Math.pow(i, 3);
		}
		System.out.println(sum);
	}
}