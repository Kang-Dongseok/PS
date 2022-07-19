package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Identifying tea
public class Baekjoon_11549 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");
		int answer = 0;
		for(int i=0; i<5; ++i) {
			if(T == Integer.parseInt(str[i])) answer++;
		}
		System.out.println(answer);
	}
}