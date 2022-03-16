package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

// 좌표 정렬하기
public class Baekjoon_11650 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		String[] str;
		for(int i=0; i<N; ++i) {
			str = br.readLine().split(" ");
			arr[i][0] = Integer.parseInt(str[0]);
			arr[i][1] = Integer.parseInt(str[1]);
		}
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0]==o2[0]) return Integer.compare(o1[1], o2[1]);
				else return Integer.compare(o1[0], o2[0]);
			}
		});
		for(int i=0; i<N; ++i) {
			System.out.println(arr[i][0]+" "+arr[i][1]);
		}
	}
}