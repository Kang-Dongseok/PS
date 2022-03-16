package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 뱀
/*
 * 빈 공간 => 0
 * 뱀 몸통, 벽 => 1
 * 사과 => 2
 * 양방향에서 추가/삭제 하므로 Deque 사용
 * 시간 복잡도 : N (1초당 연산하는 갯수가 상수만큼 이므로..?)
 */
public class Baekjoon_3190 {

	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};  // 우,하,좌,상
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N+2][N+2];
		Arrays.fill(arr[0], 1);
		Arrays.fill(arr[N+1], 1);
		for(int i=1;i<=N;i++) {  // 테두리 1로 채우기
			arr[i][0]=1;
			arr[i][N+1]=1;
		}
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st;
		// 사과 배치하기 (숫자:2)
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine()," ");
			arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]=2;
		}
		// 뱀 시작위치
		arr[1][1]=1;
		int d = 0; // 시작 방향 오른쪽
		Deque<int[]> queue = new LinkedList<int[]>();  // 뱀의 몸통 정보를 저장
		queue.offerFirst(new int[] {1,1});  // 초기 위치 저장
		int result = 0;  // 결과 몇 초
		// 뱀의 이동정보 읽기
		int L = Integer.parseInt(br.readLine());
		for(int l=0; l<L; l++) {
			st = new StringTokenizer(br.readLine()," ");
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			// 각 이동에 대하여 
			while(result<X) {  // 방향 전환 할 시간 전까지 직진
				int nr = queue.peekFirst()[0]+dir[d][0];  // First가 머리쪽
				int nc = queue.peekFirst()[1]+dir[d][1];
				if(arr[nr][nc]!=1) {  // 다음 칸 이동 가능하면
					if(arr[nr][nc]==0) {  // 사과 안먹으면
						arr[queue.peekLast()[0]][queue.peekLast()[1]]=0;  // 꼬리가 한 칸 감소 
						queue.pollLast();
					}
					// 사과 먹으면 => 꼬리 삭제 안하므로 아무 일 없음
					result++;  // 1초 증가
					arr[nr][nc]=1;  // 한칸전진
					queue.offerFirst(new int[] {nr,nc});  // 머리 한 칸 추가
				} else {  // 다음 칸 이동 불가능하면
					System.out.println(++result);  // 1초 증가 후 종료
					return;
				}
			}
			// 방향 전환에 대하여
			if(C=='D') {  // 오른쪽 90도
				d = (d+1) % 4;
			} else if(C=='L') {  // 왼쪽 90도
				d = (d+3) % 4;
			}
		}
		// 방향 전환이 다 끝난 후 무한 직진
		while(true) {
			int nr = queue.peekFirst()[0]+dir[d][0];  // First가 머리쪽
			int nc = queue.peekFirst()[1]+dir[d][1];
			if(arr[nr][nc]!=1) {  // 다음 칸 이동 가능하면
				if(arr[nr][nc]==0) {  // 사과 안먹으면
					arr[queue.peekLast()[0]][queue.peekLast()[1]]=0;  // 꼬리가 한 칸 감소 
					queue.pollLast();
				}
				// 사과 먹으면 => 꼬리 삭제 안하므로 아무 일 없음
				result++;  // 1초 증가
				arr[nr][nc]=1;  // 한칸전진
				queue.offerFirst(new int[] {nr,nc});  // 머리 한 칸 추가
			} else {  // 다음 칸 이동 불가능하면
				System.out.println(++result);  // 1초 증가 후 종료
				return;
			}
		}
		
	}
}