package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 큰 수 A+B
public class Baekjoon_10757 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		String a = str[0];
		String b = str[1];
		StringBuilder sb = new StringBuilder();
		int len1 = a.length();
		int len2 = b.length();
		int idx1 = len1-1;
		int idx2 = len2-1;
		int add = 0;
		while(true) {
			int n1 = 0;
			if(idx1>=0) n1 = a.charAt(idx1--)-'0';
			int n2 = 0;
			if(idx2>=0) n2 = b.charAt(idx2--)-'0';
			int num = n1+n2+add;
			if(num>9) { // 올림
				add = 1;
				num %= 10;
			}else { // 올림 없음
				add = 0;
			}
			sb.append(num);
			if(idx1<0 && idx2<0) break;
		}
		if(add==1) sb.append(1);
        String answer = sb.reverse().toString();
        System.out.println(answer);
	}
}