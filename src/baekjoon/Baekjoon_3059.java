package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 등장하지 않는 문자의 합
public class Baekjoon_3059 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; ++t) {
			boolean[] used = new boolean[26];
			String str = br.readLine();
			int len = str.length();
			for(int i=0; i<len; ++i) {
				used[str.charAt(i)-65]=true;
			}
			int sum = 0;
			for(int i=0; i<26; ++i) {
				if(!used[i]) sum+=65+i;
			}
			sb.append(sum).append("\n");
		}
		System.out.println(sb.toString());
	}
}