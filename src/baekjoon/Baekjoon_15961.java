package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// 회전 초밥
public class Baekjoon_15961 {
	
	static int N,d,k,c;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		d = Integer.parseInt(str[1]);
		k = Integer.parseInt(str[2]);
		c = Integer.parseInt(str[3]);
		int[] dish = new int[N]; // 벨트위에 놓여있는 초밥들의 배열
		int[] numOfDish = new int[d+1]; // 각 초밥종류마다 몇개가 선택되었는지, 번호는 1번부터 시작
		Set<Integer> dishSet = new HashSet<Integer>();
		for(int i=0; i<N; ++i) {
			dish[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=0; i<k; ++i) { // 시작은 처음부터 k개 연속해서 선택
			numOfDish[dish[i]]++; // 해당 초밥을 선택한 갯수 1 증가
			dishSet.add(dish[i]);
		}
		int result = dishSet.size();
		for(int i=0; i<N; ++i) { // i번쨰 접시 빼고, i+k번쨰 접시를 추가하며 한칸씩 움직인다.
			int nextDish = i+k; // 새로 포함할 접시의 인덱스
			if(nextDish>=N) nextDish %= N; // 한바퀴 돌면 N으로 나누어 0부터 다시 시작
			if(--numOfDish[dish[i]] == 0) { // 첫번쨰 접시 하나 줄이면서 0개가 남으면
				dishSet.remove(dish[i]);
			}
			++numOfDish[dish[nextDish]]; // 다음접시 새로 포함
			dishSet.add(dish[nextDish]);
			dishSet.add(c); // 쿠폰 번호는 항상 추가
			if(result<dishSet.size()) result = dishSet.size();
		}
		System.out.println(result);
	}
}