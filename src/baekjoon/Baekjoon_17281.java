package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 순열을 이용하여 최대 50이닝 이므로 8! * 50 = 2,016,000 이어서 시간초과가 발생하지 않을 줄 알았는데,
 * 처음에 주자들이 전진하는 과정에서 큐를 사용하였다가 시간초과가 발생하였다.
 * 이후 boolean배열로 수정해서 관리하여 해결하였다.
 * 아마 최악의 경우 한 이닝당 최대 27명의 타자가 존재하므로
 * 2,016,000 * 27 = 54,432,000 이기때문에 큐를 사용하면 아슬아슬하게 시간초과가 발생하는것 같다는 생각이 든다.
 */
public class Baekjoon_17281 {

	static int N,totalScore,result,orderIdx;
	static int[] order = new int[9]; // 선수들의 번호들이 타순으로 저장된 배열, 항상 order[3]=0은 고정
	static boolean[] used = new boolean[9];
	static int[][] points; // 각 이닝에서 선수들이 얻는 결과
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line;
		N = Integer.parseInt(br.readLine());
		points = new int[N][9];
		for(int i=0; i<N; ++i) {
			line = br.readLine().split(" ");
			for(int j=0; j<9; ++j) {
				points[i][j] = Integer.parseInt(line[j]); // 선수들의 결과를 저장
			}
		}
		result = 0;
		makeOrder(1);
		System.out.println(result);
	}
	
	public static void makeOrder(int n) { // order[n]에 세울 선수를 저장
		if(n==9) {
			totalScore = 0; // 얻은 점수값 초기화
			orderIdx = 0; // 현재 타자의 인덱스 초기화
			for(int i=0; i<N; ++i) { // N이닝 동안 경기 진행
				playOneInning(i); // i번쨰 이닝을 진행
			}
			if(result<totalScore) result = totalScore; // 최댓값 갱신
			return;
		}
		for(int i=1; i<9; ++i) {
			if(!used[i]) {
				used[i]=true;
				if(n<4) order[n-1]=i; // n이 1,2,3일때는 인덱스 하나 감소 후 선수를 저장
				else order[n]=i;
				makeOrder(n+1);
				used[i]=false;
			}
			
		}
	}
	
	public static void playOneInning(int inning) {
		int outCount = 0; // 아웃 횟수
		boolean[] base = new boolean[4]; // 홈,1루,2루,3루
		while(true) {
			int hit = points[inning][order[orderIdx]]; // 현재 순서의 타자가 얻은 결과
			if(hit==0) outCount++; // 아웃이면
			else { // 안타 혹은 홈런이면
				base[0] = true; // 현재 타자를 출루
				for(int i=3; i>=0; --i) {
					if(base[i]) {// 주자가 존재하면
						if(i+hit>=4) totalScore++; // 주자가 홈에 들어오면 1점 획득
						else base[i+hit]=true; // 주자가 홈에 못들어오면 주자 위치를 이동
						base[i]=false; // 원래 있던 자리 비우기
					}
				}
			}
			orderIdx = (orderIdx+1)%9; // 타자의 순서는 다음타자로 변경
			if(outCount==3) break; // 3번 아웃이면 현재 이닝 종료
		}
	}
}