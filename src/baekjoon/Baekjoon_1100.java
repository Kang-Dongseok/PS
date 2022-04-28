package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 하얀 칸
public class Baekjoon_1100 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = 0;
		for(int i=0; i<8; ++i) {
			char[] charArr = br.readLine().toCharArray();
			for(int j=0; j<8; ++j) {
				if((i+j)%2==0 && charArr[j]=='F') {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}