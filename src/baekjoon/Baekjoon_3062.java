package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 수 뒤집기
public class Baekjoon_3062 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; ++t) {
			StringBuilder tmp = new StringBuilder();
			int n1 = Integer.parseInt(tmp.append(br.readLine()).toString());
			int n2 = Integer.parseInt(tmp.reverse().toString());
			int num = n1+n2;
			StringBuilder tmp2 = new StringBuilder();
			String s1 = tmp2.append(num).toString();
			String s2 = tmp2.reverse().toString();
			if(s1.equals(s2)) {
				sb.append("YES").append("\n");
			}else {
				sb.append("NO").append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	
}