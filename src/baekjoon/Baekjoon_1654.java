package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 랜선 자르기
/*
 * 이분탐색 시 범위조절과 등호 여부를 잘 판단해야할 것 같다.
 */
public class Baekjoon_1654 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int K = Integer.parseInt(str[0]);
		int N = Integer.parseInt(str[1]);
		long[] len = new long[K];
		long maxLen = 0; // 최장 랜선의 길이
		long answer = 0;
		for(int i=0; i<K; ++i) {
			int n = Integer.parseInt(br.readLine());
			len[i] = n;
			if(maxLen<n) maxLen = n;
		}
		long low = 1;
		long high = maxLen;
		while(low<=high) {
			long mid = (low+high)/2;
			int sum = 0;
			for(int i=0; i<K; ++i) {
				sum += len[i]/mid;
			}
			if(sum>=N) {
				answer=mid; // 결과 최댓값 갱신
				low = mid+1; // 길이 증가
			}else {
				high = mid-1; // 길이 감소
			}
		}
		System.out.println(answer);
	}
}