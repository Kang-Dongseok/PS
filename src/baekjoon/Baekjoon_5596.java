package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// 시험 점수
public class Baekjoon_5596 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int A=0,B=0;		
		for(int i=0; i<4; ++i) {
			A += Integer.parseInt(str[i]);
		}
		str = br.readLine().split(" ");
		for(int i=0; i<4; ++i) {
			B += Integer.parseInt(str[i]);
		}
		System.out.println(Math.max(A,B));
	}
}
