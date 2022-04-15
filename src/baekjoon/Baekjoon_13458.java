package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 시험 감독
public class Baekjoon_13458 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		String[] str = br.readLine().split(" ");
		for(int i=0; i<N; ++i) {
			A[i] = Integer.parseInt(str[i]);
		}
		str = br.readLine().split(" ");
		int B = Integer.parseInt(str[0]);
		int C = Integer.parseInt(str[1]);
		long answer = N; // 총감독관수
		for(int i=0; i<N; ++i) {
			int num = A[i]-B;
			if(num>0) {
				answer += num/C;
				if(num%C!=0) answer++;
			}
		}
		System.out.println(answer);
	}
}