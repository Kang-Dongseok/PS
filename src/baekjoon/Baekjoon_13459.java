package baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 구슬 탈출
public class Baekjoon_13459 {

	static int N,M;
	static int[] posR=new int[2],posB=new int[2]; // R과B의 초기 좌표
	static char[][] map;
	static boolean answer;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 상,우,하,좌
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new char[N][M];
		for(int i=0; i<N; ++i) {
			map[i] = br.readLine().toCharArray();
		}
		for(int i=0; i<N; ++i) {
			
			for(int j=0; j<M; ++j) {
				if(map[i][j]=='R') {
					posR[0]=i;
					posR[1]=j;
					map[i][j] = '.'; // R의 좌표만 알아내고 빈칸으로 바꾸기
				}else if(map[i][j]=='B') {
					posB[0]=i;
					posB[1]=j;
					map[i][j] = '.'; // B의 좌표만 알아내고 빈칸으로 바꾸기
				}
			}
		}
		for(char[] ar : map) {
			for(char c : ar) {
				System.out.print(c);
			}
			System.out.println();
		}
		dfs(0,posR,posB);
	}
	
	public static void dfs(int n, int[] posR, int[] posB) {
		if(n==10) return; // 10회 다 이동하면 그만
		
		for()
	}
	public static int[] func(int[] posR, int[] posB, int d) {
		if(d==0) { // 북
			
		}
		
	}
	
}