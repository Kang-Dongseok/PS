package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 5의 수난
public class Baekjoon_23037 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int result = 0;
		String[] str = br.readLine().split("");
		for(int i=0; i<5; ++i) {
			result += Math.pow(Integer.parseInt(str[i]), 5);
		}
		System.out.println(result);
	}
}