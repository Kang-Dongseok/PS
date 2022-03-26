package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// Z
public class Baekjoon_1074 {
	
	static int R,C,answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		R = Integer.parseInt(line[1]);
		C = Integer.parseInt(line[2]);
		
		func(N,0,0);
		System.out.println(answer);
	}
	
	public static void func(int n, int posR, int posC) {
		if(n==0) return;
		int nr=posR,nc=posC;
		if(R>=posR+Math.pow(2, n-1)) { // 아래
			nr=posR+(int)Math.pow(2, n-1);
			answer += Math.pow(2, 2*n-1);
		}
		if(C>=posC+Math.pow(2, n-1)) { // 오
			nc=posC+(int)Math.pow(2, n-1);
			answer += Math.pow(2, 2*n-2);
		}
		func(n-1, nr, nc);
	}
}