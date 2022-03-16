package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 평균 점수
public class Baekjoon_10039 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sum = 0;
		for(int i=0; i<5; ++i) {
			int num = Integer.parseInt(br.readLine());
			sum += num>40 ? num : 40;
		}
		System.out.println(sum/5);
	}
}
