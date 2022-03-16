package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 검증수
public class Baekjoon_2475 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int sum = 0;
		for(int i=0; i<5; ++i) {
			int num = Integer.parseInt(str[i]);
			sum += num*num;
		}
		System.out.println(sum%10);
	}
}