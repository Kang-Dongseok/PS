package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 최대공약수와 최소공배수
public class Baekjoon_2609 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int a = Integer.parseInt(str[0]);
		int b = Integer.parseInt(str[1]);
		int LCM = Math.min(a, b);
		for(int i=LCM; i>=1; --i) {
			if(a%i==0 && b%i==0) {
				LCM = i;
				break;
			}
		}
		int GCD = a*b/LCM;
		System.out.println(LCM);
		System.out.println(GCD);
	}
}
