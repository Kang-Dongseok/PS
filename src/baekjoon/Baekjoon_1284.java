package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 집 주소
public class Baekjoon_1284 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			String str = br.readLine();
			if(str.equals("0")) break;
			int sum = 0;
			int len = str.length();
			for(int i=0; i<len; ++i) {
				char ch = str.charAt(i);
				if(ch=='1') sum+=2;
				else if(ch=='0') sum+=4;
				else sum+=3;
			}
			sum += len+1;
			sb.append(sum).append("\n");
		}
		System.out.println(sb.toString());
	}
}
