package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 톱니바퀴(2)
public class Baekjoon_15662 {
	
	static int T;
	static int[][] magnets;
	static int[] norths;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		T = Integer.parseInt(br.readLine()); // 톱니바퀴의 갯수
		magnets = new int[T][8]; // 톱니바퀴의 정보들
		norths = new int[T]; // 각 톱니바퀴의 12시방향을 가리키는 날의 인덱스
		for(int i=0; i<T; ++i) {
			str = br.readLine().split("");
			for(int j=0; j<8; ++j) {
				magnets[i][j] = Integer.parseInt(str[j]);
			}
		}
		int K = Integer.parseInt(br.readLine()); // 회전 수
		for(int k=0; k<K; ++k) {
			str = br.readLine().split(" ");
			int num = Integer.parseInt(str[0])-1; // 자석의 번호(0~3)
			int dir = Integer.parseInt(str[1]); // 회전 방향
			rotate(num,dir); // 회전
		}
		int result = 0;
		for(int i=0; i<T; ++i) { // 12시방향이 s인 갯수를 다 더하기
			result += magnets[i][norths[i]];
		}
		System.out.println(result);
	}
	public static void rotate(int num, int dir) {
		int left = num; // 회전가능한 제일 왼쪽 자석
		int right = num; // 회전가능한 제일 오른쪽 자석
		for(int i=num-1; i>=0; --i) { // 왼쪽자석들 탐색
			if(magnets[i][(norths[i]+2)%8]==magnets[i+1][(norths[i+1]+6)%8]) break; // 현재 자석의 3시방향과 오른쪽 자석의 9시 방향의 날이 같으면 회전X
			left = i;
		}
		for(int i=num+1; i<T; ++i) { // 오른쪽자석들 탐색
			if(magnets[i][(norths[i]+6)%8]==magnets[i-1][(norths[i-1]+2)%8]) break; // 현재 자석의 9시방향과 왼쪽 자석의 3시 방향의 날이 같으면 회전X		
			right = i;
		}
		if(dir==1) { // 시계방향
			for(int i=left; i<=right; ++i) {
				if((num+i)%2==0) { // num 자석과 짝수만큼 떨어져 있으면
					norths[i] = (norths[i]+7)%8; // 시계방향  
				}else { // 홀수만큼 떨어져있으면
					norths[i] = (norths[i]+1)%8; // 반시계방향  
				}
			}
		}else { // 반시계방향
			for(int i=left; i<=right; ++i) {
				if((num+i)%2==0) { // num 자석과 짝수만큼 떨어져 있으면
					norths[i] = (norths[i]+1)%8; // 반시계방향  
				}else { // 홀수만큼 떨어져있으면
					norths[i] = (norths[i]+7)%8; // 시계방향  
				}
			}
		}
	}
}