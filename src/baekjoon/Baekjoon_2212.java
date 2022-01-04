package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 센서
/*
 * 센서들을 오름차순 정렬한 후, 센서들 사이의 거리를 저장하는 배열을 만든다.
 * 그리고 집중국이 처음에 다 연결되어있다고 가정하고,
 * K-1개만큼의 연결을 끊어야 K개만큼 집중국의 갯수가 나오므로,
 * 센서들 사이의 거리를 저장한 배열을 오름차순 정렬 후,
 * 뒤에서부터 길이가 가장 긴 부분을 K-1개 만큼 제외한 나머지 길이의 합을 구한다.
 */
public class Baekjoon_2212 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		String[] line = br.readLine().split(" ");
		for(int i=0; i<N; ++i) {
			arr[i] = Integer.parseInt(line[i]);
		}
		Arrays.sort(arr); // 오름차순 정렬
		int[] gap = new int[N-1]; // arr의 각 요소들 사이의 차이
		for(int i=0; i<N-1; ++i) {
			gap[i] = arr[i+1]-arr[i];
		}
		Arrays.sort(gap); // 오름차순 정렬
		int result = 0;
		for(int i=0; i<N-K; ++i) {
			result+=gap[i]; // N-1개 중에 K-1개만큼의 연결을 끊어야 K개만큼 집중국의 갯수가 나오므로, 가장 큰 차이가 나는 것 부터 K-1개 빼고 나머지만 더한다.
		}
		System.out.println(result);
	}
}