package baekjoon;

import java.util.Scanner;

// 랭킹에 없는 점수들은 0으로 초기화 되는게 아니라 그냥 null 처럼 생각해야한다!!! 이거 오해해서 몇시간을 헤맨건지..

public class Baekjoon_1205 {
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();  // 랭킹에 올라갈 점수 갯수
		int newScore = sc.nextInt();  // 송유진의 새로운 점수
		int p = sc.nextInt();  // 랭킹에 올릴 수 있는 총 갯수
		if(n==0) {
			System.out.println("1");
			return;
		}
		int[] scores = new int[p];  // 랭킹 등록 범위
		
		for(int i = 0; i < n; i++) {  // 둘째 줄에 들어오는 랭킹에 등록할 점수들
			scores[i] = sc.nextInt();
		}
		
		for(int i = 0; i < n; i++) {  // 앞에서부터 랭킹 검색
			if(newScore == scores[i]) {  // 동점을 발견하면
				if(n==p) {  // 랭킹이 꽉 차있으면
					if(scores[i]==scores[p-1]) {  // 끝까지 동점자있으면
						System.out.println(-1);
						return;
					} else {
						System.out.println(i+1);
						return ;
					}
				} else {  // 랭킹이 꽉 차있지 않으면
					System.out.println(i+1);
					return ;
				}
			} else if(newScore > scores[i]) {  // 자기보다 낮은 점수를 발견하면 -> 게임 끝
				System.out.println(i+1);
				return;
			}
		}
		// for문 빠져나오면 기존 점수들 중간으로 못 끼어들었음
		if(n<p) {  // 랭킹이 꽉 차있지 않으면
			System.out.println(n+1);
			return;
		}
		System.out.println(-1);
	}
}
