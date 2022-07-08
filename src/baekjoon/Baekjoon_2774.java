package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 아름다운 수
public class Baekjoon_2774 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; ++i) {
			String str = br.readLine();
			int len = str.length();
			boolean[] arr = new boolean[10];
			for(int j=0; j<len; ++j) {
				char ch = str.charAt(j);
				arr[ch-'0']=true;
			}
			int cnt = 0;
			for(int j=0; j<10; ++j) {
				if(arr[j]) cnt++;
			}
			sb.append(cnt).append("\n");
		}
		System.out.print(sb.toString());
	}
}