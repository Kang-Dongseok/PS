package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 단어 뒤집기
public class Baekjoon_9093 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sbTotal = new StringBuilder();
		for(int t=0; t<T; ++t) {
			String[] str = br.readLine().split(" ");
			for(int i=0,len=str.length; i<len; ++i) {
				StringBuilder sb = new StringBuilder();
				sbTotal.append(sb.append(str[i]).reverse().append(" "));
			}
			sbTotal.append("\n");
		}
		System.out.println(sbTotal);
	}
}