package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 알파벳 개수
public class Baekjoon_10808 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[26];
		String str = br.readLine();
		for(int i=0,len=str.length(); i<len; ++i) {
			arr[str.charAt(i)-'a']++;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<26; ++i) {
			sb.append(arr[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
}