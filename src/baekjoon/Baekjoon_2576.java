package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 홀수
public class Baekjoon_2576 {

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int sum = 0;
    	int minOdd = 100;
    	for(int i=0; i<7; i++) {
    		int num = Integer.parseInt(br.readLine());
    		if(num%2==1) {
    			sum += num;
    			if(minOdd>num) minOdd=num;
    		}
    	}
    	if(sum!=0) {
    		System.out.println(sum);
    		System.out.println(minOdd);
    	}else {
    		System.out.println(-1);
    	}
    }
}