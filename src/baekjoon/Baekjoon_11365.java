package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// !밀비 급일
public class Baekjoon_11365 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringBuilder sb2 = new StringBuilder();
			String str = br.readLine();
			if(str.equals("END")) {
				break;
			}else {
				sb2.append(str).reverse().append("\n");
				sb.append(sb2.toString());
			}
		}
		System.out.print(sb.toString());
	}
}