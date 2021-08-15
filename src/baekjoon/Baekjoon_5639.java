package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 이진 검색 트리
/*
 * 정수값과 왼쪽,오른쪽 자식을 가지는 노드 클래스를 생성하여 직접 트릴 만들고, 후위순회를 하였다.
 * 시간 복잡도: O(NlogN), 트리 내려가면서 비교하니까?
 */
public class Baekjoon_5639 {
	
	static class Node{ // 트리를 만들기 위한 노드를 만든다.
		 int num;
		 Node left, right;
		 
		 Node(int num){
			 this.num = num;
		 }
		 
		 void add(int newNum) {
			 if(num>newNum) { // 자신보다 작은 수가 들어오면
				 if(left == null) left = new Node(newNum); // 왼쪽 자식이 없으면 왼쪽 자식으로 생성
				 else left.add(newNum); // 왼쪽 자식이 있으면 다시 왼쪽 자식을 기준으로 비교
			 }else { // 자신보다 큰 수가 들어오면
				 if(right == null) right = new Node(newNum); // 오른쪽 자식이 없으면 왼쪽 자식으로 생성
				 else right.add(newNum); // 오른쪽 자식이 있으면 다시 오른쪽 자식을 기준으로 비교
			 }
		 }
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine(); // 첫 줄 읽어서
		Node rootNode = new Node(Integer.parseInt(str)); // root 노드 생성
		while(true) { // readLine은 값이 없으면 빈문자열을 반환하므로
			str = br.readLine();
			if(str == null || str.equals("")) break;
			rootNode.add(Integer.parseInt(str));
		}
		postOrder(rootNode);
	}

	public static void postOrder(Node node) {
		if(node == null) return; // 방문한 자식이 없으면 리턴
		postOrder(node.left); // 왼쪽 자식 먼저 방문
		postOrder(node.right); // 오른쪽 자식 그 다음 방문
		System.out.println(node.num); // 본인 방문
	}
}