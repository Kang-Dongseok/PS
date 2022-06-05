package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 킹, 퀸, 룩, 비숍, 나이트, 폰
public class Baekjoon_3003 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int a = Integer.parseInt(str[0]);
		int b = Integer.parseInt(str[1]);
		int c = Integer.parseInt(str[2]);
		int d = Integer.parseInt(str[3]);
		int e = Integer.parseInt(str[4]);
		int f = Integer.parseInt(str[5]);
		a = 1-a;
		b = 1-b;
		c = 2-c;
		d = 2-d;
		e = 2-e;
		f = 8-f;
		System.out.println(a+" "+b+" "+c+" "+d+" "+e+" "+f);
	}
}