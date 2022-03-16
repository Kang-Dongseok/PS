package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 헬멧과 조끼
public class Baekjoon_15781 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int helmetMax = 0;
		int vestMax = 0;
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		str = br.readLine().split(" ");
		for(int i=0; i<N; ++i) {
			int helmet = Integer.parseInt(str[i]);
			if(helmetMax<helmet) helmetMax=helmet;
		}
		str = br.readLine().split(" ");
		for(int i=0; i<M; ++i) {
			int vest = Integer.parseInt(str[i]);
			if(vestMax<vest) vestMax=vest;
		}
		System.out.println(helmetMax+vestMax);
	}
}