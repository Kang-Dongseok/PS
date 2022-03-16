package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 히스토그램
public class Baekjoon_13752 {

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	StringBuilder sb;
    	for(int i=0; i<N; ++i) {
    		int k = Integer.parseInt(br.readLine());
    		sb = new StringBuilder();
    		for(int j=0; j<k; ++j) {
    			sb.append("=");
    		}
    		System.out.println(sb.toString());
    	}
    }
}