package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 10부제
public class Baekjoon_10797 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		int cnt = 0;
		String[] str = br.readLine().split(" ");
		for(int i=0; i<5; ++i) {
			if(num==Integer.parseInt(str[i])) cnt++;
		}
		System.out.println(cnt);
	}
}