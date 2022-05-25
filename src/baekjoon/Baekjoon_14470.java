package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 전자레인지
public class Baekjoon_14470 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		int D = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		int sum = 0;
		if(A<0) {
			sum += A*(-1)*C;
			sum += D;
			sum += E*B;
		}else if(A==0) {
			sum += D;
			sum += E*B;
		}else {
			sum += E*(B-A);
		}
		System.out.println(sum);
	}
}