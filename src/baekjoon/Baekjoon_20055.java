package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 컨베이어 벨트 위의 로봇
/*
 * 문제조건에 따라 구현만 하면 크게 어려움 없이 해결가능한 문제였다.
 */
public class Baekjoon_20055 {
	
	static int N,K,zeroCnt;
	static int inPos,outPos; // 올리는 위치,내리는 위치
	static int[] conveyorBelt; // 각 칸의 내구도 저장
	static boolean[] isRobot; // 올리는 위치로부터 해당 인덱스만큼 다음 위치에 로봇이 존재하는지

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		K = Integer.parseInt(line[1]);
		
		inPos=0;outPos=N-1;
		conveyorBelt = new int[2*N];
		isRobot = new boolean[N];
		
		line = br.readLine().split(" ");
		for(int i=0; i<2*N; ++i) {
			conveyorBelt[i] = Integer.parseInt(line[i]);
		}

		int times = 0; // 시도한 횟수
		zeroCnt = 0; // 내구도 0인 칸의 갯수
		while(true) {
			times++;
			rotate();
			moveRobots();
			upRobot();
			if(zeroCnt>=K) break;
		}
		System.out.println(times);
	}
	
	public static void rotate() { // 벨트 회전
		inPos = (inPos+2*N-1)%(2*N); // 올리는 위치 한 칸 이동
		outPos = (outPos+2*N-1)%(2*N); // 내리는 위치 한 칸 이동
		for(int i=N-2; i>=0; --i) { // 로봇들도 한 칸씩 이동
			if(isRobot[i]) { // 로봇이 존재하면 한 칸 이동
				isRobot[i+1]=true;
				isRobot[i]=false;
			}
		}
		isRobot[N-1]=false; // 내리는 위치의 로봇 제거
	}
	
	public static void moveRobots() {
		for(int i=N-2; i>=0; --i) {
			if(isRobot[i]) { // 로봇이 존재하면
				if(!isRobot[i+1] && conveyorBelt[(inPos+i+1)%(2*N)]>0) { // 다음 칸에 로봇이 없고 내구도가 1이상이면
					if(--conveyorBelt[(inPos+i+1)%(2*N)]==0) { // 다음칸의 내구도 1 감소
						zeroCnt++; // 내구도가 0이면 카운트 1 증가
					}
					isRobot[i+1]=true; // 다음칸으로 이동
					isRobot[i]=false;
				}
			}
		}
		isRobot[N-1]=false; // 내리는 위치의 로봇 제거
	}
	
	public static void upRobot() { // 로봇 올리기
		if(conveyorBelt[inPos]>0) { // 내구도 1이상 남아있으면
			if(--conveyorBelt[inPos]==0) { // 내구도 1 감소 및 내구도 0 카운트
				zeroCnt++;
			}
			isRobot[0]=true; // 로봇 올리기
		}
	}
}