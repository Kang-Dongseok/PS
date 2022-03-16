package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 암호코드
/*
 * dp[idx] 는 idx번째 까지의 숫자를 고려한 경우의 수이다.
 * dp[n]은 조건에 따라 dp[n-1] 과 dp[n-2]를 합한 값이다.
 * 기본적으로 dp[n] = dp[n-1]+dp[n-2] 와 비슷한 흐름으로 가지만, 예외인 조건을 잘 찾는것이 이 문제의 핵심인 것 같다.
 */
public class Baekjoon_2011 {
	
	public static void main(String[] args) throws IOException {
		final int MOD = 1000000;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int length = str.length(); // 암호의 길이
		if(length==1) { // 길이가 1이면
			if(str.equals("0")) { // "0"이면 해석불가
				System.out.println(0);
			}else { // 그 외에는 1가지 경우의 수
				System.out.println(1);
			}
			return;
		}
		String endOfStr = str.substring(length-2); // 마지막 2개의 문자
		if(str.startsWith("0") || // "0"으로 시작하거나 
			str.contains("00") || // "00"을 포함하거나
			str.substring(length-1).equals("0") && !(endOfStr.equals("10") || endOfStr.equals("20"))) { // 마지막에 0으로 끝나면서, 10이나 20으로는 끝나지 않을 경우 해석이 불가능하다.
			System.out.println(0);
			return;
		}
		int[] dp = new int[length+1]; // dp[idx]: idx번째 문자까지 해석할 수 있는 경우의 수
		dp[0]=1; dp[1]=1; // 0개와 1개까지의 경우의수는 1로 초기화
		
		for(int i=2; i<=length; ++i) {
			// 마지막 1개의 문자만 고려할 경우
			if(str.charAt(i-1)!='0') { // 새로운 문자가 0이 아니면
				dp[i] += dp[i-1]; // 이전의 경우의수를 그대로 더함
			}
			// 마지막 2개의 문자만 고려할 경우
			if(str.charAt(i-2)!='0' && Integer.valueOf(str.substring(i-2,i))<=26) { // 마지막 숫자2개의 십의자리가 0이 아니고 26이하이면
				dp[i] += dp[i-2];
			}
			dp[i]%=MOD; // 더한 값이 클 수 있으므로 나누어주기
		}
		System.out.println(dp[length]);
	}
}