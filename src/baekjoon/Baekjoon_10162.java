package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 전자레인지
public class Baekjoon_10162 {

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine());
    	int A = T/300;
    	T = T%300;
    	int B = T/60;
    	T = T%60;
    	int C = T/10;
    	T = T%10;
    	if(T==0) {
    		System.out.println(A+" "+B+" "+C);
    	}else {
    		System.out.println(-1);
    	}
    }
}