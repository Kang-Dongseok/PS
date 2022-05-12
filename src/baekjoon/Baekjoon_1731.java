package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 추론
public class Baekjoon_1731 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; ++i) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int Q = arr[1]-arr[0];
		if(Q==arr[2]-arr[1]) { // 등차
			System.out.println(arr[N-1]+Q);
		}else { // 등비
			System.out.println(arr[N-1]*(arr[N-1]/arr[N-2]));
		}
	}
}