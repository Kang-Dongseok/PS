package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 주사위 게임
public class Baekjoon_10103 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		int pointA = 100;
		int pointB = 100;
		int n = Integer.parseInt(br.readLine());
		for(int i=0; i<n; ++i) {
			str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			if(a>b) pointB -= a;
			else if(a<b) pointA -= b;
		}
		System.out.println(pointA);
		System.out.println(pointB);
	}
}
