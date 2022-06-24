package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 가장 긴 바이토닉 부분 수열
public class Baekjoon_11054 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] increase = new int[N];
		int[] decrease = new int[N];
		String[] str = br.readLine().split(" ");
		for(int i=0; i<N; ++i) {
			arr[i] = Integer.parseInt(str[i]);
		}
		
		increase[0] = 1;
		for(int i=1; i<N; ++i) {
			increase[i] = 1;
			for(int j=0; j<i; ++j) {
				if(arr[i]>arr[j]) increase[i] = Math.max(increase[i], increase[j]+1);
			}
		}
		decrease[N-1] = 1;
		for(int i=N-2; i>=0; --i) {
			decrease[i] = 1;
			for(int j=N-1; j>i; --j) {
				if(arr[i]>arr[j]) decrease[i] = Math.max(decrease[i], decrease[j]+1);
			}
		}
		
		int max = 0;
		for(int i=0; i<N; ++i) {
			int n = increase[i]+decrease[i];
			if(max<n) max=n;
		}
		System.out.println(max-1);
	}
}