package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Equality
public class Baekjoon_13985 {

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] str = br.readLine().split(" ");
    	int a = Integer.parseInt(str[0]);
    	int b = Integer.parseInt(str[2]);
    	int c = Integer.parseInt(str[4]);
    	if(a+b==c) System.out.println("YES");
    	else System.out.println("NO");
    }
}