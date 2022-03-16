package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 성냥개비
/*
 * 그리디하게 생각했을 때 무조건 자릿수가 많으면 큰수이므로 셩냥개비 4개이상일 때는 하나의 수보다는 성냥개비 2개만 있으면 충분한 숫자 1을 여러개로 하는것이 낫다.
 * 그리고 더 이상 자릿수를 늘릴 수 없을때는 그다음 최소한의 갯수인 성냥개비 3개로 만들 수 있는 가장 큰수인 7을 만들어서 제일 높은 자리에 배치하면
 * 가장 큰 수를 만들 수 있다.
 * 반대로 가장 작은 수는 가장 많은 성냥개비를 우선적으로 처리하면서 자릿수가 가장 작게 만든다.
 * 자릿수가 같으면 만들 수 있는 경우를 전부 비교해서 가장 작은 숫자를 선택한다.
 * 시간복잡도: O(N), 최솟값을 찾을 때 각 숫자마다 6번 비교하므로 시간복잡도가 6N 정도 된다.
 */
public class Baekjoon_3687 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		String[] dpMax = new String[101];
		dpMax[2] = "1";
		dpMax[3] = "7";
		for(int i=4; i<101; ++i) {
			dpMax[i] = dpMax[i-2]+"1";
		}
		int[] minNums = {0,0,1,7,4,2,0,8}; // 해당 인덱스갯수의 성냥개비를 가지고 만들 수 있는 최소값 저장
		String[] dpMin = new String[101];
		dpMin[2] = "1";
		dpMin[3] = "7";
		dpMin[4] = "4";
		dpMin[5] = "2";
		dpMin[6] = "6"; // 0
		dpMin[7] = "8";
		dpMin[8] = "10";
		for(int i=9; i<101; ++i) {
			long min = Long.MAX_VALUE;
			for(int j=2; j<=7; ++j) {
				// 최솟값을 찾기
				min = Math.min(min,Math.min(Long.parseLong(dpMin[j]+dpMin[i-j]), Long.parseLong(dpMin[i-j]+minNums[j])));
			}
			dpMin[i] = min+""; // Long을 String으로 변환
		}
		for(int tc=0; tc<T; ++tc) {
			int N = Integer.parseInt(br.readLine());
			sb.append(dpMin[N]).append(" ").append(dpMax[N]).append("\n");
		}
		System.out.println(sb.toString());
	}
}