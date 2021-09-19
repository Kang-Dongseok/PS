package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 이항 계수1
public class Baekjoon_11050 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int K = Integer.parseInt(str[1]);
		int num=1;
		for(int i=0; i<K; ++i) {
			num *= N-i;
		}
		for(int i=K; i>0; --i) {
			num /= i;
		}
		System.out.println(num);
	}
}
