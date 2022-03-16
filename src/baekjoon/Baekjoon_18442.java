package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 우체국 1
public class Baekjoon_18442 {

	static int V,P;
	static long L,result;
	static boolean[] selected;
	static long[] houses,selectedArr,resultArr;
	static long[][] D;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		V = Integer.parseInt(str[0]);
		P = Integer.parseInt(str[1]);
		L = Long.parseLong(str[2]);
		houses = new long[V]; // 집들의 좌표
		str = br.readLine().split(" ");
		for(int i=0; i<V; ++i) {
			houses[i] = Long.parseLong(str[i]);
		}
		// 플로이드 와샬 알고리즘
		D = new long[V][V];

		for (int i=0; i<V; i++) {
			for (int j=0; j<V; j++) {
				long dis = Math.abs(houses[i]-houses[j]);
				D[i][j] = Math.min(dis, L-dis);
			}
		}

		for (int k=0; k<V; k++) {
			for (int i=0; i<V; i++) {
				if (i == k) continue; // 출발지 = 경유지
				for (int j=0; j<V; j++) {
					if (i==j) continue; // 출발지 = 도착지
					if (D[i][j] > D[i][k] + D[k][j]) {
						D[i][j] = D[i][k] + D[k][j];
					}
				}
			}
		}
		selected = new boolean[V];
		result = Long.MAX_VALUE;
		selectedArr = new long[P];
		dfs(0,0);
		System.out.println(result);
		for(long house : resultArr) {
			System.out.print(house+" ");
		}
	}
	public static void dfs(int start, int depth) {
		
		if(depth==P) { // P개 만큼 경찰서를 선택하면
			findMin(); // 뽑은 조합으로 최솟값 찾기 
			return;
		}
		
		for(int i=start; i<V; ++i) {
			selectedArr[depth] = houses[i];
			selected[i]=true;
			dfs(i+1,depth+1);
			selected[i]=false;
		}
	}
	public static void findMin() {
		long sum = 0;
		for(int i=0; i<V; ++i) {
			long min = Long.MAX_VALUE;
			for(int j=0; j<V; ++j) {
				if(selected[j] && min>D[i][j]) min = D[i][j]; // 한 마을로부터 최소거리를 구해서
			}
			sum += min; // 전체 거리의 합에 더해줌
		}
		if(result>sum) {
			result=sum; // 최솟값 갱신
			resultArr = selectedArr.clone();
		}
	}
}