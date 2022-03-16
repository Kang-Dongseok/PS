package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 용액
/*
 * 양수만 있거나 음수만 있는 경우는 답을 쉽게 찾을 수 있다.
 * 양수와 음수 모두 함께 있는 경우, 3가지로 나뉜다.
 * 1. 0에 가까운 양수 2개
 * 2. 0에 가까운 음수 2개
 * 3. 양수 1개와 음수 1개
 * 우선 1번과 2번은 쉽게 비교할 수 있다.
 * 3번의 로직에서 0에 가장 가까운 양수와 음수를 하나씩 선택한 후 다음 숫자끼리 비교하여 절댓값이 작은 쪽이 한 칸 이동하며 양쪽 다 끝까지 비교한다.
 * 처음에 당연히 양수 하나와 음수 하나를 선택해야 할 것이라는 논리적 오류를 범해서 시간이 많이 날라갔다. 왜 그랬을까...?
 * 시간복잡도: O(N) 투 포인터를 이용하여 한 번의 계산마다 하나의 새로운 숫자를 확인한다.
 */
public class Baekjoon_2467 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] answer = new int[2]; // 정답 두 숫자를 저장
		String[] str = br.readLine().split(" ");
		for(int i=0; i<N; ++i) {
			arr[i] = Integer.parseInt(str[i]);
		}
		if(arr[N-1]<0) { // 음수만 있으면
			answer[0] = arr[N-2]; // 마지막 두 수를 저장
			answer[1] = arr[N-1];
		}else if(arr[0]>0) { // 양수만 있으면
			answer[0] = arr[0]; // 처음 두 수를 저장
			answer[1] = arr[1];
		}else { // 음수와 양수가 같이 있으면
			int negativeIdx = -1; // 음수를 가리킬 인덱스
			int positiveIdx = -1; // 양수를 가리킬 인덱스
			
			for(int i=0; i<N-1; ++i) {
				if(arr[i]<0 && arr[i+1]>0) { // 연속한 두 수가 음수와 양수이면
					negativeIdx=i; positiveIdx=i+1; // 음수와 양수의 인덱스를 저장
					break;
				}
			}
			
			// 최대 음수 2개만 선택
			if(negativeIdx>0) {
				answer[0] = arr[negativeIdx-1];
				answer[1] = arr[negativeIdx];
			}
			// 최소 양수 2개만 선택
			if(positiveIdx<N-1) {
				if(answer[0]==0 || Math.abs(answer[0]+answer[1])>arr[positiveIdx]+arr[positiveIdx+1]) { // 음수가 1개밖에 없거나 양수 2개 합의 절댓값이 더 작을 경우
					answer[0] = arr[positiveIdx];
					answer[1] = arr[positiveIdx+1];
				}
			}
			// 각각 1개씩 선택
			if(answer[0]==0 || Math.abs(answer[0]+answer[1])>arr[negativeIdx]+arr[positiveIdx]) { // 음수와 양수가 1개씩 밖에 없거나 양수와 음수 하나씩 합의 절댓값이 더 작을 경우
				answer[0] = arr[negativeIdx];
				answer[1] = arr[positiveIdx];
			}
			while(true) {
				if(negativeIdx==0 && positiveIdx==N-1) break; // 양쪽 포인터가 모두 끝을 가리키면 종료
				if(negativeIdx==0) { // 더 이상의 음수가 없으면
					positiveIdx++; // 양수를 한 칸 이동
				}else if(positiveIdx==N-1) { // 더 이상의 양수가 없으면
					negativeIdx--; // 음수를 한 칸 이동
				}else { // 둘 다 아직 숫자들이 남아 있으면
					int nextN = negativeIdx-1; // 다음 음수
					int nextP = positiveIdx+1; // 다음 양수
					if(Math.abs(arr[nextN])<Math.abs(arr[nextP])) { // 각각 다음 수를 비교 후, 절댓값이 작은 쪽이 포인터를 이동
						negativeIdx--;
					}else {
						positiveIdx++;
					}
				}
				if(Math.abs(arr[negativeIdx]+arr[positiveIdx])<Math.abs(answer[0]+answer[1])) { // 기존의 두 수의 합보다 새로운 두 수의 합이 0에 더 가까우면
					answer[0] = arr[negativeIdx];
					answer[1] = arr[positiveIdx];
				}
			}
		}
		System.out.println(answer[0]+" "+answer[1]);
	}
}