package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 지능형 기차 2
public class Baekjoon_2460 {

    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int cnt = 0;
    	int max = 0;
    	for(int i=0; i<10; ++i) {
    		String[] str = br.readLine().split(" ");
    		int out = Integer.parseInt(str[0]);
    		int in = Integer.parseInt(str[1]);
    		cnt += in-out;
    		max = Math.max(max, cnt);
    	}
    	System.out.println(max);
    }
}