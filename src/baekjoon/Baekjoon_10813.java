package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 공 바꾸기
public class Baekjoon_10813 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int[] arr = new int[N+1];
		for(int i=1; i<=N; ++i) {
			arr[i] = i;
		}
		for(int i=0; i<M; ++i) {
			str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			int temp = arr[a];
			arr[a] = arr[b];
			arr[b] = temp;
		}
		for(int i=1; i<=N; ++i) {
			System.out.print(arr[i]+" ");
		}
	}
}
