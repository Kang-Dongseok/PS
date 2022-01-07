package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 공유기 설치
/*
 * 공유기 마다 무조건 거리를 두어야 하는 최소거리를 이분탐색으로 변경해가며
 * 공유기가 모두 설치 가능한지 여부를 판단하는 것이 이 문제의 핵심인 것 같다.
 * 이분탐색은 아직 익숙치 않아서 그런지 감이 잘 안잡혀서 구글링을 참고하였다.
 * 시간복잡도: O(NlogN) N크기의 배열을 이분 탐색을 통해 logN 횟수만큼 반복문을 순회한다. 
 */
public class Baekjoon_2110 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int C = Integer.parseInt(line[1]);
		int[] arr = new int[N];
		for(int i=0; i<N; ++i) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		int low = 1; // 이분탐색 왼쪽 경계
		int high=arr[N-1]-arr[0]; // 이분탐색 오른쪽 경계
		int result = 0;
		while(low<=high) { // 이분탐색 시작
			int mid = (low+high)/2; // 최소거리를 이분탐색하여 찾기
			int prev = arr[0]; // 바로 직전의 공유기 위치, 제일 첫 집은 공유기 무조건 설치
			int count = 1; // 첫 집은 무조건 설치
			for(int i=1; i<N; ++i) {
				if(arr[i]-prev>=mid) { // i번째 집과 직전 공유기와의 거리가 최소거리 이상 이면
					prev=arr[i];
					count++; // 공유기 설치
				}
			}
			if(count>=C) { // C개의 공유기를 충분히 다 설치할 수 있다면
				low = mid+1; // 왼쪽경계 변경
				result = mid; // 답 갱신
			}else { // C개의 공유기를 다 설치하지 못한다면
				high = mid-1; // 오른쪽경계 변경
			}
		}
		System.out.println(result);
	}
}