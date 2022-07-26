package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 자동완성
public class Baekjoon_24883 {

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	char ch = br.readLine().charAt(0);
    	if(ch=='N' || ch=='n') System.out.println("Naver D2");
    	else System.out.println("Naver Whale");
    }
}