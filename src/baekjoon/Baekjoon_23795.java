package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 사장님 도박은 재미로 하셔야 합니다
public class Baekjoon_23795 {

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int sum = 0;
    	while(true) {
    		int n = Integer.parseInt(br.readLine());
    		if(n==-1) break;
    		sum += n;
    	}
    	System.out.println(sum);
    }
}