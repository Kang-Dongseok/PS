package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// MVP 다이아몬드 (Easy)
public class Baekjoon_20413 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");
		int s = Integer.parseInt(str[0]);
		int g = Integer.parseInt(str[1]);
		int p = Integer.parseInt(str[2]);
		int d = Integer.parseInt(str[3]);
		int last = 0;
		int answer = 0;
		String grade = br.readLine();
		for(int i=0; i<N; ++i) {
			char ch = grade.charAt(i);
			if(ch=='B') {
				last = s-1-last;
			}else if(ch=='S') {
				last = g-1-last;
			}else if(ch=='G') {
				last = p-1-last;
			}else if(ch=='P') {
				last = d-1-last;
			}else if(ch=='D') {
				last = d;
			}
			answer += last;
		}
		System.out.println(answer);
	}
}