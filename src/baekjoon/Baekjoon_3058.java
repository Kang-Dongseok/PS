package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 짝수를 찾아라
public class Baekjoon_3058 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; ++t) {
			int sum = 0;
			int evenNum = 100;
			String[] str = br.readLine().split(" ");
			for(int i=0; i<7; ++i) {
				int num = Integer.parseInt(str[i]);
				if(num%2==0) {
					sum += num;
					if(evenNum>num) {
						evenNum = num;
					}
				}
			}
			sb.append(sum).append(" ").append(evenNum).append("\n");
		}
		System.out.println(sb.toString());
	}
}