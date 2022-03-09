package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 미네랄
/*
 * 단순한 구현문제라서 풀이법은 그렇게 어렵지 않다.
 * 클러스터를 떨어뜨리는 거리를 계산하는 과정에서 논리적인 오류를 찾는다고 시간을 많이 소비했다...
 */
public class Baekjoon_2933 {

	static int R,C,N;
	static int turn; // 왼쪽, 오른쪽 중 어느 방향에서 던지는지 (0:왼, 1:오)
	static int fallDist; // 클러스터가 떨어지는 거리
	static char[][] map;
	static ArrayList<int[]> clusterPos; // 한 클러스터의 좌표들을 저장
	static boolean[][] sameGroupCheck; // 같은 클러스터의 미네랄 표시
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 상,우,하,좌
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		map = new char[R][C];
		for(int i=0; i<R; ++i) {
			map[i] = br.readLine().toCharArray();
		}
		clusterPos = new ArrayList<int[]>();
		N = Integer.parseInt(br.readLine());
		turn = 0; // 시작은 왼쪽부터 던짐
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		while(st.hasMoreTokens()) {
			int height = Integer.parseInt(st.nextToken());
			int[] point = throwStick(R-height); // 바닥부터 1이므로 인덱스를 뒤에서부터 계산한다
			if(point[0]<0) { // 충돌하지 않으면 다음 차례
				turn ^= 1; // 왼쪽, 오른쪽 던지는 순서 바꿈
				continue;
			}
			if(canClusterFall(point)) { // 떨어질 클러스터가 존재하면
				fallCluster(); // 클러스터 떨어뜨리기
			}
			turn ^= 1;
		}
		for(char[] ar : map) {
			for(char a : ar) {
				System.out.print(a);
			}
			System.out.println();
		}
	}
	
	public static int[] throwStick(int height) { // 막대기를 던져 충돌지점 미네랄 제거 및 충돌지점을 반환 
		int[] crossPoint = new int[] {-1,-1}; // 막대기와 미네랄의 충돌지점, {-1,-1}로 초기화
		if(turn==0) { // 왼쪽에서 던짐
			for(int c=0; c<C; ++c) {
				if(map[height][c]=='x') { // 미네랄과 충돌하면
					map[height][c]='.';
					crossPoint[0] = height;
					crossPoint[1] = c;
					break;
				}
			}
		}else { // 오른쪽에서 던짐
			for(int c=C-1; c>=0; --c) {
				if(map[height][c]=='x') { // 미네랄과 충돌하면
					map[height][c]='.';
					crossPoint[0] = height;
					crossPoint[1] = c;
					break;
				}
			}
		}
		return crossPoint;
	}
	
	public static boolean canClusterFall(int[] point) { // 떨어지는 클러스터가 있는지 체크
		boolean exist = false; // 존재 안함으로 초기화
		if(checkClusterPos(new int[] {point[0]-1, point[1]})) { // 충돌지점 윗칸 클러스터 체크
			exist=true;
		}else if(checkClusterPos(new int[] {point[0]+1, point[1]})) { // 충돌지점 아랫칸 클러스터 체크
			exist=true;
		}else { // 좌,우 중 한곳 체크
			if(turn==0) { // 왼쪽에서 던졌으면 오른쪽칸 체크
				if(checkClusterPos(new int[] {point[0], point[1]+1})) exist=true;
			}else { // 오른쪽에서 던졌으면 왼쪽칸 체크
				if(checkClusterPos(new int[] {point[0], point[1]-1})) exist=true;
			}
		}
		return exist;
	}
	
	public static boolean checkClusterPos(int[] point) { // point가 속해있는 클러스터의 모든 좌표들을 확인
		if(point[0]<0 || point[1]<0 || point[0]>=R || point[1]>=C) return false; // 경계 벗어나면 false
		if(map[point[0]][point[1]]=='.') return false; // 해당 칸이 빈칸이면 false
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(point);
		
		boolean[][] visited = new boolean[R][C];
		clusterPos.clear(); // 기존의 좌표들 초기화
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			if(r==R-1) return false; // 바닥에 붙어있는 미네랄이 있으면 false
			
			if(visited[r][c]) continue;
			visited[r][c]=true; // 방문 체크
			clusterPos.add(new int[] {r,c}); // 클러스터의 미네랄 좌표들을 저장
			
			for(int d=0; d<4; ++d) {
				int nr = r+dir[d][0];
				int nc = c+dir[d][1];
				if(nr<0 || nc<0 || nr>=R || nc>=C) continue; // 다음좌표 경계 체크
				if(map[nr][nc]=='x') { // 다음 칸이 미네랄이면
					q.offer(new int[] {nr,nc});
				}
			}
		}
		
		fallDist=999; // 떨어지는 최소 거리 초기화
		sameGroupCheck = new boolean[R][C]; // 같은 클러스터는 true
		for(int[] position : clusterPos) { // 각 미네랄 지점마다
			sameGroupCheck[position[0]][position[1]]=true; // 같은 클러스터의 미네랄 체크
		}
		for(int[] pos : clusterPos) {
			fallDist = Math.min(fallDist, calcFallHeight(pos)); // 떨어지는 거리 계산하여 갱신
		}
		return true; // 공중에 떠있는 클러스터의 미네랄 좌표들을 저장 후 true 리턴
	}
	
	public static int calcFallHeight(int[] pos) {
		int r = pos[0]+2; // 2칸 아래
		int c = pos[1];
		int dist = 1; // 최소 떨어지는 거리는 1
		for(; r<R; ++r) { // 2칸 아래부터 떨어지는 거리 계산
			if(map[r][c]=='.') dist++; // 아래가 빈칸이면 떨어지는 거리 1 증가
			else { // 미네랄을 만나면
				if(!sameGroupCheck[r][c]) { // 다른 클러스터의 미네랄이면
					break; // 그만
				}else { // 같은 클러스터의 미네랄이면
					return 999; // 쓰레기값 리턴
				}
			}
		}
		return dist;
	}
	
	public static void fallCluster() {
		for(int[] pos : clusterPos) { // 각 미네랄 지점마다
			map[pos[0]][pos[1]]='.'; // 빈칸으로 만들기
		}
		for(int[] pos : clusterPos) { // 각 미네랄 지점마다
			map[pos[0]+fallDist][pos[1]]='x'; // 떨어진 칸으로 미네랄 옮기기
		}
	}
}