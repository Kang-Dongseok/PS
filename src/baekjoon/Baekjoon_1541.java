package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * "-" 문자열을 기준으로 StringTokenizer를 이용하여 자른 후
 * 제일 첫 token만 양수이고
 * 나머지 token에서는 양수를 다 더하여 전체 합에서 빼면 최솟값이 나온다.
 * 시간복잡도 : N (숫자 갯수에 비례하니까...?)
 */
public class Baekjoon_1541 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str,"-");
		int result = 0;
		String subStr = st.nextToken();
		result += func(subStr);
		while(st.hasMoreTokens()) {
			subStr = st.nextToken();
			result -= func(subStr);
		}
		System.out.println(result);
	}
	public static int func(String str) {
		int sum = 0;
		StringTokenizer st = new StringTokenizer(str,"+");
		while(st.hasMoreTokens()) {
			sum += Integer.parseInt(st.nextToken());
		}
		return sum;
	}

}
