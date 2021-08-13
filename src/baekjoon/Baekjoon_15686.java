package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 치킨 배달
public class Baekjoon_15686 {

	static int N,M;
	static int[][] arr; // 마을
	static ArrayList<int[]> houses = new ArrayList<int[]>(); // 집들의 좌표 저장
	static ArrayList<int[]> allChickens = new ArrayList<int[]>(); // 마을의 모든 치킨집들의 좌표 저장
	
	static int chickenSize; // 전체 치킨집의 총 갯수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		arr = new int[N][N];
		// 마을 생성
		for(int i=0;i<N;i++) {
			str = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		// 집과 치킨집들의 좌표 저장
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(arr[i][j] == 1) houses.add(new int[] {i,j});
				else if(arr[i][j] == 2) allChickens.add(new int[] {i,j});
			}
		}
		
		chickenSize = allChickens.size();
		int[] comb = new int[chickenSize];
		for(int i=0;i<M;i++) {
			comb[chickenSize-1-i] = 1;
		}
		int min = Integer.MAX_VALUE;
		do {
			ArrayList<int[]> chickensLeft = new ArrayList<int[]>(); // 살아남은 M개의 치킨집을 담을 배열
			for(int i=0;i<chickenSize;i++) {
				if(comb[i]==1) chickensLeft.add(allChickens.get(i)); // 살아남은 치킨집을 배열에 따로 저장
			}
			int result = homeToChicken(chickensLeft); // 살아남은 치킨집만으로 도시의 치킨거리 계산
			if(result < min) min = result;
		}while(np(comb));
		
		
		System.out.println(min);
	}
	
	public static boolean np(int[] numbers) {
		
		int N = numbers.length;
		
		int i = N-1;
		while(i>0 && numbers[i-1]>=numbers[i]) --i;
		
		if(i==0) return false;
		
		int j = N-1;
		
		while(numbers[i-1]>=numbers[j]) --j;
		
		swap(numbers,i-1,j);
		
		int k = N-1;
		while(i<k) {
			swap(numbers,i++,k--);
		}
		
		return true;
	}
	
	public static void swap(int[] numbers,int i,int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
		
	}
	public static int homeToChicken(ArrayList<int[]> chickens) { // 각 집들의 치킨 거리를 다 더한 도시의 치킨 거리 반환
		int size = houses.size();
		int sum = 0;
		for(int i=0; i<size; i++) {
			sum += homeToChicken(houses.get(i), chickens); // 첫 번째 집부터 마지막 집까지 각각의 치킨 거리를 다 더함
		}
		return sum;
	}
	public static int homeToChicken(int[] arr, ArrayList<int[]> chickens) { // 집의 치킨 거리 반환
		int size = chickens.size();
		int min = Integer.MAX_VALUE;
		for(int i=0; i<size; i++) {
			int distance = Math.abs(arr[0]-chickens.get(i)[0]) + Math.abs(arr[1]-chickens.get(i)[1]); // 각 치킨집과의 거리
			if(distance < min) {
				min = distance;
			}
		}
		return min;
		
	}
}
