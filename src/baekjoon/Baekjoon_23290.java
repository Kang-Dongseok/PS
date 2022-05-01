package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// 마법사 상어와 복제
/*
 * 미해결
 */
public class Baekjoon_23290 {

	static int M,S,R,C;
	static int[][] smell = new int[5][5]; // 냄새
	static ArrayList<Integer>[][] map = new ArrayList[5][5]; // 물고기
	static ArrayList<Integer>[][] copiedMap = new ArrayList[5][5]; // 복사 후 물고기
	
//	static ArrayList<int[]> fishList;
//	static ArrayList<int[]> copiedList; // 복사된 물고기
	
	static int[][] dir = {{0,0},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
//		fishList = new ArrayList<int[]>();
		M = Integer.parseInt(str[0]);
		S = Integer.parseInt(str[1]);
		for(int i=1; i<5; ++i) {
			for(int j=1; j<5; ++j) {
				map[i][j] = new ArrayList<Integer>();
			}
		}
		for(int i=0; i<M; ++i) {
			str = br.readLine().split(" ");
			int r = Integer.parseInt(str[0]);
			int c = Integer.parseInt(str[1]);
			int d = Integer.parseInt(str[2]);
			map[r][c].add(d);
		}
		
		str = br.readLine().split(" ");
		R = Integer.parseInt(str[0]); // 상어의 행
		C = Integer.parseInt(str[1]); // 열
		
		for(int i=0; i<S; ++i) {
			copy(); // 물고기들 일단 복사
			System.out.println(1);
//			printCopy();
//			print2();
			moveFish();
			System.out.println(2);
//			print2();
			decreaseSmell(); // 냄새 감소
			System.out.println(3);
			moveShark();
			System.out.println(4);
//			print2();
			paste(); // 물고기들 붙여넣기
			System.out.println(5);
//			print2();
//			System.out.println("smell");
//			printSmell();
			System.out.println(i);
		}
		int answer = 0;
		for(int k=1; k<5; ++k) {
			for(int j=1; j<5; ++j) {
				answer += map[k][j].size();
			}
		}
		System.out.println(answer);
//		System.out.println(R+"<"+C);
		
	}
	
	public static void copy() {
		copiedMap = map;
//		copiedMap = new ArrayList[5][5];
//		for(int i=1; i<5; ++i) {
//			for(int j=1; j<5; ++j) {
//				copiedMap[i][j] = new ArrayList<>();
//			}
//		}
//		for(int i=1; i<5; ++i) {
//			for(int j=1; j<5; ++j) {
//				if(!map[i][j].isEmpty()) { // 물고기가 있으면
//					for(int d : map[i][j]) {
//						copiedMap[i][j].add(d); // 물고기 복사
//					}
//				}
//			}
//		}
	}
	
	public static void moveFish() {
		ArrayList<Integer>[][] arr = new ArrayList[5][5];
		for(int i=1; i<5; ++i) {
			for(int j=1; j<5; ++j) {
				arr[i][j] = new ArrayList<Integer>();
			}
		}
		
		for(int i=1; i<5; ++i) {
			for(int j=1; j<5; ++j) {
				if(!map[i][j].isEmpty()) { // 물고기가 있으면
				loop:for(int d : map[i][j]) {
						for(int k=0; k<8; ++k) {
							int nd = d-k;
							if(nd<=0) nd += 8;
							int nr = i+dir[nd][0];
							int nc = j+dir[nd][1];
							if(nr<1 || nr>4 || nc<1 || nc>4) continue; // 경계 체크
							if(nr==R && nc==C) continue; // 상어 체크
							if(smell[nr][nc]>0) continue; // 냄새 체크
							// 이동할 수 있는 칸 발견하면
							arr[nr][nc].add(nd);
							continue loop; // 이동 후 다음 물고기
						}
						arr[i][j].add(d); // 이동불가시 제자리
					}
				}
			}
		}
		map = arr; // 움직인 map으로 변경
	}
	
	static int[] path; // 상어의 이동경로
	static int max; // 최대 잡을 수 있는 물고기 수
	static int[] maxPath; // 상어의 확정 이동경로
	static int[][] dirShark = {{-1,0},{0,-1},{1,0},{0,1}}; // 상,좌,하,우
	
	public static void moveShark() {
		// 초기화
		path = new int[3];
		max = 0;
		maxPath = new int[3];
		dfs(0,R,C);
//		for(int i=0; i<3; ++i) {
//			System.out.print(maxPath[i]+"");
//		}
//		System.out.println();
		if(max==0) {
			for(int i=0; i<3; ++i) {
				int nr = R;
				int nc = C;
				for(int d=0; d<3; ++d) {
					nr += dirShark[d][0];
					nc += dirShark[d][1];
					if(nr<1 || nr>4 || nc<1 || nc>4) continue;
					R = nr;
					C = nc;
					break;
				}
			}
		}else {
			for(int i=0; i<3; ++i) {
				R += dirShark[maxPath[i]][0]; // 상어 이동
				C += dirShark[maxPath[i]][1];
//				System.out.println(R+","+C);
				if(!map[R][C].isEmpty()) { // 물고기 있으면
					map[R][C].clear(); // 물고기 제거
					smell[R][C] = 2; // 냄새 2번
				}
			}
		}
	}
	
	public static void dfs(int n, int r, int c) {
//		System.out.println(r+","+c);
		if(n==3) {
			Set<Integer> set = new HashSet<Integer>();
			set.add(R*5+C); // 제자리 체크
			int nr = R;
			int nc = C;
			int sum = 0;
//			for(int i=0; i<3; ++i) {
//				System.out.print(path[i]+",");
//			}
//			System.out.println();
			for(int i=0; i<3; ++i) {
				nr += dirShark[path[i]][0];
				nc += dirShark[path[i]][1];
//				System.out.println(nr+","+nc);
				if(!set.contains(nr*5+nc)) { // 이전에 체크 안했으면
					set.add(nr*5+nc); // 추가
					sum += map[nr][nc].size(); // 물고기수 증가
				}
			}
			if(sum>max) { // 최댓값 갱신
				max=sum;
				for(int i=0; i<3; ++i) {
					maxPath[i] = path[i];
				}
			}
			return;
		}
		for(int d=0; d<4; ++d) {
			int nr = r+dirShark[d][0];
			int nc = c+dirShark[d][1];
			if(nr<1 || nr>4 || nc<1 || nc>4) continue; // 경계
			path[n]=d;
			dfs(n+1,nr,nc);
		}
	}
	
	public static void paste() {
		for(int i=1; i<5; ++i) {
			for(int j=1; j<5; ++j) {
				if(!copiedMap[i][j].isEmpty()) { // 존재하면
					map[i][j].addAll(copiedMap[i][j]);
//					for(int d : copiedMap[i][j]) {
//						map[i][j].add(d); // map으로 붙여넣기
//					}
				}
			}
		}
	}
	
	public static void decreaseSmell() {
		for(int i=1; i<5; ++i) {
			for(int j=1; j<5; ++j) {
				if(smell[i][j]>0) smell[i][j]--; 
			}
		}
	}
	
	public static void print() {
		for(int i=1; i<5; ++i) {
			for(int j=1; j<5; ++j) {
				System.out.print(map[i][j].size()+" ");
			}
			System.out.println();
		}
	}
	public static void printCopy() {
//		for(int i=1; i<5; ++i) {
//			for(int j=1; j<5; ++j) {
//				System.out.print(copiedMap[i][j].size()+" ");
//			}
//			System.out.println();
//		}
		for(int i=1; i<5; ++i) {
			for(int j=1; j<5; ++j) {
				if(!copiedMap[i][j].isEmpty()) {
					for(int d : copiedMap[i][j]) {
						System.out.print(d+"");
					}
				}else {
					System.out.print(0);
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	public static void print2() {
		for(int i=1; i<5; ++i) {
			for(int j=1; j<5; ++j) {
				if(!map[i][j].isEmpty()) {
					for(int d : map[i][j]) {
						System.out.print(d+"");
					}
				}else {
					System.out.print(0);
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	public static void printSmell() {
		for(int i=1; i<5; ++i) {
			for(int j=1; j<5; ++j) {
				System.out.print(smell[i][j]+" ");
			}
			System.out.println();
		}
	}
}