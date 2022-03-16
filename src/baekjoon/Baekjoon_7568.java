package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 덩치
public class Baekjoon_7568 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] info = new int[N][2];
		String[] str;
		for(int i=0; i<N; ++i) {
			str = br.readLine().split(" ");
			info[i][0] = Integer.parseInt(str[0]);
			info[i][1] = Integer.parseInt(str[1]);
		}
		int[] answer = new int[N];
		for(int i=0; i<N; ++i) {
			int weight = info[i][0];
			int height = info[i][1];
			int cnt = 0;
			for(int j=0; j<N; ++j) {
				if(weight<info[j][0] && height<info[j][1]) cnt++;
			}
			answer[i] = cnt+1;
		}
		for(int i=0; i<N; ++i) {
			System.out.print(answer[i]+" ");
		}
	}
}