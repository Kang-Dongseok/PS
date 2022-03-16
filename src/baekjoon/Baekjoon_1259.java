package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 팰린드롬수
public class Baekjoon_1259 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		while(true) {
			String str = br.readLine();
			if(str.equals("0")) break;
			if(str.equals(temp.append(str).reverse().toString())) {
				sb.append("yes\n");
			}else{
				sb.append("no\n");
			}
			temp.setLength(0);
		}
		System.out.println(sb.toString());
	}
}
