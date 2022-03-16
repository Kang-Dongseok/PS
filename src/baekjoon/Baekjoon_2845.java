package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 파티가 끝나고 난 뒤
public class Baekjoon_2845 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str = br.readLine().split(" ");
		int L = Integer.parseInt(str[0]);
		int P = Integer.parseInt(str[1]);
		int num = L*P;
		str = br.readLine().split(" ");
		for(int i=0; i<5; ++i) {
			sb.append(Integer.parseInt(str[i])-num).append(" ");
		}
		System.out.println(sb.toString());
		
	}
}