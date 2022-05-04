package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 학점계산
public class Baekjoon_2754 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		double score = 0.0;
		if(str.charAt(0)=='F') {
			System.out.println(0.0);
		}else {
			score += 4-(str.charAt(0)-'A');
			if(str.charAt(1)=='+') {
				score += 0.3;
			}else if(str.charAt(1)=='-') {
				score -= 0.3;
			}
			System.out.println(score);
		}
	}
}