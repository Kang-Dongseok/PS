package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

// 좌표 정렬하기2
public class Baekjoon_11651 {

	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][];
		String[] str;
		for(int i=0; i<N; ++i) {
			str = br.readLine().split(" ");
			arr[i] = new int[] {Integer.parseInt(str[0]),Integer.parseInt(str[1])};
		}
		Arrays.sort(arr, new Comparator<int[]>() {
			// 1열 오름차순 후 같을 시 0열 오름차순
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]==o2[1]) return o1[0]-o2[0];
				return o1[1]-o2[1];
			}
		});
		for(int[] ar : arr) {
			sb.append(ar[0]).append(" ").append(ar[1]).append("\n");
		}
		System.out.print(sb.toString());
	}
}
