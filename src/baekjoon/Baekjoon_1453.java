package baekjoon;

import java.io.*;

// 피시방 알바
public class Baekjoon_1453 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] used = new boolean[101];
		int N = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");
		int cnt = 0;
		for(int i=0; i<N; ++i) {
			int n = Integer.parseInt(str[i]);
			if(!used[n]) used[n]=true;
			else cnt++;
		}
		System.out.println(cnt);
	}
}