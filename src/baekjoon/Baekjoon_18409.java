package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Counting Vowels
public class Baekjoon_18409 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int len = Integer.parseInt(br.readLine());
		String str = br.readLine();
		int cnt = 0;
		for(int i=0; i<len; ++i) {
			char ch = str.charAt(i);
			if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u') cnt++;
		}
		System.out.println(cnt);
	}	
}