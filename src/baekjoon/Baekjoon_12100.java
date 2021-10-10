package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

// 2048(Easy)
public class Baekjoon_12100 {
	
	static int N,result;
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<N; ++j) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
//		dfs(0,map);
		func(map, 3);
		for(int[] ar : map) {
			for(int a : ar) {
				System.out.print(a+" ");
			}
			System.out.println();
		}
		
	}
	public static void dfs(int n, int[][] map) {
		if(n==5) { // 5번 움직였으면 종료
			findMaxNum(map); // 최댓값 찾아서 갱신
		}
		
		int[][] copiedArr;
		for(int i=0; i<4; ++i) {
			copiedArr = copyArr(map);
			func(copiedArr,i);
			dfs(n+1,copiedArr);
		}
	}
	public static void func(int[][] map, int dir) { // dir: 0(상), 1(우), 2(하), 3(좌)
		Deque<Integer> deque = new ArrayDeque<Integer>();
		if(dir==0) { // 상
			
		}else if(dir==1) { // 우
			
		}else if(dir==2) { // 하
			
		}else { // 좌
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					int num = map[i][j];
					if(num!=0) { // 숫자가 0이 아니면
						if(!deque.isEmpty() && deque.peekLast()==num) { // 덱이 비어있지 않고, 직전에 들어간 숫자와 같으면
							deque.offer(deque.pollLast()+num); // 두 수를 합쳐서 다시 넣기
						}else {
							deque.offer(num);
						}
					}
				}
				int size = deque.size();
				for(int j=0; j<size; ++j) {
					map[i][j] = deque.poll(); // 숫자 배치하기
				}
				for(int j=size; j<N; ++j) {
					map[i][j] = 0; // 남은 칸은 다 0으로 바꾸기
				}
			}
		}
	}
	public static void findMaxNum(int[][] arr) { // 최댓값 찾아서 result에 갱신
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(result<arr[i][j]) result=arr[i][j]; // 최댓값 갱신
			}
		}
	}
	public static int[][] copyArr(int[][] arr) {
		int[][] copiedArr = new int[N][N];
		for(int i=0; i<N; ++i) {
			copiedArr[i] = arr[i].clone();
		}
		return copiedArr;
	}
}
