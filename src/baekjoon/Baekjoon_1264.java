package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 모음의 개수
public class Baekjoon_1264 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			String str = br.readLine().toLowerCase();
			if(str.equals("#")) break;
			int len = str.length();
			int cnt = 0;
			for(int i=0; i<len; ++i) {
				char ch = str.charAt(i);
				if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u') cnt++;
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}
}