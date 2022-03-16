package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 신나는 함수 실행
public class Baekjoon_9184 {

	static int[][][] D;
	static boolean[][][] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		D = new int[21][21][21];
		visited = new boolean[21][21][21];
		D[0][0][0] = 1;
		visited[0][0][0] = true;
		while(true) {
			String[] str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			int c = Integer.parseInt(str[2]);
			if(a==-1 && b==-1 && c==-1) break;
			sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ").append(w(a,b,c)).append("\n");
		}
		System.out.println(sb.toString());
	}
	private static int w(int a, int b, int c) {
		if(a<=0 || b<=0 || c<=0) return 1;
		if(a>20 || b>20 || c>20) return w(20,20,20);
		if(visited[a][b][c]) return D[a][b][c];
		else {
			if(a<b && b<c) {
				D[a][b][c] = w(a,b,c-1)+w(a,b-1,c-1)-w(a,b-1,c);
				visited[a][b][c] = true;
				return D[a][b][c];
			}
			else{
				D[a][b][c] = w(a-1,b,c)+w(a-1,b-1,c)+w(a-1,b,c-1)-w(a-1,b-1,c-1);
				visited[a][b][c] = true;
				return D[a][b][c];
			}
		}
	}
}