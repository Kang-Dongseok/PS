package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 더하기
public class Baekjoon_9085 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		int T = Integer.parseInt(br.readLine());
		for(int tc=0; tc<T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int sum = 0;
			str = br.readLine().split(" ");
			for(int i=0; i<N; ++i) {
				sum += Integer.parseInt(str[i]);
			}
			System.out.println(sum);
		}
	}
}