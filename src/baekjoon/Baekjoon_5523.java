package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 경기 결과
public class Baekjoon_5523 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int scoreA = 0;
		int scoreB = 0;
		for(int i=0; i<N; ++i) {
			String[] str = br.readLine().split(" ");
			int A = Integer.parseInt(str[0]);
			int B = Integer.parseInt(str[1]);
			if(A>B) {
				scoreA++;
			}else if(B>A) {
				scoreB++;
			}
		}
		System.out.println(scoreA+" "+scoreB);
	}
}