package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 럭비 클럽
public class Baekjoon_2083 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			String[] str = br.readLine().split(" ");
			if(str[0].equals("#")) break;
			String name = str[0];
			int age = Integer.parseInt(str[1]);
			int weight = Integer.parseInt(str[2]);
			sb.append(name).append(" ");
			if(age>17 || weight>=80) {
				sb.append("Senior").append("\n");
			}else {
				sb.append("Junior").append("\n");
			}
		}
		System.out.print(sb.toString());
	}
}
