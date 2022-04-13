package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 드래곤 커브
public class Baekjoon_15685 {

	static int[][] dir = {{1,0},{0,-1},{-1,0},{0,1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> direction = new ArrayList<Integer>();
		direction.add(0);
		for(int cnt=0; cnt<10; ++cnt) {
			for(int i=direction.size()-1; i>=0; --i) {
				direction.add((direction.get(i)+1)%4);
			}
		}
		boolean[][] check = new boolean[101][101];
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[101][101];
		for(int i=0; i<N; ++i) {
			String[] line = br.readLine().split(" ");
			for(int j=0; j<N; ++j) {
				int x = Integer.parseInt(line[0]);
				int y = Integer.parseInt(line[1]);
				int d = Integer.parseInt(line[2]);
				int g = Integer.parseInt(line[3]);
				check[x][y]=true;
				for(int n=0,end=(int)Math.pow(2,g); n<end; ++n) {
					int nextD = (direction.get(n)+d)%4;
					x += dir[nextD][0];
					y += dir[nextD][1];
					check[x][y]=true;
				}
			}
		}
		int answer = 0;
		for(int i=0; i<100; ++i) {
			for(int j=0; j<100; ++j) {
				if(check[i][j]&&check[i+1][j]&&check[i][j+1]&&check[i+1][j+1]) answer++;
			}
		}
		System.out.println(answer);
//		for(boolean[] ar : check) {
//			for(boolean a : ar) {
//				System.out.print(a+"\t");
//			}
//			System.out.println();
//		}
	}
}