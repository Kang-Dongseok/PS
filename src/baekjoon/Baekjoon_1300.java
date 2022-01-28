package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// K번째 수
/*
 * 이번 이분탐색 문제도 도무지 풀이법이 떠오르지 않아서 구글링을 하여 참고하였다.
 * B[k]=x 라는 뜻은 'x보다 작거나 같은 수가 최소 k개가 존재한다' 라는 의미로 해석하여 접근하였다.
 * 시간복잡도: O(NlogN)
 */
public class Baekjoon_1300 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		int low = 1; // 하한선
		int high = K; // 상한선
		
		while(low<high) { // lower-bound를 사용
			int mid = (low+high)/2;
			int count = 0; // mid값보다 작거나 같은 수들의 갯수
			for(int i=1; i<=N; ++i) { // 각 행마다
				count += Math.min(mid/i,N); // mid보다 작거나 같은 수들의 갯수, 행마다 최대 N개를 넘을 수 없다.
			}
			if(count>=K) { // mid보다 작거나 같은 숫자들의 갯수가 K개 이상이면
				high=mid; // 상한선이 답이 될지도 모르므로 mid로 조정
			}else { // mid보다 작거나 같은 숫자들의 갯수가 K개 보다 작으면
				low=mid+1; // 하한선이 답이 될 수는 없으므로 mid+1로 조정
			}
		}
		System.out.println(low);
	}
}