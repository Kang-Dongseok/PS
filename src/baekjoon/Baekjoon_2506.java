package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 점수 계산
public class Baekjoon_2506 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int sum = 0;
		int[] arr = new int[N];
		String[] str = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		for(int i=1; i<N; i++) {
			if(arr[i]==0) continue;
			else arr[i] += arr[i-1];
		}
		for(int i=0; i<N; i++) {
			sum += arr[i];
		}
		System.out.println(sum);
	}
}