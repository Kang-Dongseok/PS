package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

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
		dfs(0,map);
		System.out.println(result);
	}
	public static void dfs(int n, int[][] map) {
		if(n==5) { // 5번 움직였으면 종료
			findMaxNum(map); // 최댓값 찾아서 갱신
			return;
		}
		
		int[][] copiedArr;
		for(int i=0; i<4; ++i) {
			copiedArr = copyArr(map);
			func(copiedArr,i);
			dfs(n+1,copiedArr);
		}
	}
	public static void func(int[][] map, int dir) { // dir: 0(상), 1(우), 2(하), 3(좌)
		Queue<Integer> checkQ = new LinkedList<Integer>(); // 두 수를 비교하는 큐
		Queue<Integer> resultQ = new LinkedList<Integer>(); // 한 번의 이동으로 숫자들이 합쳐진 결과를 담는 큐
		
		if(dir==0) { // 상
			for(int c=0; c<N; ++c) {
				for(int r=0; r<N; ++r) {
					int num = map[r][c];
					if(num!=0) { // 숫자가 0이 아니면
						if(checkQ.isEmpty()) { // 비어있으면 다음숫자 받기
							checkQ.offer(num);
						}else { // 비어있지 않으면
							if(checkQ.peek()==num) { // 다음 숫자와 이전 숫자가 같으면
								resultQ.offer(checkQ.poll()*2); // 두 수를 합쳐서 결과큐에 넣기
							}else {
								resultQ.offer(checkQ.poll()); // 이전 수를 결과큐에 넣고
								checkQ.offer(num); // 다음 수를 체크큐에 넣기
							}
						}
					}
				}
				if(!checkQ.isEmpty()) resultQ.offer(checkQ.poll()); // 마지막에 숫자가 하나 남아있으면 결과큐에 그대로 넣기
				int size = resultQ.size();
				for(int r=0; r<size; ++r) {
					map[r][c] = resultQ.poll(); // 숫자 배치하기
				}
				for(int r=size; r<N; ++r) {
					map[r][c] = 0; // 남은 칸은 다 0으로 바꾸기
				}
			}
		}else if(dir==1) { // 우
			for(int r=0; r<N; ++r) {
				for(int c=N-1; c>=0; --c) {
					int num = map[r][c];
					if(num!=0) { // 숫자가 0이 아니면
						if(checkQ.isEmpty()) { // 비어있으면 다음숫자 받기
							checkQ.offer(num);
						}else { // 비어있지 않으면
							if(checkQ.peek()==num) { // 다음 숫자와 이전 숫자가 같으면
								resultQ.offer(checkQ.poll()*2); // 두 수를 합쳐서 결과큐에 넣기
							}else {
								resultQ.offer(checkQ.poll()); // 이전 수를 결과큐에 넣고
								checkQ.offer(num); // 다음 수를 체크큐에 넣기
							}
						}
					}
				}
				if(!checkQ.isEmpty()) resultQ.offer(checkQ.poll()); // 마지막에 숫자가 하나 남아있으면 결과큐에 그대로 넣기
				int size = resultQ.size();
				for(int c=N-1; c>=N-size; --c) {
					map[r][c] = resultQ.poll(); // 숫자 배치하기
				}
				for(int c=N-size-1; c>=0; --c) {
					map[r][c] = 0; // 남은 칸은 다 0으로 바꾸기
				}
			}
		}else if(dir==2) { // 하
			for(int c=0; c<N; ++c) {
				for(int r=N-1; r>=0; --r) {
					int num = map[r][c];
					if(num!=0) { // 숫자가 0이 아니면
						if(checkQ.isEmpty()) { // 비어있으면 다음숫자 받기
							checkQ.offer(num);
						}else { // 비어있지 않으면
							if(checkQ.peek()==num) { // 다음 숫자와 이전 숫자가 같으면
								resultQ.offer(checkQ.poll()*2); // 두 수를 합쳐서 결과큐에 넣기
							}else {
								resultQ.offer(checkQ.poll()); // 이전 수를 결과큐에 넣고
								checkQ.offer(num); // 다음 수를 체크큐에 넣기
							}
						}
					}
				}
				if(!checkQ.isEmpty()) resultQ.offer(checkQ.poll()); // 마지막에 숫자가 하나 남아있으면 결과큐에 그대로 넣기
				int size = resultQ.size();
				for(int r=N-1; r>=N-size; --r) {
					map[r][c] = resultQ.poll(); // 숫자 배치하기
				}
				for(int r=N-size-1; r>=0; --r) {
					map[r][c] = 0; // 남은 칸은 다 0으로 바꾸기
				}
			}
		}else { // 좌
			for(int r=0; r<N; ++r) {
				for(int c=0; c<N; ++c) {
					int num = map[r][c];
					if(num!=0) { // 숫자가 0이 아니면
						if(checkQ.isEmpty()) { // 비어있으면 다음숫자 받기
							checkQ.offer(num);
						}else { // 비어있지 않으면
							if(checkQ.peek()==num) { // 다음 숫자와 이전 숫자가 같으면
								resultQ.offer(checkQ.poll()*2); // 두 수를 합쳐서 결과큐에 넣기
							}else {
								resultQ.offer(checkQ.poll()); // 이전 수를 결과큐에 넣고
								checkQ.offer(num); // 다음 수를 체크큐에 넣기
							}
						}
					}
				}
				if(!checkQ.isEmpty()) resultQ.offer(checkQ.poll()); // 마지막에 숫자가 하나 남아있으면 결과큐에 그대로 넣기
				int size = resultQ.size();
				for(int c=0; c<size; ++c) {
					map[r][c] = resultQ.poll(); // 숫자 배치하기
				}
				for(int c=size; c<N; ++c) {
					map[r][c] = 0; // 남은 칸은 다 0으로 바꾸기
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