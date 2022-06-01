package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 대소문자 바꾸기
public class Baekjoon_2744 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringBuilder sb = new StringBuilder();
		int gap = 'a'-'A';
		for(int i=0,len=str.length(); i<len; ++i) {
			char ch = str.charAt(i);
			if(ch<='Z') sb.append((char)(ch+gap));
			else sb.append((char)(ch-gap));
		}
		System.out.println(sb.toString());
	}
}