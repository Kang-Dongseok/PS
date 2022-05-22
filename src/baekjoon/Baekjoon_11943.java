package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 파일 옮기기
public class Baekjoon_11943 {

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] str = br.readLine().split(" ");
    	int A = Integer.parseInt(str[0]);
    	int B = Integer.parseInt(str[1]);
    	str = br.readLine().split(" ");
    	int C = Integer.parseInt(str[0]);
    	int D = Integer.parseInt(str[1]);
    	System.out.println(Math.min(A+D, B+C));
    }
}