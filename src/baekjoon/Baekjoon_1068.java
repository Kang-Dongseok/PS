package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;


// 트리
/*
 * 여러개의 루트노드가 존재할 수 있음에 주의!
 * 각 노드마다 부모노드의 인덱스를 저장한 후
 * 부모노드(값을 -1 가진 인덱스)를 기점으로 시작하여
 * 자신을 부모노드로 가지는 자식노드가 더 이상 없을 때까지 dfs로 탐색한다. 
 * 시간복잡도: O(N^2), 최악의 경우 검색할 때 1+2+...+N 이다. 하지만 N이 50이하이기 떄문에 크게 상관없다. 
 */
public class Baekjoon_1068 {
	
	static int[] arr; // 각 N번째 노드에 자신의 부모노드의 인덱스를 저장
	static int N;
	static Queue<Integer> roots = new ArrayDeque<Integer>(); // 루트 노드들의 인덱스를 저장
	static int leafCnt; // 리프노드의 갯수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		String[] str = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		int idx = Integer.parseInt(br.readLine());
		arr[idx] = -2; // 트리에서 자르기 위해 해당 노드가 부모노드로 가리키는 값을 존재하지 않는 값(-2)로 변경
		searchRoot();
		while(!roots.isEmpty()) {
			func(roots.poll());
		}
		System.out.println(leafCnt);
		
	}
	
	public static void searchRoot() { // 루트 노드들 전부 찾아서 roots에 인덱스 저장
		for(int i=0; i<N; i++) {
			if(arr[i] == -1) roots.offer(i);
		}
	}
	
	public static void func(int idx) { // 루트노드의 인덱스가 주어지면 리프노드의 갯수를 계산
		boolean isLeaf = true; // 리프노드 유무
		for(int i=0; i<N; i++) {
			if(arr[i]==idx) { // 자신을 부모로 하는 자식노드 발견하면
				isLeaf = false; // 리프노드가 아님
				func(i);
			}
		}
		if(isLeaf) leafCnt++; // 리프노드이면 갯수 증가
	}
}
