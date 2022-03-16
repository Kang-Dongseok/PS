package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// 거짓말
/*
 * set을 이용하여 파티원들을 묶고, boolean배열을 이용하여 진실을 아는 사람들을 체크하며
 * 해당 파티에 한명이라도 진실을 하는자가 있으면, 그 파티원 모두 진실을 알게 저장한 후, 진실을 말해야만 하는 파티를
 * allTruth라는 boolean배열을 통해서 체크하고, 거짓말을 해도 되는 파티의 수만 세어서 답을 구한다.
 */
public class Baekjoon_1043 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int M = Integer.parseInt(line[1]);
		
		boolean[] truth = new boolean[N+1]; // 진실을 아는 사람의 번호
		boolean[] allTruth = new boolean[M]; // 모두가 진실을 아는 파티번호
		Set<Integer>[] partySet = new HashSet[M];
		for(int i=0; i<M; ++i) {
			partySet[i] = new HashSet<Integer>();
		}
		
		line = br.readLine().split(" ");
		int truthNum = Integer.parseInt(line[0]);
		for(int i=1; i<=truthNum; ++i) {
			truth[Integer.parseInt(line[i])]=true; // 사실을 아는사람 체크
		}
		
		for(int i=0; i<M; ++i) {
			line = br.readLine().split(" ");
			int peopleNum = Integer.parseInt(line[0]); // 한 파티당 오는 사람의 수
			for(int j=1; j<=peopleNum; ++j) {
				partySet[i].add(Integer.parseInt(line[j]));
			}
		}
		
		for(int t=0; t<M; ++t) { // M번 반복
			for(int i=0; i<M; ++i) { // 각 파티마다
				if(allTruth[i]) continue; // 모두가 사실을 알아서 확인할 필요가 없으면 무시
				Set<Integer> set = partySet[i];
				for(int personNum : set) {
					if(truth[personNum]) { // 해당 파티에 진실을 아는 사람이 존재하면
						for(int num : set) {
							truth[num]=true; // 해당 파티의 모든 사람이 진실을 알게 체크
						}
						allTruth[i]=true; // 모두가 사실 확인 체크
						break;
					}
				}
			}
		}
		
		int result = 0;
		for(int i=0; i<M; ++i) {
			if(!allTruth[i]) result++;
		}
		System.out.println(result);
	}
}