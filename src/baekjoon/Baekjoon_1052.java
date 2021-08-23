package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// 물병
/*
 * 물병의 총 갯수를 2진수로 표현 ex) 9 => 1001
 * 비트1의 갯수가 최종 합친 물병의 수
 * 제일 오른쪽 비트부터 1이 발견되면 1더해서 비트1의 수를 같거나 작아지게 만들어
 * 최종적으로 비트1을 더한 위치의 10진수 정수값을 다 더하면 새로 산 물병 갯수 k이다.
 * 시간복잡도: O(logN), 2진수로 변환한 비트만큼 검색하기 때문에
 */
public class Baekjoon_1052 {
	
	static int N,K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		K = Integer.parseInt(line[1]);
		int result = 0;
		while(numOf1Bit(N)>K) { // 합친 물병의 수가 K보다 크면
			int bottle = findLast1Bit(N); // 새로 산 물병의 수
			N += bottle;
			result += bottle;
		}
		System.out.println(result);
	}
	
	public static int numOf1Bit(int num) { // 해당 정수의 비트1의 갯수
		int total=0;
		while(num!=0) {
			if((num & 1) == 1) ++total;
			num = num>>1;
		}
		return total;
	}
	public static int findLast1Bit(int num) { // 가장 오른쪽 비트1
		int result=1;
		while((num & 1) != 1) {
			num = num>>1; // 한칸씩 건너뛰고
			result = result<<1; // 한칸씩 왼쪽 전진
		}
		return result;
	}
}
