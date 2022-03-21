package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// 세 용액
/*
 * 정렬 후 하나의 숫자를 처음부터 끝까지 하나씩 고정시켜가면서
 * 나머지 2개의 숫자를 처음과 끝으로 설정하여 이분탐색하는 방법으로 모든 경우의 수를 확인한다.
 * 시간복잡도: O(N^2) N+(N-1)+(N-2)+...+1이다.
 * 참고로 정렬(sort)의 시간복잡도를 보니
 * Arrays.sort()는 평균 O(NlogN)이고, 최악 O(N^2)이다.
 * Collections.sort()는 평균과 최악 모두 O(NlogN)이다.
 */
public class Baekjoon_2473 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Long> list = new ArrayList<Long>();
		String[] line = br.readLine().split(" ");
		for(int i=0; i<N; ++i) {
			list.add(Long.parseLong(line[i]));
		}
		Collections.sort(list); // ArrayList로 정렬 후 배열로 전환
		long[] arr = new long[N];
		for(int i=0; i<N; ++i) {
			arr[i] = list.get(i);
		}
		long num1=0,num2=0,num3=0;
		long min = Long.MAX_VALUE; // 최솟값 초기화
		for(int i=0; i<N-2; ++i) {
			int j=i+1;
			int k=N-1;
			while(j<k) {
				long sum = arr[i]+arr[j]+arr[k];
				if(min>Math.abs(sum)) { // 최솟값 발견하면
					min=Math.abs(sum); // 최솟값 갱신
					num1=arr[i]; // 숫자 저장
					num2=arr[j];
					num3=arr[k];
				}
				if(sum<0) { // 합이 음수이면 숫자의 합이 커져야 하므로
					j++;
				}else { // 합이 양수이면 숫자의 합이 작아져야 하므로
					k--;
				}
			}
		}
		System.out.println(num1+" "+num2+" "+num3+" ");
	}
}