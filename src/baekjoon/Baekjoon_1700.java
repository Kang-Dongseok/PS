package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 멀티탭 스케줄링
/*
 * 새로 바꿀것이 있으면 기존의 어떤 것과 바꿀것인지 선택하는 로직이 핵심이다.
 * 새로 바꿀것 다음부터 순서대로 체크하여 기존에 사용중인 것이면 바꾸지 않게 체크하다가
 * 기존 사용중인 것 중에 마지막 하나 체크가 안된것을 바꾸는 방법으로 구현하였다.
 * 시간복잡도: O(N^2) 이지만 N이 최대 100이므로 시간고려 안해도 됨.
 */
public class Baekjoon_1700 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int K = Integer.parseInt(line[1]);
		boolean[] used = new boolean[K+1]; // 0번은 미사용
		int usedCnt = 0; // 현재 몇개의 어댑터가 사용중인지
		int result=0;
		line = br.readLine().split(" ");
		for(int i=0; i<K; ++i) {
			int num = Integer.parseInt(line[i]);
			if(used[num]) continue; // 현재 어댑터에 이미 꽂혀있으면 패스
			if(usedCnt<N) { // 아직 어댑터가 빈 구멍이 있으면
				usedCnt++; // 어댑터가 꽂힌 수 1 증가
				used[num]=true; // 사용중으로 표시
			}else { // 어댑터에 빈 구멍이 없으면
				result++; // 플러그 뺴는 횟수 1 증가
				if(N==1) { // 어댑터가 1칸짜리이면
					used[Integer.parseInt(line[i-1])]=false; // 바로 직전의 플러그를 뽑음
					used[num]=true; // 새로운 다음 플러그 꽂기
					continue;
				}
				boolean[] checked = new boolean[K+1]; // 어떤 플러그를 뽑을지 정하기 위해 체크하는 배열
				int checkCnt = 0; // 몇개의 플러그 확인했는지 갯수 체크
				for(int j=i+1; j<K; ++j) {
					int nextNum = Integer.parseInt(line[j]); // 다음에 오는 숫자
					if(!used[nextNum]) continue; // 꽂혀있지 않으면 패스
					else { // 이미 꽂혀있으면
						if(checked[nextNum]) continue; // 안뽑을 플러그 이미 체크했으면 패스
						checked[nextNum]=true; // 안뽑을 플러그 체크
						checkCnt++; // 안뽑을 플러그 확인한 갯수 1 증가
						if(checkCnt==N-1) { // 한개의 플러그 뺴고 나머지가 이미 다 체크 되어있으면
							// 나머지 하나의 플러그와 새로운 플러그를 변경
							for(int idx=1; idx<K+1; ++idx) {
								if(used[idx] && !checked[idx]) { // 현재 꽂혀있지만 체크 안되어있는 플러그 찾아서
									used[idx]=false; // 플러그 뽑기
									break;
								}
							}
							used[num]=true; // 새로운 플로그 꽂기
						}
					}
				}
				if(checkCnt<N-1) { // 교체할 수 있는 곳이 2군데 이상이면
					for(int idx=1; idx<K+1; ++idx) {
						if(used[idx] && !checked[idx]) { // 현재 꽂혀있지만 체크 안되어있는 플러그 아무거나 찾아서(여기서는 사실상 제일 빠른번호)
							used[idx]=false; // 플러그 뽑기
							break;
						}
					}
					used[num]=true; // 새로운 플로그 꽂기
				}
			}
		}
		System.out.println(result);
	}
}